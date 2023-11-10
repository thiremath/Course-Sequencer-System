package courseSequencer.util;

import java.util.ArrayList;

import courseSequencer.state.courseInfo;

public class StateHelper {
    public static boolean isGradEligible(courseInfo helperIn){
        for(ArrayList<Character> a: helperIn.courses_grps){
            if(a.size() < 2){
                return false ;
            }
        }
        return true ;
    }

    public static boolean iscourseAlreadyOpted(courseInfo helperIn){
        if(helperIn.coursesAlloted.contains(helperIn.course)) return true;
        else return false ;
    } 

    public static boolean isCourseAllowed(courseInfo helperIn){
        if(helperIn.course-'A' >= 16 || (helperIn.course-'A') % 4 == 0){
            return true ;
        }
        else{
            int temp = 4 * ((helperIn.course-'A')/4) ;
            for(int i=temp+'A'; i<helperIn.course;i++){
                if(helperIn.currSemCourses.contains((char)i)){
                    return false ;
                }
                else if(!helperIn.coursesAlloted.contains((char)i)){
                    return false ;
                }
            }
            return true ;
        }
    } 
    
}