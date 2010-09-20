#include <stdio.h>

int main(void)
{
  int v, *p;
  p = &v;
  
  v = 7;
  printf("v = %d, *p = %d.\n", v, *p);
}

  
