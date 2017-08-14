(ns music-starter.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [music-starter.events]
            [music-starter.subs]
            [music-starter.style :as style]
            [music-starter.views :as views]
            [music-starter.config :as config]
            [music-starter.vex :as vex]
            [music-starter.bach :as bach]
            [cljs-bach.synthesis :as synth]
            ))

(defn add-styles []
  (style/update-css (style/get-css)))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))



;; Note that this is not at all hooked into reagent right now

"
(defn ^:export try-bare-bach []
  (let [ping (fn [freq]
               (synth/connect->
                (synth/square freq)
                (synth/percussive 0.01 0.4)
                (synth/gain 0.1)))]
    (-> (ping 440)
        (synth/connect-> synth/destination)
        (synth/run-with bach/context (synth/current-time bach/context) 1.0))))
"


(try
  (vex/example-render))
(try
  (bach/example-play))

(set! (.-onload js/window)
      (fn []
        (vex/example-render)
        (bach/example-play)))
