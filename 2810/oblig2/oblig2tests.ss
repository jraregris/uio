; Load test "framework"
(load "test.ss")

; Load BFS

(load "BFS.ss")

; Configure BFS

(load "Graaf.ss")
(define *map* graaf)
(define (towns-and-roads) (car *map*))

; Test BFS

(test (do-search 'a '()) '(a is not on the map))
(test (do-search 'ali 'ali) '(target = start))
(test (do-search 'ben 'ben) '(target = start))
(test (do-search 'ali 'jiz) '(shortest path: (ali ben jiz)))