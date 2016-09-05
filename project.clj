(defproject learndatalogtoday "0.1.0"
  :description "Interactive Datalog Tutorial"
  :url "http://learndatalogtoday.org"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.227"]
                 [compojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [com.datomic/datomic-free "0.9.5394"]
                 [datomic-query-helpers "0.1.1"]
                 [hiccup "1.0.5"]
                 [markdown-clj "0.9.89"]
                 [fipp "0.6.6"]
                 [com.taoensso/timbre "4.7.4"]
                 ;; cljs
                 [hylla "0.2.0"]
                 [hiccups "0.3.0"]
                 [domina "1.0.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.4"]]
  :source-paths ["src/clj"]
  :ring {:handler learndatalogtoday.handler/app}
  :main learndatalogtoday.handler
  :uberjar-name "learndatalogtoday-standalone.jar"
  :min-lein-version "2.0.0"
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}}
  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/app.js"
                                   :optimizations :advanced
                                   :externs ["externs.js"]
                                   :static-fns true}}]})
