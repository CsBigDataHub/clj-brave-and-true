(ns clj-brave-and-true.chapter5)

;;; my comp
(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

;;; 2
(defn my-comp
  [& fns]
  (reduce (fn [f g]
            #(f (apply g %&)))
          fns))

;;; 1
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(defn attr
  [a]
  (a (:attributes character)))

(attr :intelligence)
(attr :strength)
