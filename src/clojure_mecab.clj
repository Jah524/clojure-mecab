(ns clojure-mecab
  (:require
    [clojure.java.shell :refer [sh]]
    [clojure.string :refer [split]]))


(defn morphological-analysis
  [text]
  (->> (split (:out (sh "mecab" :in text)) #"\n")
       (take-while #(not= "EOS" %))
       (mapv #(let [[word info] (split % #"\t")]
                (vec (concat [word] (split info #",")))))))

(defn form?
  [token-feature form-list]
  (->> token-feature
       (drop 1)
       (take 2)
       (some (fn [x] (some #(= x %) form-list)))))

(defn extract-words
  ([sentence filter-list remove-list morph-index]
   (->> (morphological-analysis sentence)
        (filter #(if (empty? filter-list) true (form? % filter-list)))
        (remove #(form? % remove-list))
        (mapv #(nth % morph-index))))
  ([sentence filter-list remove-list]
   (extract-words sentence filter-list remove-list 0))
  ([sentence]
   (extract-words sentence [] [])))
