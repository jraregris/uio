;; A map is a pair (T . R)
;; where T is a list of towns with map-coordinates and neighbor lists.
;; and R is a list of neighbor pairs and corresponding  road lengths.

(define wine-district
  '(((a (55.4 . 71.9) b c d e)
     (b (52.6 . 71.7) a c)
     (c (51.0 . 69.9) a b f)
     (d (58.4 . 69.9) a e h)
     (e (58.1 . 71.9) a d)
     (f (53.3 . 69.0) c g i)
     (g (56.0 . 68.2) f h i j) 
     (h (56.2 . 65.6) d g j)
     (i (51.9 . 65.1) f g j)
     (j (54.1 . 64.6) g h i))
    ((a . b) . 3.)
    ((a . c) . 6.4)
    ((a . d) . 7.9)
    ((a . e) . 3.0)
    ((b . c) . 3.2)
    ((c . f) . 3.1)
    ((d . e) . 2.9)
    ((d . h) . 11.6)
    ((f . g) . 2.9)
    ((f . i) . 8.5)
    ((g . h) . 3.2)
    ((g . i) . 5.8)
    ((g . j) . 8.1)
    ((h . j) . 3.1)
    ((i . j) . 3.0)))