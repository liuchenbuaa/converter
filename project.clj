(defproject converter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "0.9.1"]
                 [clj-yaml "0.4.0"]
                 [enlive "1.1.5"]
                 [me.shenfeng/mustache "1.1"]
                 [cheshire "5.3.1"]
                 [com.taoensso/carmine "2.6.0"]
                 [honeysql "0.6.3"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [com.mchange/c3p0 "0.9.2.1"]
                 ]
  :main ^:skip-aot converter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
