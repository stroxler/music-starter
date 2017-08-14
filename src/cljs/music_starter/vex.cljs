(ns music-starter.vex
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [Vex :as vexflow]))


(defonce VF vexflow/Flow)
(defonce vf-div (js/document.getElementById "vex"))
(defonce vf-renderer (VF.Renderer. vf-div VF.Renderer.Backends.SVG))

(set! js/VF VF)


(defn example-notes []
  #js [(VF.StaveNote. #js {:clef "treble" :keys #js ["c/4"] :duration "q"})
       (VF.StaveNote. #js {:clef "treble" :keys #js ["d/4"] :duration "q"})
       (VF.StaveNote. #js {:clef "treble" :keys #js ["b/4"] :duration "qr"})
       (VF.StaveNote. #js {:clef "treble" :keys #js ["c/4" "e/4" "g/4"] :duration "q"})])


(defn example-render []
  (let [context (.getContext vf-renderer)
        stave (VF.Stave. 10 40 400)
        notes (example-notes)
        voice (VF.Voice. #js {:num_beats 4 :beat_value 4})
        formatter (VF.Formatter.)
        voices #js [voice]]
    (.resize vf-renderer 500 500)
    (.setBackgroundFillStyle (.setFont context "Arial" 10 "") "#eed")
    (.addClef stave "treble")
    (.addTimeSignature stave "4/4")
    (.setContext stave context)
    (.draw stave)
    (.addTickables voice notes)
    (.joinVoices formatter voices)
    (.format formatter voices 400)
    (.draw voice context stave)))
