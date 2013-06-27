(ns learndatalogtoday.remote
  (:refer-clojure :exclude [get])
  (:require [cljs.reader :as r]
            [goog.net.XhrIo :as XhrIo]
            [goog.Uri.QueryData :as QueryData]))

(defn url-encode [data]
  (QueryData/createFromKeysValues
   (array "data")
   (array (pr-str data))))

(defn send [url & {:keys [data callback method timeout]
                   :or {callback identity
                        method :get}}]
  (XhrIo/send url
              (fn [e]
                (-> e .-target .getResponseText r/read-string callback))
              (.toUpperCase (name method))
              (url-encode data)))

(defn get 
  ([url]
     (get url (constantly nil)))
  ([url callback]
     (get url nil callback))
  ([url data callback]
     (send url :method :get :data data :callback callback)))

(defn post
  ([url]
     (get url (constantly nil)))
  ([url callback]
     (get url nil callback))
  ([url data callback]
     (send url :method :post :data data :callback callback)))
