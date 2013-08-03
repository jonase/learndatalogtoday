# learndatalogtoday

An interactive datalog tutorial

## Prerequisites

You will need [Leiningen](https://github.com/technomancy/leiningen) installed.

## Bootstrapping

    $ git clone git@github.com:jonase/learndatalogtoday.git
    $ cd learndatalogtoday
    $ ./fetch-js-deps.sh
    $ lein cljsbuild clean && lein cljsbuild once

## Running the webapp

    $ lein ring server

A browser window should open, otherwise visit http://localhost:3000.

## Feedback welcome

You can open issues on the github issue tracker or you can email me
(either via github or gmail) your suggestions/bugs/typos/feedback/etc.

At this point in the project I'd ask you to

* not push any code to the repo even if you are a collaborator and have that power.
* don't open pull requests since I won't accept them.

This is simply because I'm considering participating in the [Lisp In
Summer Projects](http://lispinsummerprojects.org) competition with
this project and your commits might disqualify the project.

## License

Copyright © 2013 Jonas Enlund

Distributed under the Eclipse Public License, the same as Clojure.
