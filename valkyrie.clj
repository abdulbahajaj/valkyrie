;; This is the compiler. It gets a Valkyrie source file as an input.
(ns valk
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def ^:const out-path "out")

(defn trans-append [trans key el]
  (assoc-in trans [:out key] (conj (-> trans :out key) el)))

(defn client-add-ns [trans]
  (trans-append trans :client '(ns valk.generated)))

(defn server-add-ns [trans]
  (trans-append trans :server '(ns valk.generated)))

(defn emit [path content]
  (spit (format "%s/%s" out-path path) (s/join "\n" content)))

(defn write-file-contents [out]
  (emit "/client/src/valk/generated.cljs" (:client out))
  (emit "/server/src/valk/generated.cljs" (:server out)))

(defn generate-code [parsed-file]
  (-> {:ast parsed-file :out {:server '[] :client []}}
      ;; Add code generation functions here. Each function gets the above dictionary and modifies the out structure. At the end out will be written as files.
      client-add-ns
      server-add-ns 
      :out
      write-file-contents))

(defn wrap-program-in-list [file-content]
  (format "(\n%s\n)" file-content))

(defn -main [& args]
  (-> (first args) ;; Get the first argument, which is a file path
      slurp ;; read the file
      wrap-program-in-list ;; wrap the file with square brackets. The reason is that the parser, read-string, reads one exp in the string. This turns the file into an expression containing all the expressions in the file
      read-string ;; parse the file
      generate-code))

(apply -main *command-line-args*)
