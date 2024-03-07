# Bash

## Create your first bash script

1. find your bash shell
`which bash`
2. write command in .sh file
In my case, the path is /usr/bin/bash

```bash
#! /usr/bin/bash

echo "Hello World"#/usr/bin/bash
echo "Today is" `date`
echo -e "\nenter the path to directory"
read the_path
echo -e "\n you path has the following files and folders: "
ls $the_path

```
3. Provide execution right

`chmod u+x hello_bash.sh`

4. Run the script
`./hello_bash.sh`

## The Basic Syntax
0. first line !# (shebang) - to specify an interpreter that executes commands present in the file.
1. comments in bash scripting - by using #
2. variables and data typesin bash - there are no data types in Bash, in Bash, to access variable value by using $ + variable name
3. Command line arguments - 
   in a bash script or function, $1 denotes the initial argument passed, $2 for the second, ....
4. Conditional Statements (if/else)
   ```bash
   if [[condition]];
   then
       statement
    elif [[condition]]; then
       statement
    else
       do this by default
    fi
   ```

