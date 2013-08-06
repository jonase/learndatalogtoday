# Aggregates

Aggregate functions such as `sum`, `max` etc. are readily available in Datomic's Datalog implementation. They are written in the `:find` clause in your query:

    [:find (max ?date)
     :where
     ...]

An aggregate function collects values from multiple datoms and returns

* A single value: `min`, `max`, `sum`, `avg`, etc.
* A collection of values: `(min n ?d)` `(max n ?d)` `(sample n ?e)` etc. where `n` is an integer specifying the size of the collection. 
