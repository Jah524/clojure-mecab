(ns clojure-mecab-test
  (:require [clojure.test :refer :all]
            [clojure-mecab :refer [parse extract-words]]))

(deftest main-test
  (testing "parse"
    (is (= (parse "すもももももももものうち")
           [["すもも" "名詞" "一般" "*" "*" "*" "*" "すもも" "スモモ" "スモモ"]
            ["も" "助詞" "係助詞" "*" "*" "*" "*" "も" "モ" "モ"]
            ["もも" "名詞" "一般" "*" "*" "*" "*" "もも" "モモ" "モモ"]
            ["も" "助詞" "係助詞" "*" "*" "*" "*" "も" "モ" "モ"]
            ["もも" "名詞" "一般" "*" "*" "*" "*" "もも" "モモ" "モモ"]
            ["の" "助詞" "連体化" "*" "*" "*" "*" "の" "ノ" "ノ"]
            ["うち" "名詞" "非自立" "副詞可能" "*" "*" "*" "うち" "ウチ" "ウチ"]])))
  (testing "extract-words"
    (is (= (extract-words "すもももももももものうち")
           ["すもも" "も" "もも" "も" "もも" "の" "うち"]))
    (is (= (extract-words "すもももももももものうち" ["名詞"] [])
           ["すもも" "もも" "もも" "うち"]))
    (is (= (extract-words "すもももももももものうち" [] ["名詞"])
           ["も" "も" "の"]))
    (is (= (extract-words "すもももももももものうち" ["名詞"] ["非自立"])
           ["すもも" "もも" "もも"])))
  (testing "extract-words with new-line"
    (is (= (extract-words "すもももももももものうち\n隣の客はよく柿食う客だ")
           ["すもも" "も" "もも" "も" "もも" "の" "うち" "隣" "の" "客" "は" "よく" "柿" "食う" "客" "だ"]))
    (is (= (extract-words "すもももももももものうち\n隣の客はよく柿食う客だ" ["動詞"] [])
           ["食う"]))))


