#include <stdio.h>

char strgetc(char s[], int pos);

int main(void)
{
  char *t[200];
  int nr;
  

  *t = "Harsj";
  nr = 9;
  

  printf("Bokstav nr %d i '%s' er: %c\n", nr, *t, strgetc(*t, nr));
  printf("%d\n", sizeof(*t));  
}

char strgetc(char s[], int pos)
{
  return s[pos-1];
}
