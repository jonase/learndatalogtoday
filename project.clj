(defproject learndatalogtoday "0.1.0-SNAPSHOT"
  :description "Interactive Datalog Tutorial"
  :url "http://learndatalogtoday.org"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [compojure "1.1.5"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [com.datomic/datomic-free "0.9.5394"]
                 [datomic-query-helpers "0.1.1"]
                 [hiccup "1.0.4"]
                 [markdown-clj "0.9.29"]
                 [fipp "0.4.0"]
                 [com.taoensso/timbre "2.4.1"]
                 ;; cljs
                 [hylla "0.2.0"]
                 [hiccups "0.2.0"]
                 [domina "1.0.1"]]
  :plugins [[lein-ring "0.8.6"]
            [lein-cljsbuild "1.1.1"]]
  :source-paths ["src/clj"]
  :ring {:handler learndatalogtoday.handler/app}
  :main learndatalogtoday.handler
  :uberjar-name "learndatalogtoday-standalone.jar"
  :min-lein-version "2.0.0"
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}}
  :cljsbuild {
              :builds [{
                        :source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/app.js"
                                   :optimizations :advanced
                                   :externs ["externs.js"]
                                   :static-fns true}

                        #_{
                           :output-to "resources/public/app.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
