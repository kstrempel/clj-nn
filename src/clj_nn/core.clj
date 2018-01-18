(ns clj-nn.core
  (:use [clojure.core.matrix]
        [clojure.core.matrix.operators]
        [clojure.core.matrix.random]))

(def X (array [[0 0 1] [0 1 1] [1 0 1] [1 1 1]]))
(def y (array [[0 1 1 0]]))

(def syn0 (- (* (sample-normal [3 4] 2) 1)))
(def syn1 (- (* (sample-normal [4 1] 2) 1)))

(defn backpropagate
  [syn0 syn1]
  (let [l1 (/ 1 (+ (exp (- (dot X syn0)))))
        l2 (/ 1 (+ (exp (- (dot l1 syn1)))))
        l2_delta (* (- y l2) (* l2 (- 1 l2)))
        l1_delta (* (dot l2_delta syn1) (* l1 (- 1 l1)))
        result_syn0 (+ (dot syn0 l1))
        result_syn1 (+ (dot X l1_delta))]
      [result_syn0 result_syn1]))

(defn calc
  []
  (backpropagate syn0 syn1))
