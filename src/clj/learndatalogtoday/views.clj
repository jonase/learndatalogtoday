(ns learndatalogtoday.views
  (:require [hiccup.page :refer [include-js include-css]]
            [hiccup.element :refer [javascript-tag]]
            [markdown.core :as md]))

(defn base [lhs text exercises]
  [:html
   [:head
    (include-css "/third-party/bootstrap/css/bootstrap.css")
    (include-css "/third-party/codemirror-3.14/lib/codemirror.css")
    (include-css "/style.css")
    [:title "Learn Datalog Today!"]]
   [:body
    [:div.container
     [:div.row
      [:div.span3 
       [:ul.nav.nav-tabs.nav-stacked {:style "margin-top: 20px"} lhs]]
      [:div.span9
       [:div.content
        [:div {:style "text-align:justify"} text]
        [:div exercises]]
       [:footer.text-center {:style "border-top: 1px solid lightgrey; margin-top: 40px;padding:10px;"}
        [:small
         [:p [:a {:href "#"} "learndatalogtoday.org"] " &copy; 2013 Jonas Enlund" 
          [:br] 
          [:a {:href "#"} "github"] " | " 
          [:a {:href "#"} "lispinsummerprojects.org"] " | " 
          [:a {:href "#"} "about"] " | "
          [:a {:href "#"} "license"]]]]]]]
    (include-js "/third-party/jquery/jquery-1.10.1.min.js")
    (include-js "/third-party/codemirror-3.14/lib/codemirror.js")
    (include-js "/third-party/codemirror-3.14/mode/clojure/clojure.js")
    (include-js "/third-party/bootstrap/js/bootstrap.js")
    (include-js "/third-party/pagedown/Markdown.Converter.js")
    (include-js "/app.js")
    (javascript-tag "goog.require('learndatalogtoday.core')")
    (javascript-tag "learndatalogtoday.core.init();")]])

(defn build-input [tab-n input-n input]
  (let [label (condp = (:type input)
                :query "Query:"
                :rule "Rules:"
                :value (str "Input #" input-n ":"))]
    (list [:div.span2.text-right [:strong label]]
          [:div.span7 [:textarea {:class (str "tab-" tab-n)} (:value input)]])))

(defn build-inputs [tab-n inputs]
  (map-indexed (partial build-input tab-n) inputs))

(defn build-exercise [tab-n exercise]
  (list [:div.tab-pane {:id (str "tab" tab-n)}
         [:p (md/md-to-html-string (:question exercise))]
         [:div.row.inputs
          (build-inputs tab-n (:inputs exercise))]
         [:div.row
          [:div.span7.offset2
           [:button.btn.btn-block {:data-tab tab-n} "Run Query"]]]]))

(defn build-exercises [exercises]
  (list [:div.tabbable
         [:ul.nav.nav-tabs
          (for [n (range (count exercises))]
            [:li [:a {:href (str "#tab" n) 
                      :data-toggle "tab"}
                  [:span.label n]]])]
         [:div.tab-content
          (map-indexed build-exercise exercises)]]))

(defn chapter [chapters n]
  (let [chapter (chapters n)
        lhs (map-indexed (fn [i ch]
                           [:li (when (= i n) {:class "active"}) 
                            [:a {:href (str "/chapter/" i)} [:small (:title ch)]]])
                         chapters)
        exercises (build-exercises (:exercises chapter))
        text (md/md-to-html-string (:text chapter))]
    (base lhs text exercises)))