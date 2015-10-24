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

;;; A Vampire Data Analysis Program for the FWPD
(def filename "suspects.csv")
;; (slurp filename)

(def vamp-keys [:name :glitter-index])

(defn str-int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str-int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))
;; (convert :glitter-index "3")

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))
;; (parse (slurp filename))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))
;; (first (mapify (parse (slurp filename))))

(defn glitter-filter
  [minimum-glitter records]
  (map :name ;;; 1
       (filter #(>= (:glitter-index %) minimum-glitter) records)))
;; (glitter-filter 3 (mapify (parse (slurp filename))))

;;; 2
(defn append
  [suspects new-suspect]
  (into suspects
        (glitter-filter 3 new-suspect)))

(append (glitter-filter 3 (mapify (parse (slurp filename))))
        (list {:name "Manuel Uberti", :glitter-index 5}))

;;; 3
(def validations {:name? #(= % :name)
                  :glitter-index? #(= % :glitter-index)})

(defn validate
  [functions record]
  (when (map? record)
    (if (every? true?
                (map #(some % (keys record))
                     (vals functions)))
      record
      false)))

(append (glitter-filter 3 (mapify (parse (slurp filename))))
        (list (validate validations
                        {:name "Manuel Uberti", :glitter-index 5})))

;;; 4
(defn map-to-row
  [m]
  (when (validate validations m)
    (str (:name m) "," (:glitter-index m) "\n")))

(defn to-csv
  [mapified-entries]
  (clojure.string/join (map map-to-row mapified-entries)))

(= (to-csv (mapify (parse (slurp filename))))
   (slurp filename))
