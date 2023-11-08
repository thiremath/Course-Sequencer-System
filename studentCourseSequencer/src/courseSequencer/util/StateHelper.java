package courseSequencer.util;

import java.util.ArrayList;

public class StateHelper {
    public static boolean isGradEligible(ArrayList<ArrayList<Character>> courses_grpsIn){
        for(ArrayList<Character> a: courses_grpsIn){
            if(a.size() < 2){
                return false ;
            }
        }
        return true ;
    }

    public static boolean iscourseAlreadyOpted(ArrayList<Character> coursesAllotedIn, char courseIn){
        if(coursesAllotedIn.contains(courseIn)) return true;
        else return false ;
    } 

    public static boolean isCourseAllowed(ArrayList<Character> coursesAllotedIn, ArrayList<Character> semCoursesIn, char courseIn){
        if(courseIn-'A' >= 16 || (courseIn-'A') % 4 == 0){
            return true ;
        }
        else{
            int temp = 4 * ((courseIn-'A')/4) ;
            for(int i=temp+'A'; i<courseIn;i++){
                if(semCoursesIn.contains((char)i)){
                    return false ;
                }
                else if(!coursesAllotedIn.contains((char)i)){
                    return false ;
                }
            }
            return true ;
        }
    } 
    
}