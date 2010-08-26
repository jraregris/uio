#include <stdio.h>

int a, b, c;

int main(void)
{
  printf("Skriv to tall:");
  scanf("%d", &a);
  scanf("%d", &b);
  c = a + b;
  printf("Summen er %d.\n",c);
  
  printf("I adress %08x ligger a med verdien %d.\n", &a, a);
  printf("I adress %08x ligger b med verdien %d.\n", &b, b);
  printf("I adress %08x ligger c med verdien %d.\n", &c, c);
}
