package courseSequencer.projectmanager;

import courseSequencer.util.Results;
import courseSequencer.util.FileProcessor;

public class ProjectManager implements ProjectManagerInterface{
    private FileProcessor inputFileProcessor ;
    String InputFileOne ;
    String InputFileTwo ;
    String OutputFile ;

    public ProjectManager(String InputFileOneIn, String InputFileTwoIn, String OutputFileIn){
        InputFileOne = System.getProperty("user.dir")+"/"+InputFileOneIn ;
        InputFileTwo = System.getProperty("user.dir")+"/"+InputFileTwoIn ;
        OutputFile = System.getProperty("user.dir")+"/"+OutputFileIn ;
        inputFileProcessor = new FileProcessor(InputFileOne, InputFileTwo) ;
    }

    @Override
    public void run() {
        // Algorithm algorithm = new Algorithm(inputFileProcessor) ;
        // algorithm.Compute() ;
        // Results results = new Results(algorithm.result) ;
        // results.writetoFile(OutputFile);
    }
}
