package courseSequencer.projectmanager;

// import backupSystem_StudentRecords.bstBuilder.BSTBuilder;
import courseSequencer.util.Results;

import java.util.zip.CRC32;

import courseSequencer.state.CourseSequencerStateI;
import courseSequencer.state.courseInfo;
import courseSequencer.state.courseSequencerHelper;
import courseSequencer.state.courseSequencer;
import courseSequencer.util.ExceptionHandler;
import courseSequencer.util.FileProcessor;
import courseSequencer.util.Pair;

public class ProjectManager implements ProjectManagerInterface{
    public static FileProcessor inputFileProcessor ;
    String InputFile ;
    String OutputFile ;
    String errorLogFile ;
    int Debug_Level ;
    int Update_Value ;
    public static StringBuilder results = new StringBuilder() ;

    public ProjectManager(String InputFileIn, String OutputFileIn, String errorLogFileIn, String Debug_LevelIn, String Update_ValueIn){
        InputFile = System.getProperty("user.dir")+"/"+InputFileIn ;
        OutputFile = System.getProperty("user.dir")+"/"+OutputFileIn ;
        errorLogFile = System.getProperty("user.dir")+"/"+errorLogFileIn ;
        
        ExceptionHandler.errorLogFilePath = errorLogFile ;
        try{
            // Debug_Level = Integer.parseInt(Debug_LevelIn) ;
            // Update_Value = Integer.parseInt(Update_ValueIn) ;
        }
        catch(NumberFormatException e){
            ExceptionHandler.handleException(e,"") ;
        }

        inputFileProcessor = new FileProcessor(InputFile, OutputFile, errorLogFile, Debug_Level, Update_Value) ;

        // Results.deleteFile(OutputFile) ;
        // Results.deleteFile(errorLogFile) ;
    }

    public void writeToResults(String sIn){
        results.append(sIn) ;
    }

    @Override
    public void run() {
        // BSTBuilder bstBuilder = new BSTBuilder() ;
        // Results res = new Results() ;

        // bstBuilder.insert() ;
        // writeToResults("Inorder Traversal\n");
        // bstBuilder.inorder();
        
        // writeToResults("Sum of all the B-Numbers in each tree\n");
        // bstBuilder.sum();

        // bstBuilder.update(Update_Value) ;
        // writeToResults("\n\nSum of all the B-Numbers after increment\n");
        // bstBuilder.sum();

        // res.writetoFile(OutputFile,results);
        // System.out.println(results);

        courseInfo CourseInfo = new courseInfo() ;
        courseSequencer c = new courseSequencer(CourseInfo) ;
        StringBuilder s = new StringBuilder() ;
        FileProcessor fp = ProjectManager.inputFileProcessor ;
        Pair pair = fp.readLine() ;
        int b_Number = pair.b_Number ;
        char[] prefs = pair.prefs;
        if(prefs != null){
            for(char course: prefs){
                c.registerCourse(course);
                if(c.getCurrState() == c.getState(5)){
                    break ;
                }
            }
        }
        s.append(String.valueOf(b_Number)+" ") ;
        for(char tempChar: CourseInfo.coursesAlloted){
            s.append(String.valueOf(tempChar)) ;
            s.append(" ") ;
        }
        s.append("-- ") ;
        if(c.isGradEligible(CourseInfo)){
            s.append(CourseInfo.semwiseCourses.size()) ;
            s.append(" "+c.NumStateChanges) ;
        }
        else{
            s.append("0") ;
            s.append(" "+c.NumStateChanges) ;
            s.append("\n The Student Does not Graduate!!") ;
        }
        System.out.println(CourseInfo.semwiseCourses+" semwise courses");
        System.out.println(" "+s+"\n");
        System.out.println(CourseInfo.courses_grps+"courses_grps");
    }
}
