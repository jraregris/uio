;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ABSTRACTION

;; Coordinates
(define (x-coord coords) (car coords))
(define (y-coord coords) (cdr coords))

;; Unexpanded node
(define (lookup-coordinates node) (cadr (assoc node (towns-and-roads))))
(define (lookup-neighbors nodenavn) (cddr (assoc nodenavn (towns-and-roads))))
(define (lookup-distance pred node)
  (cdr (or (assoc (cons pred node) (distances))
           (assoc (cons node pred) (distances)))))

;; Enqueued node
(define (get-node-name node) (car node))
(define (get-pred-name node) (cadr node))
(define (get-distance-from-start node) (caddr node))
(define (make-queued pred node-name)
  (let ((pred-name (get-node-name pred)))
    (list node-name
          pred-name
          <compute-distance-from-start>))) ; FILL IN

;; Queue
(define (front-queue Q) (car Q))
(define (rest-queue Q) (cdr Q))
(define (init-queue start) (list (list start #f 0)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; HEURISTIC

(define (estimate-min-dist node target)
  xxx) ; FILL IN

(define (higher-priority? x y target)
  x-is-in-a-better-position-than-y) ; FILL IN

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ALGORITHM

(define (enqueue node Q target)
  xxx) ; FILL IN

(define (expand-node Q S node target)
  xxx) ; FILL IN

(define (traverse Q S target) ; need target name and coords in order to estimate pigeon distance
  xxx) ; FILL IN

(define (retrace S)                             ; S now begins with target and ends with start
  xxx) ; FILL IN

(define (A* start target)
  (retrace (traverse (init-queue start) '() target)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (do-search start target)
  (cond ((not (assoc start  (towns-and-roads))) (list start  'is 'not 'on 'the 'map))
        ((not (assoc target (towns-and-roads))) (list target 'is 'not 'on 'the 'map))
        ((eq? target start) (list 'target '= 'start))
        (else (list 'Shortest 'path ': (A* start target)))))


; The definitions below are placed here for easy replacement during test runs.
; Every procedure will have these definitions in its environment during run-time.

(load "sample-graph.ss")
(define *map* sample-map)
(define (towns-and-roads) (car *map*))
(define (distances) (cdr *map*))

(do-search 'a 'j)

