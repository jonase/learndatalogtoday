# Welcome!

**Learn Datalog Today** is an interactive tutorial designed to teach
you the [datomic](http://datomic.com) dialect of
[datalog](http://en.wikipedia.org/wiki/Datalog).  Datalog is a
*database query language* with roots in logic programming. Datalog has
similar expressive power as [SQL](http://en.wikipedia.org/wiki/SQL).

Datomic is a new database with an interesting and novel architecture
giving its users a unique set of features. You can read more about
datomic at [http://datomic.com](http://datomic.com) and the
architecture is described in some detail
[in this infoq article](http://infoq.com).

In datomic, a datalog query is written in
[EDN](http://edn-format.org). EDN is a data format similar to JSON but:

* is extensible
* has more base types
* is a subset of [Clojure] (http://clojure.org) data

EDN consists of

* Numbers: `42`, `3.14159`
* Strings: `"This is a string"`
* Keywords: `:kw`, `:namespaced/keyword`, `:foo.bar/baz`
* Symbols: `max`, `+`, `?title`
* Vectors: `[1 2 3]` `[:find ?foo ...]`
* Lists: `(3.14 :foo [:bar :baz])`, `(+ 1 2 3 4)`
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

We'll go over the specific parts of the query later but for now you 
should simply type the above query verbatim into the textbox below,
press **Run Query** and continue to the next part of the tutorial.