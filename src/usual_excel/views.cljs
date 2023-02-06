(ns usual-excel.views
  (:require
   [reagent.core :as r]
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
  ;;  [cljsjs.xlsx :as XLSX]
   [usual-excel.events :as events]
   [usual-excel.subs :as subs]))

;; (defn read-sheet [wb sheet-name]
;;   (let [sheet (aget (. wb -Sheets) sheet-name)
;;         data (XLSX/utils.sheet_to_json sheet #js {:header 1})
;;         dataclj (js->clj data)
;;         kk (map keyword (first dataclj))
;;         outdata (into [] (for [line (rest dataclj)] (zipmap kk line)))]

;;     (re-frame/dispatch [:source-data outdata])))

;; (defn read-local-file []
;;   (let [xhr (js/XMLHttpRequest.)]
;;     (.open xhr "GET" "datadump.xlsx" true)
;;     (set! (.-responseType xhr) "blob")
;;     (set! (.-onload xhr)
;;           (fn []
;;             (let [reader (js/FileReader.)]
;;               (.readAsArrayBuffer reader (.-response xhr))
;;               (set! (.-onload reader)
;;                     (fn [evt]
;;                       (let [ab (-> evt .-target .-result)
;;                             wb (XLSX/read ab #js {:type "array" :cellDates true})
;;                             sn (. wb -SheetNames)]
;;                         (read-sheet wb (first sn))))))))
;;     (.send xhr)))
   
(defn title-main [str]
  [:h1
   [:p.title str]])

(defn submit-image-form
  "a form version, using a default form (no ajax)"
  [str]
  [:div str [:p]
   [:div [:form {:action "/upload-image"
                 :enc-type "multipart/form-data"
                 :method "post"}
          [:input {:type "file"
                   :name "myfileup"}]
          [:button {:type "submit"
                    :on-click #(re-frame/dispatch [::events/update-file "ddd"])}
           "Submit image form"]]]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     (title-main "Usual-Excel")
     [:div
      [:span.chat-left "안녕하세요. 파일을 업로드 해 주세요."]]
     [:p]
     [:div.horizontal
      [:span.horizontal.chat-right
       (submit-image-form "자, 여기 있어.")]]
     [:button {:type "submit"
               :on-click #(re-frame/dispatch [::events/update-file "ddd"])}
      "Submit image form"]]))

     
