# Introduction to pet-owners

`pet-owners` is a simple Clojure project demonstrating how to use [Datomic](https://www.datomic.com/) for storing and querying pet owner data.

## Features

- Defines a Datomic schema for pet owners (with unique names).
- Provides functions to add a new pet owner.
- Provides functions to list all pet owners in the database.

## Example Usage

```clojure
(require '[pet-owners.core :as po])

;; Add a new pet owner
(po/add-pet-owner po/conn "amanda")

;; List all pet owners
(po/find-all-pet-owners po/conn)

## Getting Started
1. Start a local Datomic transactor and console (see the main README).
2. Run the REPL:
```sh
lein repl
```
3. Load the namespace and use the functions as shown above.

### Next Steps
* Extend the schema to include pets and their attributes.
* Add more queries (e.g., find owners by pet, etc.).
* Write tests for your functions.

For more details, see the README.