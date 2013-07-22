# Basic Queries

The example database we'll use contains *movies* mostly, but not
exclusively, from the 80s. There is information about movie titles,
release year, directors, cast members etc. As the tutorial advances
we'll learn more about the contents of the database and how that
content is organized, i.e., the database schema.

The data model in datomic is based around atomic facts called
**datoms**. A datom is a 5-tuple consisting of

* Entity-id 
* Attribute 
* Value
* Transaction-id
* Assert (true) or retract (false)

A complete datom could look something like

    [<e-id> :some/attribute "some value" <tx-id> true/false] 

Initially, we will not use the last two items (`tx-id` and
`assert/retract`) so you can think of the database as a 
**set of datoms** of the form

    ...
    [167 :person/name "James Cameron"]
    [234 :movie/title "Die Hard"]
    [234 :movie/year 1987]
    [235 :movie/title "Terminator"]
    [235 :movie/director 167]
    ...

Note that the last two datoms share the same entity id which means
they are facts about the same movie. Note also that the last datoms
value is the same as the first datoms entity-id, i.e., the value of
the `:movie/director` attribute is itself an entity.

A query is a vector starting with the keyword `:find` followed by one
or more **pattern variables** (symbols starting with `?`,
e.g. `?title`). After the find clause comes the `:where` clause which
restricts the query to datoms that match the given **data patterns**. 
For example, the query

    [:find ?e
     :where
     [?e :person/name "Ridley Scott"]]

finds all entity-ids that has the attribute `:person/name` with a value
of `"Ridley Scott"`. 

A data pattern is a datom with some parts replaced with pattern
variables. It is the job of the query engine to figure out every
possible value of the pattern variables and return the ones that are
specified in the `:find` clause.
