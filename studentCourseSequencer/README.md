# CS-

### Author
### Tejas Ravindra Hiremath - thiremath@binghamton.edu

-----------------------------------------------------------------------
-----------------------------------------------------------------------

## Programming Language - Java
## Build System - ant (available on remote.cs already)

-----------------------------------------------------------------------
## ANT Commands 
 **Note: build.xml is present in projectName/src folder.**


#### Instruction to clean:

 **Command: ant -buildfile projectName/src/build.xml clean**
Description: It cleans up all the .class files that were generated when you
compiled your code.

#### Instruction to compile:

 **Command: ant -buildfile projectName/src/build.xml all**

Description: Compiles your code and generates .class files inside the BUILD folder.

#### Instruction to run:
 **Command: ant -buildfile projectName/src/build.xml run -Darg0=InputFile1.txt -Dagr1=InputFile2.txt -Darg2=OutputFile.txt**


Note : all the input/output files are expected to be at the level of the src/ directory. Example:
projectName/src
projectName/market-price.txt

-----------------------------------------------------------------------