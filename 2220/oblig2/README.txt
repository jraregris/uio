README.txt


This file contains a small howto for Eclipse and Netbeans,
i.e., an explanation of how to get things up and running
with these two IDE's.

Note that regular editors will work fine as well, if you
want to use Emacs, it can be a good thing to get a treeview
up and running, since path-names are long. (M-x speedbar)

If you would like to use Vim I'd be glad to help you out.


-- bjarneh@ifi.uio.no


--------------------------------------HOWTO Eclipse-------------------------


Eclipse:

    Unpack the source into a folder, for instance: /home/olanormann/oblig2

    File-> New Project -> Java Project
    
    Project Name: inf2220

    Create Project From Existing Source -> Browse to source folder

    in this example that would be: /home/olanormann/oblig2

    -> 'Next'

    Default output folder says: 'inf2220/bin'

    Change this to 'inf2220/build/classes'   

    Open workspace view

    Right-click on the build.xml -> Run as -> Ant build

    This should compile your source code.


    Note that running the Main class from the Eclipse Run menu will
    produce output inside the console at the bottom, which has problems
    displaying escape sequences, so you'll see some strange tokens
    popping up, if you run this application on a Linux machine.



---------------------------------end HOWTO Eclipse-------------------------



---------------------------------HOWTO NetBeans----------------------------

Since Netbeans is the default IDE from Sun, I'll give a quick intro
to this IDE as well, I know that this IDE is less common than Eclipse,
but it is used in many large scale Java projects.



Netbeans:

    Unpack the source into a folder, for instance: /home/olanormann/oblig2

    File -> New Project -> Java Project With Existing Sources

    Project Name: inf2220

    Project Folder: /home/olanormann/oblig2
    
    -> 'Next' -> 'Next' -> 'Finish'

    goto:
        Files -> select build.xml -> run target -> jar


------------------------------end HOWTO NetBeans----------------------------




You can start by looking at the graph package: 
        
    no.uio.ifi.inf2220.iinstaller.graph

this is where you will be doing most of the work.
It only contains two classes, and by implementing the
needed functionality inside these, the installer should 
work as intended.

