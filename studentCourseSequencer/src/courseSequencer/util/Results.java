package courseSequencer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import courseSequencer.util.ExceptionHandler;

public class Results implements ResultsInterface {

    @Override
    public void writeToConsole(StringBuilder strIn) {
        System.out.println(strIn);
    }

    @Override
    public void writetoFile(String OutputFileIn, StringBuilder sbIn) {
        try{
            File file = new File(OutputFileIn) ;
            FileWriter fileWriter = new FileWriter(file,true) ;
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;
            String s = String.valueOf(sbIn) ;
            bufferedWriter.write(s);
            bufferedWriter.close();
        }
        catch(Exception eIn){
            ExceptionHandler.handleException(eIn, "");
        }
    }

    public static void deleteFile(String fileName) {
        try {
            File file = new File(fileName); 
            FileWriter f = new FileWriter(file) ;
            BufferedWriter bufferedWriter = new BufferedWriter(f) ;
            bufferedWriter.write("") ;
            System.out.println("Cleared the file: "+file.getName());
            bufferedWriter.close();
        } catch (Exception eIn) {
            ExceptionHandler.handleException(eIn, "");
        }
        //   if (file.delete()) { 
        //     System.out.println("Deleted the file: " + file.getName());
        //   } 
    }

}