(ns clj-brave-and-true.chapter4)

;;; Implement map with reduce
(defn my-map
  [f s]
  (reduce (fn [new-s s]
            (cons (f s) new-s))
          []
          s))

(my-map inc [1 2 3])

;;; Implement filter with reduce
(defn my-filter
  [p s]
  (reduce (fn [new-s s]
            (if (p s)
              (cons s new-s)
              new-s))
          []
          s))

(my-filter #(> % 2) [1 2 3 4 5])

;;; Implement some with reduce
(defn my-some
  [p s]
  (reduce (fn [new-s s]
            (if (p s)
              true
              new-s))
          []
          s))

(my-some #(> % 2) [1 2 1])
