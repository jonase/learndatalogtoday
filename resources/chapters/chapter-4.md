# More queries

A datom, as described earlier, is the 4-tuple `[eid attr val tx]`. So far, we have only asked questions about values and/or entity-ids. It's important to remember that it's also possible to ask questions about attributes and transactions.

## Attributes 

For example, say we want to find all attributes that are associated with person entities in our database. We know for certain that `:person/name` is one such attribute but are there others we have not yet seen?

    [:find ?attr
     :where 
     [?p :person/name]
     [?p ?attr]]

The above query returns a set of entity ids referring to the attributes we are interested in. To get the actual keywords we need to look them up using the `:db/ident` attribute:

    [:find ?attr
     :where
     [?p :person/name]
     [?p ?a]
     [?a :db/ident ?attr]]

## Transactions

It's also possible to run queries to find information about transactions, such as

* When was a fact asserted?
* What facts were part of a transaction?
* When did this transaction run?
* etc.

The transaction entity is the fourth element in the datom vector. The only attribute associated with a transaction (by default) is `:db/txInstant` which is the instant in time when the transaction was committed to the database.
