default: strings compress

compress: compress
	gcc compress.c -o compress

strings: strings.c stringsfun.h
	gcc strings.c stringsfun.h -ggdb -o strings

clean: 
	rm strings
	rm compress
