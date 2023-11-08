package courseSequencer.util;
import courseSequencer.util.Results;

public class ExceptionHandler {

    public static Results errorLogProcessor = new Results();
    public static String errorLogFilePath ;

    public static void handleException(Exception eIn, String eMsgIn){

        String eMsg;
        if (eMsgIn.isEmpty()){
            eMsg = eIn.getMessage();
        }
        else {
            eMsg = eMsgIn;
        }

        StringBuilder eString = new StringBuilder() ;
        eString.append("EXCEPTION: " + eMsg);

        if(errorLogFilePath != null){
            errorLogProcessor.writetoFile(errorLogFilePath,eString);
        }
        errorLogProcessor.writeToConsole(eString);

        if (eIn != null){
            eIn.printStackTrace();
        }
        
        System.exit(0);
    }
}