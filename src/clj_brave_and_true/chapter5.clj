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

(defn attr
  [a]
  (a (:attributes character)))

;;; 3
(defn my-assoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (my-assoc-in (get m k) ks v))
    (assoc m k v)))

;;; 4
(update-in character [:attributes :intelligence] inc)

;;; 5
(defn my-update-in
  [m [k & ks] f & args]
  (if ks
    ;; TODO
    ))
