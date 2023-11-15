# CS542: Assignment 3
## Name: Tejas Ravindra Hiremath

-----------------------------------------------------------------------
-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on the project.
#### Note: build.xml is present in studentCourseSequencer/src folder.
-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCourseSequencer/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCourseSequencer/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

The program accepts 3 String arguments through the command line.

####Command: ant -buildfile studentCourseSequencer/src/build.xml run -Darg0=<input_file.txt> -Darg1=<output_file.txt> -Darg2=<errorLog_file.txt>

Replace <fileName.txt> with real file names. For example, if the files are available in the path,
you can run it in the following manner:

### Command: 
ant -buildfile studentCourseSequencer/src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=errorLog.txt

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:
I have designed a Course Sequencer for Students, which assigns courses to students based on their preferences for courses which are categorized in five different groups i.e, 
Group-1: Long Programming and Design: Courses from A-D
Group-2: Data Structures and Algorithms: Courses from E-H
Group-3: Hardware Sequence: Courses from I-L
Group-4: Data Analytics: Courses from M-P
Group-5: Electives: Courses from Q-Z.

Each student can always be in any one state among five different states i.e,
StateOne - Student has taken most courses in Group-1
StateTwo - Student has taken most courses in Group-2.
StateThree - Student has taken most courses in Group-3.
StateFour - Student has taken most courses in Group-4.
StateFive - Student has taken most courses in Group-5.
StateSix- Student is Graduated.

Note that each student starts in StateOne and if graduated the program terminates with StateSix.

In any group, except Group-5, a student can only take courses in the Alphabetic order i.e he/she needs to complete the prerequisite for every course. If prerequisite is not completed, the course is pushed onto a waiting list which is processed after every semester.

To be eligible for graduation,
The student needs to complete 10 courses.
The student needs to take 2 courses per group.

I have used the State Pattern to design the solution to this problem where courseSequencer is the context class and State1,State2,State3,State4,State5 and GraduatesState are the States with actions such as updateState to change the state and isGraduated to check whether the student is graduated or not.

## Input:
<studentID>: <course> <course> <course> ... <course>

Example of input.txt file:
1234: A B C D E F G H I J K L M N O P Q R S T U V W


## Output:
The output.txt will have the following format:

<studentID>:<course completed> <course completed> ... <course completed> -- <# semesters> <# state changes>

Example of Output.txt file if the student is graduated:
1234: A E I B F J C G K D H L M Q R N -- 6 0

Example of Output.txt file if the student is not graduated:
1234: S U X E I M J F N O G K P H L R V W Q T -- 0 3
The Student does not graduate because of not completing two courses in each group!
-----------------------------------------------------------------------
### Quality of the Solution: 
This program is efficient while performing all the operations. Actions such as- processPreference, updateState and isGraduated are always performed by calling processPreference(), updateState() and isGraduated() methods of the respective states. Here all the states except Graduated State have the same implementation for each methods, therefore we have used a helper class to define these methods. But in future, if the method implementation for states change, this program is very easily scalable because of using the state pattern.

### Time Complexity: 
The total amount of time taken by the algorithm is n^2 time complexity as we are always processing a queue of waitlist courses after alloting a particular course. The worst case time complexity remains
O(n^2) where n is the number of preferences given by a student. 


### Data Structures used: 

This Algorithm uses Data Structures- (Custom DataStructure: pair), ArrayList, Array and HashMap.

I have created a pair Data Structure which has the member variables such as the (int)B-Number, (Character Array)preferences. This class is a static class therefore, accessible to every other class in the package.

We also used a HashMap(HashMap<int, CourseSequencerStateI>), which is found in java.util.HashMap package to store an integer as a key and a CourseSequencerStateI as a value for every state. Here the total size of hashmap is always 6 because there are only six groups of courses in our program. The operations performed using HashMap are get(), find() and put() which are all constant-time operation i.e O(1) time complexity which makes these operations very efficient.

The ProjectManager uses the FileProcessor API which uses a [String]Array to iterate over the id's and student preferences one by one. We used Array here, because we just need to iterate over the preferences one by one and we don't want to use any other methods. We know the length of Array which is atmost 27 i.e constant time- O(1) operation.

The Helper classes uses ArrayList<ObserverInterface>, which is found in java.util.ArrayList package to store the courses. We have only used add() and get() method of ArrayList which takes constant time i.e, O(1) time complexity.


-----------------------------------------------------------------------

### Purpose of all folders

state:
    This folder six different states i.e State1,2,3,4,5 and Graduated State class. It contains the context class i.e, courseSequencer and also it's interface- CourseSequencerStateI.

Driver:
    This folder has the Driver class which contains the main method and accepts 3 String arguments through command line. It validates the number of arguments passed and creates an instance of projectmanager and calls its run method, passing all the arguments.

projectmanager:
    This folder has the projectManager class which creates an instance of the context class- courseSequencer and calls the process preference method on it by passing the input one by one using the fileProcessor API.

utils:
    This folder contains all the utility classes- courseInfo, courseSequencerHelper which are both helper classes. Other classes are such as ExceptionHandler, FileDisplayInterface, FileProcessor, FileProcessorInterface, Pair(Custom Data Structure), Results class which has methods- writetoFile, writeToConsole. It contains the final result which is used to write to the output file.

-----------------------------------------------------------------------
### No Slack Days used for the Assignment.
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense."

Date: -- 11/14/2023