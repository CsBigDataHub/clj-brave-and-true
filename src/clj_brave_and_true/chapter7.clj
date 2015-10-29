(ns clj-brave-and-true.chapter7)

;;; 1
(eval '(list (read-string "Manuel") (read-string "Aliens")))

;;; 2
(defn precedence?
  [v]
  (or (= v '*) (= v '/)))

;; TODO
;; (defn infix
;;   [s]
;;   )
