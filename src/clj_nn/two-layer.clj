(ns clj-nn.two-layer
  (:use [clojure.core.matrix]
        [clojure.core.matrix.operators]
        [clojure.core.matrix.random]))

;; Input dataset
(def X (array [[0 0 1]
               [0 1 1]
               [1 0 1]
               [1 1 1]]))

;; output dataset
(def y (transpose (array [[0 0 1 1]])))

;; initialize weights randomly with mean 0
;; with seed 1 ( just a good practice )
(def syn0 (- (* 2 (sample-uniform [3 1] 1)) 1))

(defn nonlin
  ([x] (nonlin x false))
  ([x deriv] (if deriv
              (* x (- 1 x))
              (/ 1 (+ 1 (exp (- x)))))))

(defn forward
  [syn0]
  (let [l1 (nonlin (dot X syn0))
        l1_error (- y l1)
        l1_delta (* l1_error (nonlin l1 true))]
      [(+ syn0 (dot (transpose X) l1_delta)) l1]))

(defn train
  []
  (loop [param [syn0 nil]
         cnt 10000]
      (if (== cnt 0)
       (last param)
       (recur
         (forward (first param))
         (dec cnt)))))
