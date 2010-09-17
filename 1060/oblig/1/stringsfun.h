#include <stdio.h>

struct node 
{
  struct node *next;
  char *data;
};

  

void doprint(char file[])
{
  printf("PRINT!");
  
}
void dorandom(char file[])
{
  printf("RANDOM");
  
}
void doreplace(char file[])
{
  printf("REPLACE!");
  
}
void doremove(char file[])
{
  printf("REMOVE!");
  
}
void dolen(char file[])
{
  printf("LEN!");
  
}

