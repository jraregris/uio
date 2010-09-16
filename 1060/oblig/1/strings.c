#include <stdlib.h>
#include <argp.h>




const char *argp_program_version =     "inf1060-h10-o1 0.1";
const char *argp_program_bug_address = "<oddmunds@ulrik.uio.no>";
static char doc[] =                    "STRINGS - String manipulation program";

static struct argp argp = {0,0,0,doc};



int main(int argc, char **argv)
{
  // Parse arguments
  argp_parse(&argp, argc, argv, 0, 0, 0);
  exit(0);
}
