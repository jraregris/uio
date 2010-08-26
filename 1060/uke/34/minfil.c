#include <stdio.h>
#include <stdlib.h>

int main(void)
{
  FILE *f;
  int nc[256], i, c;
  
  for(i=0;i<=255;++i)
    nc[i]=0;
  f=fopen("minfil.c","r");
  if(f==NULL)
    {
      printf("Kan ikke lese 'minfil.c'!\n");
      exit(1);
    }
  c=fgetc(f);
  while(c!=EOF)
    {
      ++nc[c];
      c=fgetc(f);
    }
  fclose(f);
  for(i=0;i<=255;++i)
    {
      if(nc[i]>0)
	{
	  if((32 <= i && i <= 126) || 161 <=i)
	    printf("'%c'",i);
	  else
	    printf("%3d", i);
	  printf(":%4d\n", nc[i]);
	  
	}
    }
  return 0;
}
