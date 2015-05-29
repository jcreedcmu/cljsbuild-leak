(defproject cljsbuild-leak "0.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0-beta2"]
                 [org.clojure/clojurescript "0.0-3269" :scope "provided"]]

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.3"]]

  :clean-targets ["build"]

  :cljsbuild {:builds {:foo {:source-paths ["src/both" "src/foo"]
                             :compiler {:main          "pack.core"
                                        :output-dir    "build/foo/js/out"
                                        :output-to     "build/foo/app.js"}}
                       :bar {:source-paths ["src/both" "src/bar"]
                             :compiler {:main          "pack.core"
                                        :output-dir    "build/bar/js/out"
                                        :output-to     "build/bar/app.js"}}}})
