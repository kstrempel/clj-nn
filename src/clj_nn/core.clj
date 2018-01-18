(ns clj-nn.core
  (:use [clojure.core.matrix]
        [clojure.core.matrix.operators]
        [clojure.core.matrix.random]))

(def X (array [[0 0 1] [0 1 1] [1 0 1] [1 1 1]]))
(def y (transpose (array [[0 1 1 0]])))

(def syn0 (- (* (sample-uniform [3 4]) 2) 1))
(def syn1 (- (* (sample-uniform [4 1]) 2) 1))

(defn backpropagate
  [syn0 syn1]
  (let [l1 (/ 1 (+ 1 (exp (- (dot X syn0)))))
        l2 (/ 1 (+ 1 (exp (- (dot l1 syn1)))))
        l2_delta (* (- y l2) (* l2 (- 1 l2)))
        l1_delta (* (dot l2_delta (transpose syn1)) (* l1 (- 1 l1)))
        result_syn1 (+ syn1 (dot (transpose l1) l2_delta))
        result_syn0 (+ syn0 (dot (transpose X) l1_delta))]
      [result_syn0 result_syn1]))


(defn calc
  []
  (loop [param [syn0 syn1]
         cnt 6000]
        (if (== cnt 0)
          param
          (recur
            (apply backpropagate param)
            (dec cnt)))))
