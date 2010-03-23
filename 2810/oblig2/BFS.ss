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
  (append-unvisited Q S (lookup-neighbors node) node))

(define (append-unvisited Q S neighborslist node)
  (cond ((null? neighborslist) Q) ; If nodelist is empty return the list
        ((has? (car neighborslist) S) (append-unvisited Q S (cdr neighborslist) node)) ;If S has first in ns, continue with next in ns
        (else
         (append-unvisited (append Q (list (list (car neighborslist) node))) S (cdr neighborslist) node)))) ; Else: append first in ns til end of Q, and continue with next in ns

; Q: queue of untraversed S:stack of traversed
(define (traverse Q S target-name)
  (cond ((eq? (caar Q) target-name) ; (t.2) Hvis n = mål
         (append (cons (car Q) S)))           ; (t.3) Legger vi (n,f) på S og returnerer S
        ((has? (car Q) S)
         (traverse (cdr Q) S target-name))
        (else
         (traverse (expand-node (cdr Q) S (caar Q)) (append (list (caar Q)) S) target-name))
        ))

; Helpers
(define (has? node list)
  (cond ((eq? list '()) #f)
        ((eq? node (car list)) #t)
        (else (has? node (cdr list)))))

(define (retrace S)       ; T now begins with target and ends with start
  (retrace2 S '()))

(define (retrace2 S P)
  (display S)
  (display "\n")
  (cond ((eq? (cdr (car S)) #f)
         (append P (list (cdr S))))
        (else (retrace2 (cdr S) (append P (list (cadr S)))))))

(define (BFS start target) (retrace (traverse (init-queue start) '() target))) 

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; RUN
(define (do-search start target)
  (cond ((not (assoc start  (towns-and-roads))) (list start  'is 'not 'on 'the 'map))
        ((not (assoc target (towns-and-roads))) (list target 'is 'not 'on 'the 'map))
        ((eq? target start) (list 'target '= 'start))
        (else (list 'Shortest 'path: (BFS start target)))))
