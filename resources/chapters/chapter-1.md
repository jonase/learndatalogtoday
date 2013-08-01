# Basic Queries

The example database we'll use contains *movies* mostly, but not
exclusively, from the 80s. You'll find information about movie titles,
release year, directors, cast members etc. As the tutorial advances
we'll learn more about the contents of the database and how it's organized.

The data model in datomic is based around atomic facts called
**datoms**. A datom is a 4-tuple consisting of

* Entity-id 
* Attribute 
* Value
* Transaction-id

You can think of the database as a flat **set of datoms** of the form

    [<e-id>  <attribute>      <value>          <tx-id>]
    ...
    [ 167    :person/name     "James Cameron"    102  ]
    [ 234    :movie/title     "Die Hard"         102  ]
    [ 234    :movie/year      1987               102  ]
    [ 235    :movie/title     "Terminator"       102  ]
    [ 235    :movie/director  167                102  ]
    ...

Note that the last two datoms share the same entity id which means
they are facts about the same movie. Note also that the last datoms
value is the same as the first datoms entity-id, i.e., the value of
the `:movie/director` attribute is itself an entity. All the datoms in
the above set were added to the database in the same transaction
because they share the same transaction id.

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
specified in the `:find` clause. The symbol `_` can be used as a
wildcard for the parts in the data pattern you wish to ignore. You can
also elide trailing values in a data pattern. Therefor, the above
query is equivalent to

    [:find ?e
     :where
     [?e :person/name "Ridley Scott" _]]

because we ignore the transaction part of the datoms.

