(ns learndatalogtoday.core
  (:require [learndatalogtoday.reader :refer [read-string]]
            [datomic-query-helpers.core :refer [normalize]]
            [hylla.remote :as remote] 
            [domina :refer [by-id nodes]]
            [domina.css :refer [sel]]
            [domina.events :refer [listen! prevent-default]]
            [hiccups.runtime :refer [render-html]]))

(defn find-clause [q]
  (:find (normalize q)))

(defn render-row [row] 
  (render-html 
   [:tr (for [x row] [:td (pr-str x)])]))

(defn render-error [msg]
  (render-html 
   [:div.alert.alert-error
    [:strong "Oh snap!"]
    [:p msg]]))

(defn render-fail [msg]
  (render-html
   [:div.alert
    [:p msg]]))

(defn render-success [msg]
  (render-html
   [:div.alert
    [:p msg]]))

(defn render-result [exercise query result-data]
  (let [alert (sel (str "#tab" exercise " .alerts"))
        thead (sel (str "#tab" exercise " thead"))
        tbody (sel (str "#tab" exercise " tbody"))]
    (domina/destroy-children! alert)
    (domina/destroy-children! thead)
    (domina/destroy-children! tbody)
    (if (= :error (:status result-data))
      (domina/append! alert (render-error (:message result-data)))
      (do (if (= :fail (:status result-data))
            (domina/append! alert (render-fail "Sorry, these results are not correct"))
            (do (domina/add-class! (sel ".active .label") "label-success")
                (domina/append! alert (render-success "Correct!"))))
          (domina/append! thead (render-row (find-clause query)))
          (doseq [row (:result result-data)]
            (domina/append! tbody (render-row row)))))))

(defn run-query-fn [chapter exercise editors]
  (fn [event]
    (try
      (let [input-strings (map #(.getValue %) editors)
            input-data (map read-string input-strings)]
        (remote/get (format "/query/%s/%s" chapter exercise)
                     input-data
                     #(render-result exercise (first input-data) %)))
      (catch js/Error e
        (render-result exercise nil 
                       {:status :error 
                        :message (.-message e)})))))

(defn show-ans-fn [chapter exercise editors]
  (fn [e]
    (prevent-default e)
    (remote/get (format "/answer/%s/%s" chapter exercise)
                nil
                (fn [ans] 
                  (mapv #(.setValue %1 %2) editors ans)))))

(defn ^:export init [chapter ecount]
  (doseq [n (range ecount)]
    (let [button-id (str "#run-query-" n)
          input-class (str ".input-" n)
          editors (mapv #(.fromTextArea js/CodeMirror %)
                        (nodes (sel input-class)))
          show-answer-class (str ".show-ans-" n)]
      
      ;; Need to refresh the codemirror editors when tab is shown. I'd
      ;; rather do this with domina but "shown" is a bootstrap
      ;; specific event.
      (.on (js/$ "a[data-toggle=\"tab\"]") 
           "shown" 
           (fn [e] (mapv #(.refresh %) editors)))

      (listen! (sel button-id) :click
               (run-query-fn chapter n editors))
      (listen! (sel show-answer-class)  :click
               (show-ans-fn chapter n editors)))))
