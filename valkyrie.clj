;; This is the compiler. It gets a Valkyrie source file as an input.

(defn write-file-contents [out]
  (clojure.java.io/make-parents "out/server.cljs")
  (spit "out/server.cljs" (:server out))
  (spit "out/client.cljs" (:client out)))

(defn generate-code [parsed-file]
  (-> {:ast parsed-file :out {:server '() :client '()}}
      ;; Add code generation functions here. Each function gets the above dictionary and modifies the out structure. At the end out will be written as files.
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
