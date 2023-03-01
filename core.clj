(ns child-block-counter.core
  (:require [logseq.core :as logseq]
            [clojure.string :as string]))

(defn get-child-block-count [block-id]
  (let [child-blocks (logseq/get-blocks {:parent block-id})
        child-block-count (count child-blocks)]
    child-block-count))

(defn render-block-count [block]
  (let [block-id (-> block :block/id)
        child-block-count (get-child-block-count block-id)]
    (str " (" child-block-count ")")))

(defn add-block-counts []
  (let [blocks (logseq/get-blocks {})
        blocks-with-counts (map #(update % :block/content str (render-block-count %)) blocks)]
    (logseq/update-blocks! blocks-with-counts)))

(defn start []
  (add-block-counts))

