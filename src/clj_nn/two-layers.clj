(ns clj-nn.two-layer
  (:use [clojure.core.matrix]
        [clojure.core.matrix.operators]
        [clojure.core.matrix.random]
        [clojure.core.matrix.stats]
        [clojure.core.matrix.dataset]))

;; Input dataset
(def X (array [[0 0 1]
               [0 1 1]
               [1 0 1]
               [1 1 1]]))

;; output dataset
(def y (transpose (array [[0 1 1 1]])))

;; initialize weights randomly with mean 0
;; with seed 1 ( just a good practice )
(def syn0 (- (* 2 (sample-uniform [3 4] 1)) 1))
(def syn1 (- (* 2 (sample-uniform [4 1] 1)) 1))

(defn nonlin
  ([x] (nonlin x false))
  ([x deriv] (if deriv
              (* x (- 1 x))
              (/ 1 (+ 1 (exp (- x)))))))

(defn forward
  [syn1 syn0]
  (let [l0 X
        l1 (nonlin (dot l0 syn0))
        l2 (nonlin (dot l1 syn1))

        l2_error (- y l2)
        l2_delta (* l2_error (nonlin l2 true))

        l1_error (dot l2_delta (transpose syn1))
        l1_delta (* l1_error (nonlin l1 true))]
      [(+ syn1 (dot (transpose l1) l2_delta))
       (+ syn0 (dot (transpose l0) l1_delta))
       l2_delta]))

(defn train
  []
  (loop [param [syn1 syn0 nil]
         cnt 10001]
      (when (== (mod cnt 1000) 0)
        (println (format "%f" (get (array (mean (abs (get param 2)))) 0))))
      (if (== cnt 0)
       (do
         (println "done")
         (get param 2))
       (recur
         (forward (first param) (second param))
         (dec cnt)))))
