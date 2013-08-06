# Extensible Data Notation

In Datomic, a Datalog query is written in
[extensible data notation (edn)](http://edn-format.org). Edn is a data format similar to JSON, but it:

* is extensible with user defined value types,
* has more base types,
* is a subset of [Clojure](http://clojure.org) data.

Edn consists of:

* Numbers: `42`, `3.14159`
* Strings: `"This is a string"`
* Keywords: `:kw`, `:namespaced/keyword`, `:foo.bar/baz`
* Symbols: `max`, `+`, `?title`
* Vectors: `[1 2 3]` `[:find ?foo ...]`
* Lists: `(3.14 :foo [:bar :baz])`, `(+ 1 2 3 4)`
* Instants: `#inst "2013-02-26"`
* .. and a few other things which we will not need in this tutorial.

Here is an example query that finds all movie titles in our example database:

    [:find ?title
     :where 
     [_ :movie/title ?title]]

Note that the query is a vector with four elements:

* the keyword `:find`
* the symbol `?title`
* the keyword `:where`
* the vector `[_ :movie/title ?title]`

We'll go over the specific parts of the query later, but for now you 
should simply type the above query verbatim into the textbox below,
press **Run Query**, and then continue to the next part of the tutorial.
