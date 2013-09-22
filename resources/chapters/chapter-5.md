# Predicates

So far, we have only been dealing with **data patterns**: 
`[?m :movie/year ?year]`. We have not yet seen a proper way of handling
questions like "*Find all movies released before 1984*". This is where
**predicate clauses** come into play.

Let's start with the query for the question above:

    [:find ?title
     :where
     [?m :movie/title ?title]
     [?m :movie/year ?year]
     [(< ?year 1984)]]

The last clause, `[(< ?year 1984)]`, is a predicate clause. The
predicate clause filters the result set to only include results for
which the predicate returns a "truthy" (non-nil, non-false) value. You
can use any Clojure function or Java method as a predicate function:

    [:find ?name
     :where 
     [?p :person/name ?name]
     [(.startsWith ?name "M")]]

Clojure functions must be fully namespace-qualified, so if you have
defined your own predicate `awesome?` you must write it as
`(my.namespace/awesome? ?movie)`. Some ubiquitous predicates can be
used without namespace qualification: `<, >, <=, >=, =, not=` and so on.
