(defproject learndatalogtoday "0.1.0"
  :description "Interactive Datalog Tutorial"
  :url "http://learndatalogtoday.org"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [compojure "1.6.1"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [com.datomic/datomic-free "0.9.5697" :exclusions [com.google.guava/guava]]
                 [datomic-query-helpers "0.1.1"]
                 [hiccup "1.0.5"]
                 [markdown-clj "1.0.3"]
                 [fipp "0.6.13"]
                 [com.taoensso/timbre "4.10.0"]
                 ;; cljs
                 [hylla "0.2.0"]
                 [hiccups "0.3.0"]
                 [domina "1.0.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.4"]]
  :jvm-opts ["-XX:+IgnoreUnrecognizedVMOptions" "--add-modules java.xml.bind"]
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
