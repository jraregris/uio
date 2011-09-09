;; ASSIGNMENT 1 

;; 2a
(first (rest '(apple orange pear lemon)))
;; => ORANGE

;; 2b
(last (first '((apple orange) (pear lemon))))
;; => ORANGE

;; 2c
(first (first (rest '(apple (orange) ((pear (lemon)))))))
;; => ORANGE

;; 3a
(setf foo (list 42))

(first foo)
;; => 42

;; 3b
(setf foo 20)

(* (+ foo 1) 2)
;; => 42

;; 3c
(setf foo '(list bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar
            bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar 
            bar bar bar bar bar bar bar bar foo))
(position 'foo foo) => 42

;; 3d
(setf foo '(((bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar
                  bar bar bar bar bar bar bar bar bar bar bar bar bar bar bar
                  bar bar bar bar bar bar bar bar bar bar bar bar)
             (bar))))

(length (rest (first (first (reverse foo)))))
;; => 42

;; 4a
(setf *foo* '(a b c d e f g h i j k l m n o p q r s t u v w x y z))

(first (rest (reverse *foo*)))
;; => Y (next to last element)

;; 4b

;; No.

;; 5
(defun ? (?)                            ; Defines a function called
                                        ; '?' with one parameter (also
                                        ;  called '?').

  (if (consp ?)                         ; Forks the program flow,
                                        ; checks if the parameter '?'
                                        ; is a cons-cell.
  
      (cons                             ; If the above checks returns
                                        ; true, a new cons cell is
                                        ; created.

       (? (first ?))                    ; as the first cell, a
                                        ; recursive call to ?
                                        ; (thefunction) with the first
                                        ; cell of ? (the parameter) as
                                        ; parameter.

       (? (rest ?)))                    ; as the 'rest', a recursive
                                        ; call to ? (the function)
                                        ; with the 'rest' of ? (the
                                        ; parameter) as parameter.

    ?))                                 ; If the parameter '?' is not
                                        ; a cons cell, the parameter
                                        ; '?' itself will be returned.

;; The result of this function is that a clone of the parameter '?'
;; will be recursively built and returned. The use of '?' for both the
;; function name and the parameter is probably an intentional
;; obfuscation of the code. If there is a left parenthesis directly
;; before any symbol, it's a function, if not it's a variable or literal.

(? '(bashful (doc (dopey)) grumpy (happy sleepy sneezy)))
;; => (BASHFUL DOC DOPEY GRUMPY HAPPY SLEEPY SNEEZY)

;; 6

;; First I thought out a recursive way of solving this problem. But
;; then it dawned on me that this could be solved a lot simpler as below.

(defun palindromep (p)
  (if (equal p (reverse p)) t ))

(palindromep '(a b l e w a s i e r e i s a w e l b a))
;; => t

;; But I also noticed that the example in the exercise is
;; case-insensitive.

(palindromep '(A b l e w a s i e r e i s a w e l b a))
;; => nil

;; So I guess I'll go with my first way of doing this after all.

(defun palindromep (p)
  (cond ((< (length p) 2) t)
        ((= (length p) 2)
         (equalp (symbol-name (first p)) (symbol-name (first (reverse p)))))
        ((palindromep (list (first p) (first (reverse p))))
         (palindromep (rest (reverse (rest p)))))
        ))

(palindromep '(A b l e w a s i e r e i s a w e l b a))

;; => t

(palindromep '(a b a))

(equalp (symbol-name 'a) (symbol-name 'A))
(defun palindromep (p)
  (cond (< (length p) 2) (t)))

(palindromep '(1))



       



