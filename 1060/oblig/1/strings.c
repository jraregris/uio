#include <stdio.h>
#include <stdlib.h>

#include "stringsfun.h"

//// Documentation
#define VERSION "1060-h10-oblig1 0.1"
#define AUTHOR "<oddmunds@ulrik.uio.no>"

/* main doc line */
static char doc[] = "STRINGS - String manipulation program \
                 \n\n  print \t\t Print text \
                   \n  random \t\t Print a random line \
                   \n  replace \t\t Replace vowels \
                   \n  remove \t\t Remove vowels \
                   \n  len \t\t\t Print length";

void usage(char arg[])
{
  printf("USAGE: %s command input_file\n", arg);
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
  if(argc<3)
    {
      usage(argv[0]);
    }
  else 
    {
      
    }
  
  exit(0);
}
