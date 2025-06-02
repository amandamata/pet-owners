(ns pet-owners.core
  (:require [datomic.api :as d])
  (:require [pet-owners.kafka.consumer :as consumer])
  (:require [pet-owners.kafka.producer :as producer]))

(def uri "datomic:dev://localhost:4334/pet-owners")

(d/delete-database uri)
(d/create-database uri)

(def conn (d/connect uri))

(let [schema (load-file "resources/datomic/schemas.edn")]
  @(d/transact conn schema))

(defn add-pet-owner [conn name]
  (let [tx @(d/transact conn [{:owner/name name}])]
    (println "Transaction result:" tx)
    (producer/publish-owner name)))

(defn find-all-pet-owners [conn]
  (let [db (d/db conn)]
    (map first
         (d/q '[:find ?name
                :where [?e :owner/name ?name]]
              db))))
(comment
  (add-pet-owner conn "amanda")'
  (find-all-pet-owners conn)
  )


(comment 
  (consumer/start-consumer)
)
