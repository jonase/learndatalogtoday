# [learndatalogtoday](https://www.learndatalogtoday.org)

An interactive [Datalog tutorial](https://www.learndatalogtoday.org).

## Prerequisites

You will need [Leiningen](https://github.com/technomancy/leiningen) and java installed.

## Run locally

    $ lein uberjar
    $ java -cp target/learndatalogtoday-standalone.jar clojure.main -m learndatalogtoday.handler

Server is now running on `$PORT` (`http://localhost:8080` by default).

## License

Copyright © 2013-2023 Jonas Enlund

Distributed under the Eclipse Public License, the same as Clojure.
