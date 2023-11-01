package courseSequencer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class Results implements ResultsInterface {
    ArrayList<String> results ;
    public Results(ArrayList<String> resultIn){
        results = resultIn ;
    }

    @Override
    public void writeToConsole(String strIn) {
        try{
        String errorLogPath = System.getProperty("user.dir")+"/"+"errorLog.txt" ;
        File file = new File(errorLogPath) ;
        FileWriter fileWriter = new FileWriter(file) ;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;
        bufferedWriter.write(strIn);
        bufferedWriter.write("\n") ;
        bufferedWriter.close();
        }
        catch(Exception eIn){
            ExceptionHandler.handleException(eIn, "");
        }
    }

    @Override
    public void writetoFile(String OutputFileIn) {
        try{
        File file = new File(OutputFileIn) ;
        FileWriter fileWriter = new FileWriter(file) ;
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;
        int i = 0 ;
        for(String s : results){
            bufferedWriter.write(s);
            i++ ;
            if(results.size() != i){
                bufferedWriter.write("\n");
            }
        }
        bufferedWriter.close();
        }
        catch(Exception eIn){
            ExceptionHandler.handleException(eIn, "");
        }
    }

}