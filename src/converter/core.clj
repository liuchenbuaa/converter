(ns converter.core
  (:gen-class)
  (:use clojure.java.io)
  (:import [java.io File])
  (:require [converter.db :as db]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn insert-tag [name]
  (db/insert (str "insert into tag(name,status) values ('" name "',1)")))

(defn insert-tag-relation [son father]
  (db/insert (str "insert into tag_relation(tag_id,parent_id) values ('" son "','" father "')")))

(defn is-valid? [str]
  (not (re-find #"_Category" str)))

(defn remove-yaml [str]
  (let [pos (.indexOf str "yaml")]
    (if-not (= pos -1)
      (subs str 0 (- pos 1))
      str)))

(defn read-and-write [name father]
  (let [file (File. name)
        file-name (.getName file)]
    (if (is-valid? file-name)
      (let [son (insert-tag (remove-yaml file-name))]
        (insert-tag-relation son father)
        (if (.isDirectory file)
          (doseq [x (.listFiles file)]
            (read-and-write (str file "/" (.getName x)) son)
            )
          )))))
