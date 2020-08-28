;; This is the compiler. It gets a Valkyrie source file as an input.
(ns valk
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def ^:const out-path "out")

(defn trans-append [trans key & el]
  (assoc-in trans [:out key] (concat (-> trans :out key) el)))





(defn is-server-special-form? [exp]
  (if (not (seq? exp)) false
      (let [sym (first exp)]
        (and (symbol? sym) (.contains ["defs", "defchan"] (str sym))))))

(defn collect-server-special-forms [trans]
   (reduce #(if (is-server-special-form? %2) (concat %1 (list %2)) %1)
          '() (:ast trans)))
(defn add-server-sf-to-server [trans]
  (apply trans-append trans :server (collect-server-special-forms trans)))

(defn vserver-add-app [trans]
  (trans-append
   trans
   :server
   '(defn app [req]
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    "Valk server runtime"})))

(defn copy-parsed-file-to-client [trans]
  (apply trans-append trans :client (:ast trans)))

(defn emit [path content]
  (spit (format "%s/%s" out-path path) (string/join "\n" content) :append true))

(defn write-file-contents [out]
  (emit "/vserver/src/vserver/generated.clj" (:server out))
  (emit "/client/src/valk/generated.cljs" (:client out)))

(defn generate-code [parsed-file]
  (-> {:ast parsed-file :out {:server '[] :client []}}
      ;; Add code generation functions here. Each function gets the above dictionary and modifies the out structure. At the end out will be written as files.
      vserver-add-app
      add-server-sf-to-server

      copy-parsed-file-to-client
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
