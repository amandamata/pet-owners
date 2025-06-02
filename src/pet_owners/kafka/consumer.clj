(ns pet-owners.kafka.consumer
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:import [org.apache.kafka.clients.consumer KafkaConsumer]
           [org.apache.kafka.common.serialization StringDeserializer]
           [java.time Duration]))

(def config (edn/read-string (slurp (io/resource "pet_owners/config/kafka.edn"))))

(defn create-consumer []
  (KafkaConsumer.
   {"bootstrap.servers" (:bootstrap-servers config)
    "group.id" (:group-id config)
    "auto.offset.reset" (:auto-offset-reset config)
    "key.deserializer" StringDeserializer
    "value.deserializer" StringDeserializer}))

(defn start-consumer []
  (let [consumer (create-consumer)]
    (.subscribe consumer [(:topic-name config)])
    (while true
      (let [records (.poll consumer (Duration/ofMillis 100))]
        (doseq [record records]
          (println "Received message:"
                   "\nKey:" (.key record)
                   "\nValue:" (.value record)
                   "\nTimestamp:" (.timestamp record)))))))
