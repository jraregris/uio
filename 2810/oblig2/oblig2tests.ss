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

(display "lookup-neigbhors, front-queue, rest-queue")
(test (lookup-neighbors 'Ali) '(Ben Coz Dil Ezi))
(test (lookup-neighbors (front-queue (lookup-neighbors 'Ali))) '(Ali Coz Hur Ibe Jiz))
(test (front-queue (lookup-neighbors 'Ali)) 'Ben)
(test (rest-queue (lookup-neighbors 'Ali)) '(Coz Dil Ezi))

(display "\nhas?")
(test (has? 'Ali '()) #f)
(test (has? 'Ali (list 'Ali)) #t)
(test (has? 'Ali (list 'Ali 'baba 'congo)) #t)
(test (has? 'Ali (list 'baba 'congo 'Ali)) #t)
(test (has? 'Ali (list 'baba 'congo)) #f)


(display "\ninit-queue")
(test (init-queue 'Ali) '((Ali #f)))

(display "\nexpand-node")
(test (expand-node '() '() 'Ali) '((Ben Ali)(Coz Ali)(Dil Ali)(Ezi Ali)))

;(display "\ntraverse")
;(test (traverse (init-queue 'Ali) '() 'Ali) '((Ali #f)))


; Acceptance tests
(display "\nAcceptance Tests")
(test (do-search 'a '()) '(a is not on the map))
(test (do-search 'Ali 'Ali) '(target = start))
(test (do-search 'Ben 'Ben) '(target = start))

(test (do-search 'Ali 'Ben) '(Shortest path: (Ali Ben)))
(test (do-search 'Ali 'Jiz) '(shortest path: (Ali Ben Jiz)))

(display "\nmore\n")

(map do-search
     '(Ali Ali Ali Åge Ozi)
     '(Jiz Tlö Wot Mur Hur))