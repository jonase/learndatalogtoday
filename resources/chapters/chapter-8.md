# Rules

Many times over the course of this tutorial, we have had to write the
following three lines of repetitive query code:

    [?p :person/name ?name]
    [?m :movie/cast ?p]
    [?m :movie/title ?title]

**Rules** are the means of abstraction in Datalog. You can abstract
away reusable parts of your queries into rules, give them meaningful
names and forget about the implementation details, just like you can with functions
in your favorite programming language. Let's create a rule for the three lines above:

    [(actor-movie ?name ?title)
     [?p :person/name ?name]
     [?m :movie/cast ?p]
     [?m :movie/title ?title]]

The first vector element is called the *head* of the rule where the first
symbol is the name of the rule. The rest of the rule is called the body. 

It is possible to use `(...)` or `[...]` to enclose it, but it is conventional to use `(...)` to aid the eye when distinguishing between the rule's head and its body, and also between rule invocations and normal data patterns, as we'll see below.

You can think of a rule as a kind of function, but remember that this
is logic programming, so we can use the same rule to:

* find movie titles given an actor name, and
* find actor names given a movie title.

Put another way, we can use both `?name` and `?title` in `(actor-movie ?name ?title)` for input as well as for output. If we provide values for neither, we'll get all the possible combinations in the database. If we provide values for one or both, it'll constrain the result returned by the query as you'd expect.

To use the above rule, you simply write the head of the rule instead of the data patterns. Any variable with values already bound will be input, the rest will be output.

The query to find cast members of some movie,
for which we previously had to write:

    [:find ?name
     :where
     [?p :person/name ?name]
     [?m :movie/cast ?p]
     [?m :movie/title "The Terminator"]]

Now becomes:

    [:find ?name
     :in $ %
     (actor-movie ?name "The Terminator")]

The `%` symbol in the `:in` clause represent the rules. You can write
any number of rules, collect them in a vector, and pass them
to the query engine like any other input:

    [[(rule-a ?a ?b)
      ...]
     [(rule-b ?a ?b)
      ...]
     ...]

You can use [data patterns](/chapter/2), [predicates](/chapter/5),
[transformation functions](/chapter/6) and calls to other rules in the body of
a rule.

Rules can also be used as another tool to write logical OR queries, as the
same rule name can be used several times:

    [[(associated-with ?person ?movie)
      [?movie :movie/cast ?person]]
     [(associated-with ?person ?movie)
      [?movie :movie/director ?person]]]

Subsequent rule definitions will only be used if the ones preceding it aren't satisfied.

Using this rule, we can find both directors and cast members very easily:

    [:find ?name
     :in $ %
     :where
     [?m :movie/name "Predator"]
     (associated-with ?p ?m)
     [?p :person/name ?name]]

Given the fact that rules can contain calls to other rules, what would
happen if a rule called itself? Interesting things, it turns out, but
let's find out in the exercises.

