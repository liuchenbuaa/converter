(ns converter.core
  (:gen-class)
  (:use clojure.java.io)
  (:import [java.io File]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn read-and-write [name]
  (let [file (File. name)]
    (if-not (.isDirectory file)
      (println (.getName file))
      (doseq [x (.listFiles file)]
        (println (.getName x))
        (read-and-write (str file "/" (.getName x))))
      )))
