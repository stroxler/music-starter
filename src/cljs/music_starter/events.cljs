(ns music-starter.events
  (:require [re-frame.core :as re-frame]
            [music-starter.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
