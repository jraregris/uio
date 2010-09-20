#include <stdio.h>
#include <stdlib.h>

struct node 
{
  struct node *next;
  char *data;
};

struct node *head = NULL;  

void makelist(struct node *head, char file[]){
  
  FILE *f;

//  struct node *this;
//  struct node *next;

  //  next = malloc(sizeof(struct node));
  //this->data = nextline();
  //this->next = mallox(sizeof(struct node));

  
}


void doprint(char file[])
{
  printf("PRINT!");
  makelist(head, file);
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

