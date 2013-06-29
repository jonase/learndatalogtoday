(ns learndatalogtoday.core
  (:require [learndatalogtoday.remote :as remote]
            [goog.History :as History]
            [goog.events]
            [goog.events.EventType]
            [hiccups.runtime :as hiccup]
            [domina]
            [domina.css :refer [sel]]
            [domina.events :as events]))

(def $ js/$)
(defn log [x]
  (.log js/console x))

(def history (goog.History.))

(let [converter (js/Markdown.Converter.)]
  (defn md [text]
    (.makeHtml converter text)))

(def state (atom {:current-tab nil}))

(defn build-input [tab-n input-n input]
  (let [[label id] (condp = (:type input)
                     :query ["Query:" (str "query-input-tab-" tab-n)]
                     :rule ["Rules:" (str "rules-input-tab-" tab-n)] 
                     :value [(str "Input #" input-n ":") (str "value-tab-" tab-n "-input-" input-n)])]
    (list [:div.span2.text-right [:strong label]]
          [:div.span7 [:textarea.cm {:id id} (:value input)]])))

(defn build-inputs [tab-n inputs]
  (map-indexed (partial build-input tab-n) inputs))

(defn build-exercise [tab-n exercise]
  (list [:div.tab-pane {:id (str "tab" tab-n)}
         [:p (md (:question exercise))]
         [:div.row.inputs
          (build-inputs tab-n (:inputs exercise))]]))

(defn build-exercises [exercises]
  (list [:div.tabbable
         [:ul.nav.nav-tabs
          (for [n (range (count exercises))]
            [:li [:a {:href (str "#tab" n) 
                      :data-toggle "tab"}
                  [:span.label n]]])]
         [:div.tab-content
          (map-indexed build-exercise exercises)]]
        [:div.row
          [:div.span7.offset2
           [:button#run-query.btn.btn-block "Run Query"]]]))

(defn get-current-editors []
  (let [s @state] 
    (get-in s [:tabs (:current-tab s)])))

(defn get-current-editor-values []
  (map #(.getValue %) (get-current-editors)))

(defn render-chapter [chapter]
  (let [text (:text chapter)
        exercises (:exercises chapter)]
        (domina/append! (domina/by-id "text-content")
                        (md text))
        
        (domina/append! (domina/by-id "exercises-content")
                        (hiccup/render-html (build-exercises exercises)))
        
        (doseq [n (range (count exercises))]
          (let [id (str "tab" n)
                tas (sel (str "#" id " textarea"))
                cms (mapv (fn [ta] (.fromTextArea js/CodeMirror ta)) (domina/nodes tas))]
            (swap! state assoc-in [:tabs id] cms)))
        
        ;; In jQuery since I couldn't get "shown" to work in domina
        (-> ($ "a[data-toggle=\"tab\"]")
            (.on "shown"
                 (fn [evt]
                   (swap! state assoc :current-tab 
                          (subs (domina/attr (.-target evt) "href") 1))
                   (doseq [e (get-current-editors)] (.refresh e)))))
        
        (events/listen! (domina/by-id "run-query")
                        :click
                        (fn [evt]
                          (let [args (get-current-editor-values)]
                            (log (pr-str args)))))
        
        (-> ($ "a[href=\"#tab0\"]") (.tab "show"))))

(defn init []
  (remote/get "/chapters"
    (fn [chapters]
      (goog.events/listen history
                          (.-NAVIGATE goog.history/EventType)
                          (fn [evt]
                            (let [n (js/parseInt (.-token evt))]
                              (if (js/isNaN n)
                                (.setToken history "0")
                                (render-chapter (chapters n))))))
      (.setEnabled history true)


      )))
