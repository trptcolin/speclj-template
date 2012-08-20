(ns leiningen.new.speclj
  (:use [leiningen.new.templates :only [renderer name-to-path ->files
                                        sanitize-ns year project-name]]))

(def render (renderer "speclj"))

(defn speclj
  "An speclj-based application project template."
  [name]
  (let [render (renderer "speclj")
        data {:raw-name name
              :name (project-name name)
              :namespace (sanitize-ns name)
              :nested-dirs (name-to-path name)
              :year (year)}]
    (println "Generating a project called" name "based on the 'speclj' template.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/{{nested-dirs}}/core.clj" (render "core.clj" data)]
             ["spec/{{nested-dirs}}/core_spec.clj" (render "spec.clj" data)])))

