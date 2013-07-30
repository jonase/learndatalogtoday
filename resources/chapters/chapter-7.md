# Aggregates

Aggregate functions such as `sum`, `max` etc. are readily available in Datomics datalog implementation. They are written in the `:find` clause in your query:

    [:find (max ?date)
     :where
     ...]



