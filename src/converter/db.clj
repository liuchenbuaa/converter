(ns converter.db
  (:import com.mchange.v2.c3p0.ComboPooledDataSource
           com.jolbox.bonecp.BoneCPDataSource)
  (:require [clojure.java.jdbc :as jdbc]))

(defn pool-c3p0
  []
  (let [cpds (doto (ComboPooledDataSource.)
               (.setDriverClass nil)
               (.setJdbcUrl "jdbc:mysql://127.0.0.1/test123?characterEncoding=UTF-8")
               (.setUser "demo")
               (.setPassword "demo")
               ;; expire excess connections after 30 minutes of inactivity:
               (.setMaxIdleTimeExcessConnections (* 30 60))
               ;; expire connections after 3 hours of inactivity:
               (.setMaxIdleTime (* 3 60 60)))]
    {:datasource cpds}))

(defn pool-bonecp
  []
  (let [partitions 3
        cpds (doto (BoneCPDataSource.)
               (.setJdbcUrl "jdbc:mysql://localhost:3306/test123?characterEncoding=UTF-8")
               (.setUsername "demo")
               (.setPassword "demo")
                                        ;(.setMinConnectionsPerPartition (inc (int (/ min-pool partitions))))
                                        ;(.setMaxConnectionsPerPartition (inc (int (/ max-pool partitions))))
               (.setPartitionCount partitions)
               (.setStatisticsEnabled true)
               ;; test connections every 25 mins (default is 240):
               (.setIdleConnectionTestPeriodInMinutes 25)
               ;; allow connections to be idle for 3 hours (default is 60 minutes):
               (.setIdleMaxAgeInMinutes (* 3 60))
               ;; consult the BoneCP documentation for your database:
               (.setConnectionTestStatement "/* ping *\\/ SELECT 1"))]
    {:datasource cpds}))

(def db-pool (delay (pool-c3p0)))
(def db-pool1 (delay (pool-bonecp)))

(defn query1 [query]
  (jdbc/query @db-pool1 query))

(defn execute [query]
  (jdbc/execute! @db-pool [query]))

(defn insert [query]
  (let [{r :generated_key} (jdbc/db-do-prepared-return-keys @db-pool query nil)]
    r))
