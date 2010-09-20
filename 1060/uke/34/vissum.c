#include <stdio.h>

extern int sum(int n);

int main(void)
{
  int i;
  for(i=1;i<=10;++i)
    printf("%2d:%4d\n",i,sum(i));
}
