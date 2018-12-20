# Parameterized queries

Looking at this query:

    [:find ?title
     :where
     [?p :person/name "Sylvester Stallone"]
     [?m :movie/cast ?p]
     [?m :movie/title ?title]]

It would be great if we could reuse this query to find movie
titles for any actor and not just for "Sylvester Stallone". This is
possible with an `:in` clause, which provides the query with input
parameters, much in the same way that function or method arguments does
in your programming language.

Here's that query with an input parameter for the actor:

    [:find ?title
     :in $ ?name
     :where
     [?p :person/name ?name]
     [?m :movie/cast ?p]
     [?m :movie/title ?title]]

This query takes two arguments: `$` is the database itself (implicit,
if no `:in` clause is specified) and `?name` which presumably will be
the name of some actor.

The above query is executed like `(q query db "Sylvester Stallone")`,
where `query` is the query we just saw, and `db` is a database value.
You can have any number of inputs to a query.

In the above query, the input pattern variable `?name` is bound to a
scalar - a string in this case. There are four different kinds of
input: scalars, tuples, collections and relations.

## A quick aside

Hold on. Where does that `$` get used? In query, each of these data
patterns is actually a **5 tuple**, of the form:

    [<database> <entity-id> <attribute> <value> <transaction-id>]

It's just that the `database` part is implicit, much like the first
parameter in the `:in` clause. This query is functionally identical
to the previous one:

    [:find ?title
     :in $ ?name
     :where
     [$ ?p :person/name ?name]
     [$ ?m :movie/cast ?p]
     [$ ?m :movie/title ?title]]

## Tuples

A tuple input is written as e.g. `[?name ?age]` and can be used when
you want to destructure an input. Let's say you have the vector
`["James Cameron" "Arnold Schwarzenegger"]` and you want to use this
as input to find all movies where these two people collaborated:

    [:find ?title
     :in $ [?director ?actor]
     :where
     [?d :person/name ?director]
     [?a :person/name ?actor]
     [?m :movie/director ?d]
     [?m :movie/cast ?a]
     [?m :movie/title ?title]]

Of course, in this case, you could just as well use two distinct inputs instead:

    :in $ ?director ?actor

## Collections

You can use collection destructuring to implement a kind of logical **or** in your query. Say you want to find all movies directed by either James Cameron **or** Ridley Scott. You would use the vector `["James Cameron" "Ridley Scott"]` as input to the following query:

    [:find ?title
     :in $ [?director ...]
     :where
     [?p :person/name ?director]
     [?m :movie/director ?p]
     [?m :movie/title ?title]]

Here, the `?director` pattern variable is initially bound to both "James Cameron" and "Ridley Scott". Note that the ellipsis following `?director` is a literal, not elided code.

## Relations

Relations - a set of tuples - are the most interesting and powerful of
input types, since you can join external relations with the datoms in
your database.

As a simple example, let's consider a relation with tuples `[movie-title box-office-earnings]`:

    [
     ...
     ["Die Hard" 140700000]
     ["Alien" 104931801]
     ["Lethal Weapon" 120207127]
     ["Commando" 57491000]
     ...
    ]

Let's use this data and the data in our database to find
box office earnings for a particular director:

    [:find ?title ?box-office
     :in $ ?director [[?title ?box-office]]
     :where
     [?p :person/name ?director]
     [?m :movie/director ?p]
     [?m :movie/title ?title]]

Note that the `?box-office` pattern variable does not
appear in any of the data patterns in the `:where` clause.
