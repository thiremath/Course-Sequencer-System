run:
	ant -buildfile projectName/src/build.xml clean
	ant -buildfile projectName/src/build.xml all
	ant -buildfile projectName/src/build.xml run -Darg0= -Darg1= -Dagr2=