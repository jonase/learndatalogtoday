# Transformation functions

**Transformation functions** are functional transforms which can be
used in queries to transform values and bind their results to pattern
variables. Say, for example, there exists an attribute `:person/birth`
with type `:db.type/instant`. Given the birthday it's easy to
calculate the (very approximate) age of a person:

    (defn age [birthday today]
      (/ (- (.getTime today)
            (.getTime birthday))
         (* 1000 60 60 24 365))
         
with this function in hand we can now calculate the age of a person

    [:find ?age
     :in $ ?person ?today
     :where
     [?p :person/name ?name]
     [?p :person/birth ?birth]
     [(age ?birth ?today) ?age]]  

A transformation function clause has the shape `[(<fn> <arg1> <arg2> ...) <binding>]` where `<binding>` can be the same binding forms as we saw in [chapter 3](/chapter/3):

* Scalar: `?age`
* Tuple: `[?foo ?bar ?baz]`
* Collection: `[?name ...]`
* Relation: `[[?title ?rating]]`

One thing to be aware of is that transformation functions can't be nested. You can't for example do

    [(f (g ?x)) ?a]

instead, you must bind intermediate results in temporary pattern variables

    [(g ?x) ?t]
    [(f ?t) ?a]



