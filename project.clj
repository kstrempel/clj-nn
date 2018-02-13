(defproject clj-nn "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-jupyter "0.1.14"]]
  :main ^:skip-aot clj-nn.two-layer
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.mikera/core.matrix "0.61.0"]
                 [proto-repl "0.3.1"]
                 [incanter "1.5.7"]])
