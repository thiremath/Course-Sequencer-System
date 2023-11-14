package courseSequencer.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor implements FileProcessorInterface{

    public static String InputFile; 
    String OutputFile;
    String errorLogFile; 
    public Boolean isfileParsed = false;
    Scanner s ;
    Pair pair ;


    public FileProcessor(String InputFileIn, String OutputFileIn, String errorLogFileIn){
        InputFile = InputFileIn ;
        OutputFile = OutputFileIn ;
        errorLogFile = errorLogFileIn ;

        try{
            File myobj = new File(InputFile) ;
            Scanner myReader = new Scanner(myobj);
            if(!myReader.hasNextLine()){
                isfileParsed = true ;
                System.err.println("The Input file is empty!");
                System.exit(0);
            }
            s = myReader ;
        }
        catch(FileNotFoundException e){
            System.err.println("Input File not found!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public Pair readLine() {
        pair = new Pair() ;
        
        String[] arraStrings = new String[2];
        String line = s.nextLine();
        if(!line.isEmpty()){
            arraStrings = line.replace(" ","").split(":") ;

            try{
                pair.b_Number = Integer.parseInt(arraStrings[0]) ;
                pair.prefs = arraStrings[1].toCharArray();
            }
            catch(Exception e){
                ExceptionHandler.handleException(e,"") ;       
            }

            if(!s.hasNextLine()){
                isfileParsed = true ;
                s.close();
            }
        }
        else{
            isfileParsed = true ;
            return null ;
        }

        return pair ;
    }
}
