;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ABSTRACTION

;; Unexpanded node
(define (lookup-neighbors nodenavn) (cddr (assoc nodenavn (towns-and-roads))))
(define (lookup-distance pred node)
  (cdr (or (assoc (cons pred node) (distances))
           (assoc (cons node pred) (distances)))))

;; Queue
(define (front-queue Q) (car Q))
(define (rest-queue Q) (cdr Q))
(define (init-queue start) (list (list start #f 0)))

;; Enqueued node
(define (get-node-name node) (car node))
(define (get-pred-name node) (cadr node))
(define (get-distance-from-start node) (caddr node))
(define (make-queued pred node-name)
  (let ((pred-name (get-node-name pred)))
    (list node-name
          pred-name
          <compute-distance-from-start>))) ; FILL IN

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; HEURISTIC

(define (higher-priority? x y)
  x-is-in-a-better-position-than-y) ; FILL IN

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ALGORITHM

(define (enqueue node Q)
  xxx) ; FILL IN

(define (expand-node Q S node)
  xxx) ; FILL IN

(define (traverse Q S target)
  xxx) ; FILL IN

(define (retrace S)
  xxx) ; FILL IN

(define (UCS start target) (retrace (traverse (init-queue start) '() target)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (do-search start target)
  (cond ((not (assoc start  (towns-and-roads))) (list start  'is 'not 'on 'the 'map))
        ((not (assoc target (towns-and-roads))) (list target 'is 'not 'on 'the 'map))
        ((eq? target start) (list 'target '= 'start))
        (else (list 'Shortest 'path ': (UCS start target)))))


; The definitions below are placed here for easy replacement during test runs.
; Every procedure will have these definitions in its environment during run-time.

(load "sample-graph.ss")
(define *map* sample-map)
(define (towns-and-roads) (car *map*))
(define (distances) (cdr *map*))

(do-search 'a 'j)

