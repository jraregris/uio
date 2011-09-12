;; ASSIGNMENT 1a INF4820
;; Oddmund Strømme (oddmunds)

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

(position 'foo foo)
;; => 42

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
  (cond 
   ;; If the sequence is shorter than two elements it is palindromic!
   ((< (length p) 2) t)            
        
   ;; If it has two elements, it is a palindrome if they are the same.
   ((= (length p) 2)
    (equalp (symbol-name (first p)) 
            (symbol-name (first (reverse p))))) 
   
   ;; If the sequence is longer than two elements, it is palindrome if
   ;; the first and last element are palindrome with eachother...
   ((palindromep (list (first p) 
                       (first (reverse p))))
    
    ;; ...and the rest of the sequence is a palindrome.
    (palindromep (rest (reverse (rest p)))))
   ))

(palindromep '(A b l e w a s i e r e i s a w e l b a))
;; => t

;; 7a

(defun where (a l)
  (cond 
   ;; If the list is empty, return nil.
   ((null l) nil)
   ;; If the first symbol in the list matches the atom, return 0.
   ((equalp a (first l)) 0)
   ;; If none of the above apply, recursivly call WHERE again with the
   ;; rest of the list, and return it's value + 1.
   (t (add-number-or-nil 1 (where a (rest l))))))

;; The way I solved it doesn't really work on it's own because I tried
;; to add 1 and nil if the atom was not in the list at all. I solved
;; this by making a new function that adds the numbers if it is not nil,
;; and returns nil if it is. This feels hacky and unelegant, but it gets
;; the job done.
(defun add-number-or-nil (n r)
  (when r (+ n r)))

(where 'c '(a b c d- e c))
;; => 2

;; 7b

(where 'c '((a b) (c d) (e c)))
;; => nil

;; My implementation evaluates the above call to nil which I think is
;; right given the specification. If I were to support nested lists I
;; would have to make a decision on how that should be handled. Should
;; I flatten the list? Or should I return a list of position
;; numbers? (In this case (1 0).) This functionality would definitly
;; go beyond the specification and could lead to unexpected (and
;; subjectively wrong) results.

;; 8a

(defun set-union (a b)
  (cond 
    ;; If b is empty, return a.
    ((null b) a)

    ;; If first of b is not in a (checked with a call to WHERE from
    ;; the previous exercise), return SET-UNION with the first of b
    ;; appended to a and the rest of b as parameters.
    ((not (where (first b) a))
	 (set-union (append a (list (first b)))
		    (rest b)))

    ;; Else return SET-UNION with a and rest of b.
    (t (set-union a (rest b)))
    ))

(set-union '(a b c) '(d e a))
;; => (A B C D E)

;; This is not the same result as in the exercise text, but as far as
;; I can tell, the order of the list is unimportant (as these are
;; sets). I also assume that the first list is a proper set as noted
;; in the text.

(defun set-intersection (a b)
  (cond
   ;; If b is nil, return the empty set.
   ((null b) nil)

   ;; If first b is in a, return first b appended to SET-INTERSECTION
   ;; with a and the rest of b as arguments.
   ((where (first b) a)
    (append (set-intersection a (rest b)) (list (first b))))

   ;; Else return SET-INTERSECTION with a and rest of b.
   (t (set-intersection a (rest b)))
   ))


(set-intersection '(a b c) '(d e a))
;; => (A)

(defun set-subtraction (a b)
  (cond
   ;; When we run out of b, return what is left of a.
   ((null b) a)
   
   ;; If first b is in a, return SET-SUBSTRACTION with a with first b
   ;; removed and the rest of b as arguments.
   ((where (first b) a)
    (set-subtraction (remove (first b) a) (rest b)))

   ;; Else return SET-SUBTRACTION with a and the rest of b.
   (t (set-subtraction a (rest b)))
  ))

(set-subtraction '(a b c) '(d e a))
;; => (B C)

;; 9

(defun tokenize (string)
  (loop
      for start = 0 then (unless (null end) (+ end 1))
      for end = (unless (null start) (position #\space string :start start))
      while start collect (subseq string start end)))

;; 9a

(with-open-file (stream "brown.txt" :direction :input)
  (loop
      for line = (read-line stream nil)
      while line
      append (tokenize line)))

;; The return value of the above command is a list of string, each
;; being one word from the input file. There are also some empty
;; strings ("") where there were more than one space between the words
;; in the original text.

;; 9b

(setf *corpus* (with-open-file (stream "brown.txt" :direction :input)
                 (loop
                     for line = (read-line stream nil)
                     while line
                     append (tokenize line))))

;; Our current strategy for tokenizing is to plit the text between the
;; spaces. That means that everything between two spaces is a
;; word/token. This causes empty words to show up everywhere there are
;; two spaces next to each other.

(length *corpus*)
;; => 24052

;; 9c
 
(defun unique (l)
  (let ((result nil))
    (loop
        for token in l
        when (not (where token result)) ; if token is not already in the list...
        do (setf result (append result (list token)))) ; ...append it to result
     result))

(unique *corpus*)

;; 9d

(defun word-count (l)
  (let ((m (make-hash-table :test #'equalp))) ; equalp for case-insensitivity
    (loop
        for token in l
        do (if (gethash token m)
               (setf (gethash token m) (+ 1 (gethash token m)))
             (setf (gethash token m) 1)))
    m))

(setf k (word-count *corpus*))
;; => #<EQUALP hash-table with 5920 entries @ #x1000dd8ec2>

(gethash "the" k)
;; => 1781

(gethash "man" k)
;; => 10
