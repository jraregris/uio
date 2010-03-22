; Load test "framework"
(load "test.ss")

; Load BFS

(load "BFS.ss")

; Configure BFS

(load "Graaf.ss")
(define *map* graaf)
(define (towns-and-roads) (car *map*))

; Test BFS

; ‘Unit’ tests

(test (lookup-neighbors 'Ali) '(Ben Coz Dil Ezi))
(test (lookup-neighbors (front-queue (lookup-neighbors 'Ali))) '(Ali Coz Hur Ibe Jiz))
(test (front-queue (lookup-neighbors 'Ali)) 'Ben)
(test (rest-queue (lookup-neighbors 'Ali)) '(Coz Dil Ezi))

(test (has? 'ali '()) #f)
(test (has? 'ali (list 'ali)) #t)
(test (has? 'ali (list 'ali 'baba 'congo)) #t)
(test (has? 'ali (list 'baba 'congo 'ali)) #t)
(test (has? 'ali (list 'baba 'congo)) #f)

(test (traverse (init-queue 'ali) '() 'ali) '(ali))
; Acceptance tests

(test (do-search 'a '()) '(a is not on the map))
(test (do-search 'Ali 'Ali) '(target = start))
(test (do-search 'Ben 'Ben) '(target = start))
:(test (do-search 'Ali 'Ben) '(Shortest path: (Ali Ben)))
;(test (do-search 'Ali 'Jiz) '(shortest path: (Ali Ben Jiz)))
