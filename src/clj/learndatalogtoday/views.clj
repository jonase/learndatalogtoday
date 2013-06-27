(ns learndatalogtoday.views
  (:require [hiccup.page :refer [include-js include-css]]
            [hiccup.element :refer [javascript-tag]]))

(defn head []
  [:head
   (include-css "/third-party/bootstrap/css/bootstrap.css")
   (include-css "/third-party/codemirror-3.14/lib/codemirror.css")
   (include-css "style.css")
   [:title "Learn Datalog Today!"]])

(defn base []
  [:html
   (head)
   [:body
    [:div.container
     [:div.row
      [:div.span3 
       [:ul.nav.nav-tabs.nav-stacked {:style "margin-top: 20px"}
        [:li [:a [:small "Welcome!"]]]
        [:li [:a [:small "Basic Queries"]]]
        [:li.active [:a [:small "Data Patterns"]]]
        [:li [:a [:small "Parameterized Queries"]]]
        [:li [:a [:small "More Queries"]]]
        [:li [:a [:small "Predicates"]]]
        [:li [:a [:small "Transformation Functions"]]]
        [:li [:a [:small "Aggregates"]]]
        [:li [:a [:small "Rules"]]]
        [:li [:a [:small "Querying Multiple Databases"]]]
        [:li [:a [:small "The End"]]]]]
      [:div.span9
       [:div#text-content {:style "text-align:justify"}]
       [:div#exercises-content]
       [:div {:style "margin-top: 10px"} [:span [:a {:href "#"} "<< prev"]] [:span.pull-right [:a {:href "#"} "next >>"]]]
       [:footer.text-center {:style "border-top: 1px solid lightgrey; margin-top: 40px;padding:10px;"}
        [:small
         [:p [:a {:href "#"} "learndatalogtoday.org"] " &copy; 2013 Jonas Enlund" 
          [:br] 
          [:a {:href "#"} "github"] " | " 
          [:a {:href "#"} "lispinsummerprojects.org"] " | " 
          [:a {:href "#"} "about"] " | "
          [:a {:href "#"} "license"]]]]]
      ]]
    (include-js "/third-party/jquery/jquery-1.10.1.min.js")
    (include-js "/third-party/codemirror-3.14/lib/codemirror.js")
    (include-js "/third-party/codemirror-3.14/mode/clojure/clojure.js")
    (include-js "/third-party/bootstrap/js/bootstrap.js")
    (include-js "/third-party/pagedown/Markdown.Converter.js")
    (include-js "/app.js")
    (javascript-tag "goog.require('learndatalogtoday.core')")
    (javascript-tag "learndatalogtoday.core.init();")]])
