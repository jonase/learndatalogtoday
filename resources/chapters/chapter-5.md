# Predicates

So far, we have only been dealing with **data patterns**: 
`[?m :movie/year ?year]`. We have not yet seen a proper way of handling
queries like "*Find all movies released before 1984*". This is where
**predicate clauses** come into play.

Let's start with the query for the question above:

    [:find ?title
     :where
     [?m :movie/title ?title]
     [?m :movie/year ?year]
     [(< 1984 ?year)]]

The last clause, `[(< 1984 ?year)]`, is a predicate clause. The predicate clause filters the result set to only include results for which the predicate returns a "truthy" (non-nil, non-false) value. You can use any Clojure function or Java method as a predicate function:

    [:find ?name
     :where 
     [?p :person/name ?name]
     [(.startsWith ?name "M")]]