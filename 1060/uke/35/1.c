#include <stdio.h>

int main(int argc, char *argv[])
{
  if(argc<2)
    printf("ERROR: 1 ARGUMENT REQUIRED!\n");
  else
    {
      char *msg[255];
      *msg = argv[1];
      if(atoi(*msg))
	printf("Number: %d\n", atoi(argv[1]));
      else
	printf("String: %s\n", *msg);
    }
}
