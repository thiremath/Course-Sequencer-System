package courseSequencer.projectmanager;

import courseSequencer.util.Results;
import courseSequencer.util.courseInfo;
import courseSequencer.state.courseSequencer;
import courseSequencer.util.ExceptionHandler;
import courseSequencer.util.FileProcessor;
import courseSequencer.util.Pair;

public class ProjectManager implements ProjectManagerInterface{
    public static FileProcessor inputFileProcessor ;
    String InputFile ;
    String OutputFile ;
    String errorLogFile ;

    public ProjectManager(String InputFileIn, String OutputFileIn, String errorLogFileIn){
        InputFile = System.getProperty("user.dir")+"/"+InputFileIn ;
        OutputFile = System.getProperty("user.dir")+"/"+OutputFileIn ;
        errorLogFile = System.getProperty("user.dir")+"/"+errorLogFileIn ;
        
        ExceptionHandler.errorLogFilePath = errorLogFile ;
        inputFileProcessor = new FileProcessor(InputFile, OutputFile, errorLogFile) ;

        Results.clearFile(OutputFile) ;
        Results.clearFile(errorLogFile) ;
    }

    @Override
    public void run() {
        while(!inputFileProcessor.isfileParsed){
            courseInfo CourseInfo = new courseInfo() ;
            courseSequencer sequencer = new courseSequencer(CourseInfo) ;
            FileProcessor fp = ProjectManager.inputFileProcessor ;
            processPreferences(sequencer,CourseInfo,fp);
        }        
    }

    public void processPreferences(courseSequencer sequencer,courseInfo CourseInfo, FileProcessor fp){
        try{
            Pair pair = fp.readLine() ;
            if(pair != null){
                CourseInfo.b_Number = pair.b_Number ;
                char[] prefs = pair.prefs;
                if(prefs != null){
                    for(char course: prefs){
                        if(((int)course <65 || (int)course >90) && (((int)course <97 || (int)course >122))){
                            ExceptionHandler.handleException(null, "Enter a valid Preference!");
                        }
                        char currentCourse = Character.toUpperCase(course) ;
                        if(!sequencer.isGraduated()) sequencer.registerCourse(currentCourse);
                        else break ;
                    }
                }
                StringBuilder res = getResults(CourseInfo,sequencer.isGraduated(),sequencer.NumStateChanges);
                Results r = new Results() ;
                r.writetoFile(OutputFile, res);
                System.out.println(" "+res+"\n");
            }
        }
        catch (Exception e) {
            ExceptionHandler.handleException(e, "");
        }
        // System.out.println(CourseInfo.semwiseCourses+" semwise courses");
        // System.out.println(CourseInfo.courses_grps+"courses_grps");
    }

    public StringBuilder getResults(courseInfo CourseInfo, boolean isGraduated, int NumStateChangesIn){
        StringBuilder s = new StringBuilder() ;
        s.append(String.valueOf(CourseInfo.b_Number)+" ") ;
        for(char tempChar: CourseInfo.coursesAlloted){
            s.append(String.valueOf(tempChar)) ;
            s.append(" ") ;
        }
        s.append("-- ") ;
        if(isGraduated){
            s.append(CourseInfo.semwiseCourses.size()) ;
            s.append(" "+NumStateChangesIn) ;
        }
        else{
            s.append("0") ;
            s.append(" "+NumStateChangesIn) ;
            s.append("\nThe Student does not graduate because of not completing two courses in each group!") ;
        }
        s.append("\n") ;
        return s ;
    }
}
