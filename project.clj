(defproject learndatalogtoday "0.1.0-SNAPSHOT"
  :description "Interactive Datalog Tutorial"
  :url "http://learndatalogtoday.org"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [com.datomic/datomic-free "0.8.4007"]
                 [hiccup "1.0.3"]

                 ;; cljs
                 [hiccups "0.2.0"]
                 [domina "1.0.1"]]
  :plugins [[lein-ring "0.8.5"]
            [lein-cljsbuild "0.3.2"]]
  :source-paths ["src/clj"]
  :ring {:handler learndatalogtoday.handler/app}
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}}

  :cljsbuild {
    :builds [{
        :source-paths ["src/cljs"]
        :compiler {
          :output-to "resources/public/app.js"  
          :optimizations :whitespace
          :pretty-print true}}]})
