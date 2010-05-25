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
        ((hascar? (car neighborslist) S) (append-unvisited Q S (cdr neighborslist) node)) ;If S has first in ns, continue with next in ns
        (else
         (append-unvisited (append Q (list (list (car neighborslist) node))) S (cdr neighborslist) node)))) ; Else: append first in ns til end of Q, and continue with next in ns

                                        ; Q: queue of untraversed S:stack of traversed
(define (traverse Q S target-name)
  (cond ((eq? (caar Q) target-name) ; (t.2) Hvis n = mål
         (append (cons (car Q) S)))           ; (t.3) Legger vi (n,f) på S og returnerer S
        ((does_list_of_pairs_have_town? (caar Q) S)
         (traverse (cdr Q) S target-name))
        (else
         (traverse (expand-node (cdr Q) S (caar Q)) (append (list (car Q)) S) target-name))
        ))

                                        ; Helpers
(define (has? node list)
  (cond ((eq? list '()) #f)
        ((eq? node (car list)) #t)
        (else (has? node (cdr list)))))

(define (hascar? node list)
  (cond ((eq? list '()) #f)
        ((eq? node (car (car list))) #t)
        (else (hascar? node (cdr list)))))

(define (does_list_of_pairs_have_town? town list)
  (cond ((eq? list '()) #f)
        ((has? town (car list)) #t)
        (else (does_list_of_pairs_have_town? town (cdr list)))))

(define (retrace S)       ; T now begins with target and ends with start
  (retrace2 S (list (caar S)) (cadr (car S))))

(define (retrace2 S P pred)
  
  
  (cond ((eq? pred #f)
         P)
        
        ((eq? (cdr (car S)) #f)
         (append P (list (cdr S))))
        
        ((eq? (car (car S)) pred)
         (retrace2 
          (cdr S) 
          (append (list (caar S)) P) 
          (cadr (car S))))
        
        (else (retrace2 
               (cdr S) 
               P 
               pred))))

(define (BFS start target) (retrace (traverse (init-queue start) '() target))) 

         ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; RUN
(define (do-search start target)
  (cond ((not (assoc start  (towns-and-roads))) (list start  'is 'not 'on 'the 'map))
        ((not (assoc target (towns-and-roads))) (list target 'is 'not 'on 'the 'map))
        ((eq? target start) (list 'target '= 'start))
        (else (list 'Shortest 'path: (BFS start target)))))


         ;;;;;;;;;;;;;;;;;;;;
;;tests

                                        ; Load test "framework"
(load "test.ss")

                                        ; Configure BFS

(load "Graaf.ss")
(define *map* graaf)
(define (towns-and-roads) (car *map*))

(map do-search
     '(Ali Ali Ali Åge Ozi)
     '(Jiz Tlö Wot Mur Hur))

                                        ;Resultater for BFS:

                                        ;((Shortest path Ali -> Jiz = (Ali Ben Jiz))
                                        ; (Shortest path Ali -> Tlö = (Ali Ben Ibe Liu Rok Tlö))
                                        ; (Shortest path Ali -> Wot = (Ali Ben Ibe Liu Rok Tlö Wot))
                                        ; (Shortest path Åge -> Mur = (Åge Ægi Yli Wot Tlö Rok Mur))
                                        ; (Shortest path Ozi -> Hur = (Ozi Faz Dil Hur)))

                                        ;Resultater for UCS og A*:

                                        ;((shortest path Ali -> Jiz = ((Ali Ben Jiz) . 63))
                                        ;(shortest path Ali -> Tlö = ((Ali Coz Dil Faz Nil Mur Rok Tlö) . 149))
                                        ;(shortest path Ali -> Wot = ((Ali Coz Dil Faz Ozi Pel Uqq Wot) . 157))
                                        ;(shortest path Åge -> Mur = ((Åge Ørt Zot Wot Uqq Rok Mur) . 106))
                                        ;(shortest path Ozi -> Hur = ((Ozi Faz Gol Hur) . 79)))
