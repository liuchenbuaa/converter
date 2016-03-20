(defproject converter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [honeysql "0.6.3"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [com.mchange/c3p0 "0.9.2.1"]
                 ]
  :main ^:skip-aot converter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
