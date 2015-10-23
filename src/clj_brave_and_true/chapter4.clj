(ns clj-brave-and-true.chapter4)

;;; implement map with reduce
(defn my-map
  [f s]
  (reduce (fn [new-s s]
            (cons (f s) new-s))
          []
          s))

(my-map inc [1 2 3])

;;; implement filter with reduce
(defn my-filter
  [p s]
  (reduce (fn [new-s s]
            (if (p s)
              (cons s new-s)
              new-s))
          []
          s))

(my-filter #(> % 2) [1 2 3 4 5])
