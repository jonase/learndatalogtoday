# learndatalogtoday

An interactive datalog tutorial. Up and running on [http://www.learndatalogtoday.org](http://www.learndatalogtoday.org)

## Prerequisites

You will need [Leiningen](https://github.com/technomancy/leiningen) installed.

## Bootstrapping

    $ git clone git@github.com:jonase/learndatalogtoday.git
    $ cd learndatalogtoday
    $ git fetch origin
    $ git checkout -b lisp origin/lisp
    $ ./fetch-js-deps.sh
    $ lein cljsbuild clean && lein cljsbuild once

## Running the webapp

    $ lein ring server

A browser window should open, otherwise visit http://localhost:3000.

## Know bugs

This version will only work with google chrome. The version running on
http://www.learndatalogtoday.org runs fine on most browsers. The
latest version is on the master branch.

## Feedback welcome

You can open issues on the github issue tracker or you can email me your suggestions/bugs/typos/feedback/etc. Pull requests welcome!

## License

Copyright © 2013 Jonas Enlund

Distributed under the Eclipse Public License, the same as Clojure.
