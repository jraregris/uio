#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "functions.h"

//// Documentation
#define VERSION "1060-h10-oblig1 0.1"
#define AUTHOR "<oddmunds@ulrik.uio.no>"

void usage(char arg[])
{
  printf("USAGE: %s command input_file\n\n", arg);
  printf("where \"command\" is one of the following:\n");
  printf("  print      print input_file\n");
  printf("  random     print a random line\n");
  printf("  replace    replace the vowels with all the other vowels\n");
  printf("  remove     remove vowels\n");
  printf("  len        print the number of characters in input_file\n");
}


int main(int argc, char **argv)
{
  // Parse arguments
  if(argc<2)
    {
      usage(argv[0]);
    }
  else 
    {
      if(strcmp(argv[1], "print")==0)
        {
          doprint(argv[2]);
        }
      else if(strcmp(argv[1], "random")==0)
        {
          dorandom(argv[2]);
        }
      else if(strcmp(argv[1], "replace")==0)
        {
          doreplace(argv[2]);
        }
      else if(strcmp(argv[1], "remove")==0)
        {
          doremove(argv[2]);
        }
      else if(strcmp(argv[1], "len")==0)
        {
          dolen(argv[2]);
        }
      else
        {
          usage(argv[0]);
        }
    }
  exit(0);
}
