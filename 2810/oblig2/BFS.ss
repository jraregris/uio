;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ABSTRACTION

;; Unexpanded node
(define (lookup-neighbors nodenavn) (cddr (assoc nodenavn (towns-and-roads))))

;; Queue
(define (front-queue Q) (car Q))
(define (rest-queue Q) (cdr Q))
(define (init-queue start) (list (list start #f)))

;; Enqueued node
(define (get-node-name node) (car node))
(define (get-pred-name node) (cadr node))
(define (make-queued pred node-name) (list node-name (get-node-name pred)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ALGORITHM

(define (expand-node Q S node)
  '())


; Q: queue of untraversed S:stack of traversed
(define (traverse Q S target-name)
  (cond ((has? (car Q) S) #f)
        ((eq? (car (front-queue Q)) target-name) ; If n == target (t.2)
         )    
        
        )
  S)

(define (has? node list)
  (cond ((eq? list '()) #f)
        ((eq? node (car list)) #t)
        (else (has? node (cdr list)))))

;  ; Is target first neighbor?
;  (cond ((eq? (car (lookup-neighbors (caar Q)))
;              target-name)
;         ; then return a list of n and target
;        (list (caar Q) target-name)))         




(define (retrace S)                       ; T now begins with target and ends with start
   S)

(define (BFS start target) (retrace (traverse (init-queue start) '() target))) 

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; RUN
(define (do-search start target)
  (cond ((not (assoc start  (towns-and-roads))) (list start  'is 'not 'on 'the 'map))
        ((not (assoc target (towns-and-roads))) (list target 'is 'not 'on 'the 'map))
        ((eq? target start) (list 'target '= 'start))
        (else (list 'Shortest 'path: (BFS start target)))))
