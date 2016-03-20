(ns converter.db
  (:import com.mchange.v2.c3p0.ComboPooledDataSource)
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

(def db-pool (delay (pool-c3p0)))

(defn query [query]
  (jdbc/query @db-pool query))

(defn execute [query]
  (jdbc/execute! @db-pool [query]))

(defn insert [query]
  (let [{r :generated_key} (jdbc/db-do-prepared-return-keys @db-pool query nil)]
    r))
