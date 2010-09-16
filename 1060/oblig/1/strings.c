#include <stdlib.h>
#include <argp.h>    // Tar seg av kommandolinje-argumenter og slikt

//// Documentation
/* version, bug address */
const char *argp_program_version = "1060-h10-oblig1 0.1";
const char *argp_program_bug_address = "<oddmunds@ulrik.uio.no>";

/* main doc line */
static char doc[] = "STRINGS - String manipulation program \
                 \n\n  print \t\t Print text \
                   \n  random \t\t Print a random line \
                   \n  replace \t\t Replace vowels \
                   \n  remove \t\t Remove vowels \
                   \n  len \t\t\t Print length";

/* argument doc */
static char args_doc[] = "COMMAND INPUT_FILE";

struct arguments
{
  char *args[2];
  char *command, *input_file;
};


static struct argp argp = {0, 0, args_doc, doc};

int main(int argc, char **argv)
{
  // Parse arguments
  argp_parse(&argp, argc, argv, 0, 0, 0);


  
  exit(0);
}
