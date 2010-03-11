;; Testprosedyre for lettere å debugge
(define (test actual expected)
  (cond ((equal? actual expected)
         (display "."))
        (else 
         
         (display "\nFAIL! Expected:")
         (display expected)
         (display "\n           was:")
         (display actual))))