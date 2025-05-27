# Pet Owners: Running Datomic Locally

This guide will help you set up and run a Datomic environment locally for Clojure development.

---

## Prerequisites

Make sure you have the following installed:

- **Leiningen**: For Clojure project management ([Install Guide](https://leiningen.org/)).
- **Maven**: For dependency management (`brew install maven` on macOS).
- **Java**: Datomic requires Java 8 or 11 ([Adoptium](https://adoptium.net/)).

---

## Setup Steps

### 1. Create a New Project

```sh
lein new pet-owners
cd pet-owners
```

### 2. Download Datomic

Download the Datomic distribution (pro version) from the [official site](https://my.datomic.com/downloads/pro).

### 3. Unzip the Distribution

```sh
unzip datomic-pro-1.0.7364.zip
cd datomic-pro-1.0.7364
```

### 4. Start the Transactor

```sh
bin/transactor config/samples/dev-transactor-template.properties
```

### 5. Start the Console

```sh
bin/console -p 8888 dev datomic:dev://localhost:4334/pet-owners
```

### 6. Install the Datomic JAR in Local Maven Repo

Replace the path with your actual location:

```sh
mvn install:install-file \
  -Dfile=/Users/youruser/Downloads/datomic-pro-1.0.7364/datomic-transactor-pro-1.0.7364.jar \
  -DgroupId=com.datomic \
  -DartifactId=datomic-pro \
  -Dversion=1.0.7364 \
  -Dpackaging=jar
```

### 7. Add Dependencies

Edit your `project.clj`:

```clojure
:dependencies [[org.clojure/clojure "1.11.1"]
               [com.datomic/peer "1.0.7364"]]
```

### 8. Download Dependencies

```sh
lein deps
```

### 9. Start the REPL

```sh
lein repl
```

---

## Notes

- **.gitignore**: Make sure to ignore build artifacts, IDE files, and secrets. See the included `.gitignore` for suggestions.
- **Datomic License**: Datomic Pro requires a valid license. Place your `license-key` in the transactor properties if needed.
- **Console Access**: The Datomic console will be available at [http://localhost:8888](http://localhost:8888).

---

You are now ready to develop with Datomic locally. Enjoy!