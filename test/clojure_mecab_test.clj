(ns clojure-mecab-test
  (:require [clojure.test :refer :all]
            [clojure-mecab :refer [morphological-analysis extract-words]]))

(deftest main-test
  (testing "morphological-analysis"
    (is (= (morphological-analysis "すもももももももものうち"))
        [["すもも" "名詞" "一般" "*" "*" "*" "*" "すもも" "スモモ" "スモモ"]
         ["も" "助詞" "係助詞" "*" "*" "*" "*" "も" "モ" "モ"]
         ["もも" "名詞" "一般" "*" "*" "*" "*" "もも" "モモ" "モモ"]
         ["も" "助詞" "係助詞" "*" "*" "*" "*" "も" "モ" "モ"]
         ["もも" "名詞" "一般" "*" "*" "*" "*" "もも" "モモ" "モモ"]
         ["の" "助詞" "連体化" "*" "*" "*" "*" "の" "ノ" "ノ"]
         ["うち" "名詞" "非自立" "副詞可能" "*" "*" "*" "うち" "ウチ" "ウチ"]]))
  (testing "extract-words"
    (is (= (extract-words "すもももももももものうち")
           ["すもも" "も" "もも" "も" "もも" "の" "うち"]))
    (is (= (extract-words "すもももももももものうち" ["名詞"] [])
           ["すもも" "もも" "もも" "うち"]))
    (is (= (extract-words "すもももももももものうち" [] ["名詞"])
           ["も" "も" "の"]))
    (is (= (extract-words "すもももももももものうち" ["名詞"] ["非自立"])
           ["すもも" "もも" "もも"]))))

