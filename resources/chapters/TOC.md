* Chapter 0: Introduction
  - Welcome
  - Datalog
  - Datomic
  - EDN
  - Copy/Paste a query as a simple exercise

* Chapter 1: Basic Queries
  - About the example database
  - The Datalog data model
  - Queries as edn data
  - Example query
  - Wildcard pattern / elide values on right
  - Explenation of pattern variables and data patterns 
  - 2-3 exercises
    * Find entity ids of movies made in 1987
    * Find all movie names
    * Find the entity id's of all the directors
    
* Chapter 2: Data patterns
  - Joins are implicit
  - Simple example
  - A second example
  - At least 4 exercises

* Chapter 3: Input, part 1
  - Leave $ and % for later
  - do all of 
    * scalars: ?title 
    * tuples: [?title ?name] 
    * collections: [?name ...] 
    * relations: [ [?title ?score] ]
  - Exercises for all of the above
    * Given a movie director, list all his movies.
    * Given two actors, list all movies they've performed in together.
    * Given a list of movie title, list all the title and the year the movie was released.
    
* Chapter 4: More queries
  - query for attributes
  - query for transaction
  - ~3 examples
    * When was the sample data imported?
    * What attributes are associated with the movie "Die Hard"
    * Find all available attributes and there type.

* Chapter 5: Predicates
  - ~3 exercises
    * Find movies older than ?year
    * Find movies older than ?title

* Chapter 6: Transformation functions
  - Exercises
    * Given a namespace (as a string) Find all attributes, types and cardinality

* Chapter 7: Aggregates

* Chapter 8: Rules

* Chapter 9: Querying multiple databases 

* Chapter 10: The End.
