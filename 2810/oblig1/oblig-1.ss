;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; GLOBALE VARIABLER
(define startår 1970)
(define sekunder/dag 86400)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; HJELPEPROSEDYRER
(define (skuddår? år)                 ; Returer #t hvis ÅR er et skuddår.
  (and (zero? (remainder år 4))
       (or (not (zero? (remainder år 100))) (zero? (remainder år 400)))))
(define (årslengde år) (if (skuddår? år) 366 365))
(define (månedslengde år måned)       ; Returner lengden til MÅNED i året ÅR.
  (cond ((or (= måned 4) (= måned 6) (= måned 9) (= måned 11)) 30)
        ((= måned 2) (if (skuddår? år) 29 28))
        (else 31)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; dager->dato returner en liste med år måned og dag for den
;; dato som svarer til det gitte antall dager fra 1. 1. 1970.
(define (dager->dato år mnd dag)
  (cond ((> mnd 12) (dager->dato (+ år 1) (- mnd 12) dag))
        ((> dag (årslengde år)) (dager->dato (+ år 1) mnd (- dag (årslengde år))))
        ((> dag (månedslengde år mnd)) (dager->dato år (+ mnd 1) (- dag (månedslengde år mnd))))
        (else (list år mnd dag))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; sekunder->dato sekunder returnerer en liste med år måned og dag for
;; den dato som svarer til det gitte antall sekunder fra 1. 1. 1970.
(define (sekunder->dato sekunder)
  (dager->dato startår 1 (+ 1 (quotient sekunder sekunder/dag))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; dato->dager returnerer antall dager fra midnatt  1. 1. 1970
;; til midnatt den gitt datoen
(define (dato->dager år måned dag)
  
  ; FYLL UT
  
  0)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; dato->sekunder returnerer antall sekunder fra midnatt 1. 1. 1970
;; til midnatt den gitt datoen
(define (dato->sekunder år måned dag)
  (* sekunder/dag (dato->dager år måned dag)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Testprosedyre
(define (test actual expected)
  (cond ((equal? actual expected)
         (display "."))
        (else 
        
         (display " FAIL! Expected:")
         (display expected)
         (display "\n           was:")
         (display actual))))

;; Testkjøring


(test (sekunder->dato           0) '(1970  1  1))
(test (sekunder->dato     2592000) '(1970  1 31))
(test (sekunder->dato    92361600) '(1972  12 5))
(test (sekunder->dato  1077926400) '(2004  2 28))
(test (sekunder->dato  1078012800) '(2004  2 29))
(sekunder->dato  1078099200) ;==> (2004  3  1)  00:00:00
(sekunder->dato  1104451200) ;==> (2004 12 31)  00:00:00
(sekunder->dato  1104537600) ;==> (2005  1  1)  00:00:00
(sekunder->dato  1201824000) ;==> (2008  2  1)  20:50:26
(sekunder->dato 64313049600) ;==> (4008  1  1)  00:00:00
;'-----------
;(dato->sekunder 1970  1  1) ;==>           0   00:00:00
;(dato->sekunder 1970  1 31) ;==>     2592000   00:00:00
;(dato->sekunder 1972  12 5) ;==>    92361600   00:00:00
;(dato->sekunder 2004  2 28) ;==>  1077926400   00:00:00
;(dato->sekunder 2004  2 29) ;==>  1078012800   00:00:00
;(dato->sekunder 2004  3  1) ;==>  1078099200   00:00:00
;(dato->sekunder 2004 12 31) ;==>  1104451200   00:00:00
;(dato->sekunder 2005  1  1) ;==>  1104537600   00:00:00
;(dato->sekunder 2008  2  1) ;==>  1201824000   00:00:00

