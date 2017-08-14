(ns music-starter.style
  (:require [garden.core :as g]))


(defn update-css [css-str]
  (let [ style-tag-selector "#garden-css"
        style-tag (.querySelector js/document style-tag-selector)]
    (set! (.-innerHTML style-tag) css-str)))

(defn get-css []
  (g/css [:body {}]))
