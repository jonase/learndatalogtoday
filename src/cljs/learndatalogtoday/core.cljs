(ns learndatalogtoday.core
  (:require [domina :refer [by-id nodes]]
            [domina.css :refer [sel]]
            [domina.events :refer [listen!]]))

(defn run-query-fn [chapter exercise inputs]
  (fn [e]
   (.log js/console (str "Chapter " chapter))
   (.log js/console (str "Exercise " exercise))
   (.log js/console (str "Inputs " (pr-str (mapv #(.getValue %) inputs))))))

(defn init [chapter ecount]
  (doseq [n (range ecount)]
    (let [button-id (str "#run-query-" n)
          input-class (str ".input-" n)
          editors (mapv #(.fromTextArea js/CodeMirror %)
                        (nodes (sel input-class)))]
      
      ;; Need to refresh the codemirror editors when tab is shown. I'd
      ;; rather do this with domina but "shown" is a bootstrap
      ;; specific event.
      (.on (js/$ "a[data-toggle=\"tab\"]") 
           "shown" 
           (fn [e] (mapv #(.refresh %) editors)))

      (listen! (sel button-id) :click
               (run-query-fn chapter n editors)))))



