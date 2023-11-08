run:
	ant -buildfile studentCourseSequencer/src/build.xml clean
	ant -buildfile studentCourseSequencer/src/build.xml all
	ant -buildfile studentCourseSequencer/src/build.xml run -Darg0=bstInput.txt -Darg1=bstOutput.txt -Darg2=errorLog.txt -Darg3=2 -Darg4=1
	ant -buildfile studentCourseSequencer/src/build.xml clean