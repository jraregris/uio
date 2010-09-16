#include <stdlib.h>
#include <argp.h>    // Tar seg av kommandolinje-argumenter og slikt

#define VERSION     "0.1"
#define BUG_ADDRESS "<oddmunds@ulrik.uio.no>"

const char *argp_program_version = VERSION;
const char *argp_program_bug_address = BUG_ADDRESS;
static char doc[] =                    "STRINGS - String manipulation program";

static struct argp argp = {0,0,0,doc};



int main(int argc, char **argv)
{
  // Parse arguments
  argp_parse(&argp, argc, argv, 0, 0, 0);


  
  exit(0);
}
