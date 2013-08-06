# Data patterns

In the previous chapter, we looked at **data patterns**, i.e., vectors
after the `:where` clause, such as `[?e :movie/title "Commando"]`. 
There can be many data patterns in a `:where` clause:

    [:find ?title
     :where
     [?e :movie/year 1987]
     [?e :movie/title ?title]]

The important thing to note here is that the pattern variable `?e` is
used in both data patterns. When a pattern variable is used in
multiple places, the query engine requires it to be bound to the same
value in each place. Therefore, this query will only find movie titles
for movies made in 1987.

The order of the data patterns does not matter (aside from performance 
considerations), so the previous query could just as well have been written this way:

    [:find ?title
     :where
     [?e :movie/title ?title]
     [?e :movie/year 1987]]

In both cases, the result set will be exactly the same.

Let's say we want to find out who starred in "Lethal Weapon". We
will need three data patterns for this. The first one finds the
entity ID of the movie with "Lethal Weapon" as the title:

    [?m :movie/title "Lethal Weapon"]

Using the same entity ID at `?m`, we can find the cast members with the data
pattern:

    [?m :movie/cast ?p] 

In this pattern, `?p` will now be (the entity ID of) a person entity, so we can grab the
actual name with:

    [?p :person/name ?name] 

The query will therefore be:

    [:find ?name
     :where
     [?m :movie/title "Lethal Weapon"]
     [?m :movie/cast ?p]
     [?p :person/name ?name]]
