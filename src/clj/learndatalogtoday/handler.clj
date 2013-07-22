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

(declare read-chapter)

(defn app-routes [db chapters]
  (routes
   (GET "/" [])
   
   (GET "/chapters"
     []
     (edn-response chapters))

   (GET "/chapter/:n"
     [n]
     #_(chapters (Integer/parseInt n))
     ;; For fast dev
     (read-chapter (Integer/parseInt n))
     )
   
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

(defn read-chapter 
  "Returns a html string"
  [chapter]
  (let [chapter-data (->> chapter
                          (format "resources/chapters/chapter-%s.edn") 
                          read-file)]
    (views/chapter-response (assoc chapter-data
                              :chapter chapter))))

(def app
  (let [schema (read-file "resources/db/schema.edn")
        seed-data (read-file "resources/db/data.edn")
        db (init-db "movies" schema seed-data)
        chapters (mapv read-chapter [0])] 
    (handler/site (app-routes db chapters))))
