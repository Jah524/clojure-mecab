# clojure-mecab

A Clojure library for Japanese morphological analyzer MeCab.

## Prerequisites

### Install [Mecab](http://taku910.github.io/mecab/)

- Try below example to check whether mecab has been correctly installed

```bash
$ echo すもももももももものうち | mecab
すもも	名詞,一般,*,*,*,*,すもも,スモモ,スモモ
も	助詞,係助詞,*,*,*,*,も,モ,モ
もも	名詞,一般,*,*,*,*,もも,モモ,モモ
も	助詞,係助詞,*,*,*,*,も,モ,モ
もも	名詞,一般,*,*,*,*,もも,モモ,モモ
の	助詞,連体化,*,*,*,*,の,ノ,ノ
うち	名詞,非自立,副詞可能,*,*,*,うち,ウチ,ウチ
EOS
```

### Add dependency to your `project.clj`

```clojure
[jah524/clojure-mecab "0.2.0"]
```

## Usage

```clojure
(use 'clojure-mecab)

(parse "すもももももももものうち")
;=> [["すもも" "名詞" "一般" "*" "*" "*" "*" "すもも" "スモモ" "スモモ"]
;    ["も" "助詞" "係助詞" "*" "*" "*" "*" "も" "モ" "モ"]
;    ["もも" "名詞" "一般" "*" "*" "*" "*" "もも" "モモ" "モモ"]
;    ["も" "助詞" "係助詞" "*" "*" "*" "*" "も" "モ" "モ"]
;    ["もも" "名詞" "一般" "*" "*" "*" "*" "もも" "モモ" "モモ"]
;    ["の" "助詞" "連体化" "*" "*" "*" "*" "の" "ノ" "ノ"]
;    ["うち" "名詞" "非自立" "副詞可能" "*" "*" "*" "うち" "ウチ" "ウチ"]]


(extract-words "すもももももももものうち")
;=> ["すもも" "も" "もも" "も" "もも" "の" "うち"]

(extract-words "すもももももももものうち" ["名詞"] [])
;=> ["すもも" "もも" "もも" "うち"]

(extract-words "すもももももももものうち" [] ["名詞"])
;=> ["も" "も" "の"]

(extract-words "すもももももももものうち" ["名詞"] ["非自立"])
;=> ["すもも" "もも" "もも"]
```

# Note

This library uses `clojure.java.shell/sh` to access mecab so that you do not need Java JNI bindings.
If you want to deploy your application to Saas such as Heroku, you better use [kuromoji](http://www.atilika.org/) (Java implementation) instead.
But mecab is much faster than kuromoji, so you should use mecab if you want to process massive data.

## License

Copyright © 2018 Jah524

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
