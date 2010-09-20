#include <stdio.h>

int main(void)
{
  FILE *f;
  int i;
  
  f=fopen("potens.tab","w");
  if(f==NULL)
    {
      printf("Umulig å skrive til potens.tab\n");
      return 1;
    }
  
  fprintf(f, " n  n2  n3\n");
  for(i=0;i<=10;++i)
    {
      fprintf(f, "%2d %3d %4d\n", i, i*i, i*i*i);
    }
  
  fclose(f);
  return 0;
}
