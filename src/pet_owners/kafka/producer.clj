(ns pet-owners.kafka.producer
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]
           [org.apache.kafka.common.serialization StringSerializer]))

(def config (edn/read-string (slurp (io/resource "pet_owners/config/kafka.edn"))))

(defn create-producer []
  (KafkaProducer.
   {"bootstrap.servers" (:bootstrap-servers config)
    "key.serializer" StringSerializer
    "value.serializer" StringSerializer}))

(defn send-message [producer topic key value]
  (.send producer (ProducerRecord. topic key value)))

(defn publish-owner [owner-name]
  (with-open [producer (create-producer)]
    (send-message producer
                  (:topic-name config)
                  owner-name
                  (str "New pet owner registered: " owner-name))))
