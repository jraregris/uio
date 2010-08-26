#include <stdio.h>
int inGlobal;

int main(void)
{
  int inLocal;
  int outLocalA;
  int outLocalB;
  
  inLocal = 2;
  inGlobal = 3;
  
  outLocalA = inLocal++ & ~inGlobal;
  outLocalB = (inLocal + inGlobal) - (inLocal - inGlobal);
  
  printf("The results are : outLocalA = %d, outLocalB = %d\n",
         outLocalA, outLocalB);
}

