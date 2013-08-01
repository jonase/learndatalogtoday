(ns tutorial.fns
  (:import [java.util Calendar Date]))

(defn date-vector 
  "Returns the vector [year month day]"
  [date]
  (let [c (Calendar/getInstance)]
    (.setTime c date)
    [(.get c Calendar/YEAR)
     (inc (.get c Calendar/MONTH))
     (.get c Calendar/DAY_OF_MONTH)]))

(defn age 
  "Very crude."
  [^Date birthday ^Date today]
  (quot (- (.getTime today)
           (.getTime birthday))
        (* 1000 60 60 24 365)))
