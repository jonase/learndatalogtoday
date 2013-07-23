(ns learndatalogtoday.query)

(defn- normalize-query* [query result]
  (if (seq query)
    (let [key (first query)
          [val rest] (split-with (complement keyword?) (rest query))]
      (recur rest (assoc result key val)))
    result))

(defn normalize [query]
  (if (map? query) 
    query
    (normalize-query* query {})))

(defn find-clause [query]
  (:find (normalize query)))

