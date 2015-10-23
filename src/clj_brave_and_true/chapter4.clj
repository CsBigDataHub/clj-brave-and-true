(ns clj-brave-and-true.chapter4)

;;; implement map with reduce
(defn my-map
  [f s]
  (reduce (fn [new-s s]
            (cons (f s) new-s))
          []
          s))

(map inc [1 2 3])
(my-map inc [1 2 3])