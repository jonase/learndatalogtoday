(ns learndatalogtoday.handler
  (:require [learndatalogtoday.views :as views]
            [compojure.core :refer [routes GET]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [datomic.api :as d]
            [hiccup.page :refer [html5]]))

(defn edn-response [edn-data]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str edn-data)})

(defn app-routes [db chapters]
  (routes
   (GET "/" [] (html5 (views/base)))
   
   (GET "/chapters"
     []
     (edn-response chapters))
   
   (GET "/exercises/:n" 
     [n] 
     (slurp (str "resources/chapters/chapter-1.edn")))
   
   (route/resources "/")
   (route/not-found "Not Found")))

(defn init-db [name schema seed-data]
  (let [uri (str "datomic:mem://" name)
        conn (do (d/delete-database uri)
                 (d/create-database uri)
                 (d/connect uri))]
    @(d/transact conn schema)
    @(d/transact conn seed-data)
    (d/db conn)))

(defn read-file [s]
  (read-string (slurp s)))

(def app
  (let [schema (read-file "resources/db/schema.edn")
        seed-data (read-file "resources/db/data.edn")
        db (init-db "movies" schema seed-data)
        chapters (mapv read-file ["resources/chapters/chapter-0.edn"
                                  "resources/chapters/chapter-1.edn"
                                  "resources/chapters/chapter-2.edn"
                                  "resources/chapters/chapter-3.edn"])] 
    (handler/site (app-routes db chapters))))
