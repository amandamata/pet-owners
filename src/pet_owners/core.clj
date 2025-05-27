(ns pet-owners.core
  (:require [datomic.api :as d]))

(def pet-owner-schema
  [{:db/ident :pet-owner/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity
    :db/doc "A pet owner's name"
    :db.install/_attribute :db.part/db}])

(def uri "datomic:dev://localhost:4334/pet-owners")

(d/create-database uri)

(def conn (d/connect uri))

@(d/transact conn pet-owner-schema)

(defn add-pet-owner [conn name]
  @(d/transact conn [{:pet-owner/name name}]))

(defn find-all-pet-owners [conn]
  (let [db (d/db conn)]
    (map first
         (d/q '[:find ?name
                :where [?e :pet-owner/name ?name]]
              db))))

(comment
  (add-pet-owner conn "amanda")
  (find-all-pet-owners conn)
  )

