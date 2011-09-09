default: part1 part2

debug: part1.c part2.c functions.h
	gcc -ggdb part1.c functions.h -o 1
	gcc -ggdb part2.c -o 2

part1: part1.c functions.h
	gcc part1.c functions.h -o 1

part2: part2.c
	gcc part2.c -o 2

clean: 
	rm 1
	rm 2
