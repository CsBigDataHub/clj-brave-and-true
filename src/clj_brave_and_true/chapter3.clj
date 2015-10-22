(ns clj-brave-and-true.chapter3)

;;; 2
(#(+ % 100) 1)

;;; 3
(defn dec-maker
  [dec-by]
  (fn [x] (- x dec-by)))

(def dec9 (dec-maker 9))
(dec9 10)

;;; 4
(defn mapset
  [f s]
  (set (map f s)))

(mapset inc [1 1 2 2])

;;; 5, 6
(defn expander
  [times part]
  (let [name (:name part)]
    (when (or (= name "eye")
              (= name "ear")
              (= name "shoulder")
              (= name "upper-arm")
              (= name "forearm")
              (= name "kidney")
              (= name "hand")
              (= name "knee")
              (= name "thigh")
              (= name "lower-leg")
              (= name "achilles")
              (= name "foot"))
      (repeat times part))))

(def alien-expander (partial expander 5))

(def base-parts [{:name "head"      :size 3}
                 {:name "eye"       :size 1}
                 {:name "ear"       :size 1}
                 {:name "mouth"     :size 1}
                 {:name "nose"      :size 1}
                 {:name "neck"      :size 2}
                 {:name "shoulder"  :size 3}
                 {:name "upper-arm" :size 3}
                 {:name "chest"     :size 10}
                 {:name "back"      :size 10}
                 {:name "forearm"   :size 3}
                 {:name "abdomen"   :size 6}
                 {:name "kidney"    :size 1}
                 {:name "hand"      :size 2}
                 {:name "knee"      :size 2}
                 {:name "thigh"     :size 4}
                 {:name "lower-leg" :size 3}
                 {:name "achilles"  :size 1}
                 {:name "foot"      :size 2}])

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size
and an expander function."
  [body-parts expander]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (expander part)))
          []
          body-parts))

(symmetrize-body-parts base-parts alien-expander)
