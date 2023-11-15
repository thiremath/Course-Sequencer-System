package courseSequencer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import courseSequencer.state.CourseSequencerStateI;
import courseSequencer.state.courseSequencer;

public class courseSequencerHelper {
    Queue<Character> queue ;
    courseSequencer sequencer ;
    courseInfo CourseInfo ;
    boolean isGraduated ;
    public HashMap<Integer,CourseSequencerStateI> map ;

    public courseSequencerHelper(courseSequencer sequencerIn, courseInfo CourseInfoIn){
        sequencer = sequencerIn ;
        CourseInfo = CourseInfoIn ;
        queue = new LinkedList<>() ;
        isGraduated = false ;
        map = new HashMap<>() ;
    }

    public void createStateMap(CourseSequencerStateI State1,CourseSequencerStateI State2,CourseSequencerStateI State3,CourseSequencerStateI State4,CourseSequencerStateI State5,CourseSequencerStateI GraduatedState){
        map.put(0,State1) ;
        map.put(1,State2) ;
        map.put(2,State3) ;
        map.put(3,State4) ;
        map.put(4,State5) ;
        map.put(5,GraduatedState) ;
    }

    public CourseSequencerStateI getState(int i){
        return map.get(i) ;
    }

    public void processPreference(char courseIn){
            processWaitlist();

            CourseInfo.course = courseIn ;
            allotCourse() ;

            processWaitlist();
    }

    public void processWaitlist(){
        int counter = 1 ;
        while(counter-- != 0){
            if(CourseInfo.currSemCourses.size() == 3 && !isGraduated){
                CourseInfo.currSemCourses = new ArrayList<>();
                int qsize = queue.size() ;
                while(qsize-- != 0){
                    if(CourseInfo.currSemCourses.size() != 3){
                        CourseInfo.course = queue.remove() ;
                        allotCourse();
                        if(isGraduated){
                            break ;
                        }
                    }
                    if(CourseInfo.currSemCourses.size() == 3){
                        counter++ ;
                    }
                }
            }
        }
    }

    public void allotCourse(){
        if(!isGraduated){
            if(!iscourseAlreadyOpted()){
                if(isCourseAllowed()){
                    addCourse();
                    sequencer.checkState(CourseInfo);
                    if(isGradEligible()){
                        isGraduated = true ;
                        sequencer.state = sequencer.getState(5) ;
                        return ;
                    }
                }
                else{
                    queue.add(CourseInfo.course) ;
                }
            }
            else{
                System.out.println(CourseInfo.course+" is already Opted.");
                ExceptionHandler.handleException(null, "Course-"+CourseInfo.course+" is already Opted.");
            }
        }
    }

    public void addCourse(){
        CourseInfo.currSemCourses.add(CourseInfo.course) ;
        CourseInfo.coursesAlloted.add(CourseInfo.course) ;
        if(CourseInfo.semwiseCourses.size() != 0){
            if(CourseInfo.semwiseCourses.get(CourseInfo.semwiseCourses.size()-1).size() != 3){
                CourseInfo.semwiseCourses.get(CourseInfo.semwiseCourses.size()-1).add(CourseInfo.course) ;
            }
            else{
                CourseInfo.semwiseCourses.add(new ArrayList<>(){{add(CourseInfo.course);}}) ;
            }
        }
        else{
            CourseInfo.semwiseCourses.add(new ArrayList<>(){{add(CourseInfo.course);}}) ;
        }

        if(((CourseInfo.course - 'A') / 4) >= 5){
            CourseInfo.courses_grps.get(4).add(CourseInfo.course) ;
        }
        else{
            CourseInfo.courses_grps.get((CourseInfo.course - 'A') / 4).add(CourseInfo.course) ;
        }
    }

    public boolean isGradEligible(){
        for(ArrayList<Character> a: CourseInfo.courses_grps){
            if(a.size() < 2){
                return false ;
            }
        }
        return true ;
    }

    public boolean iscourseAlreadyOpted(){
        if(CourseInfo.coursesAlloted.contains(CourseInfo.course)) return true;
        else return false ;
    } 

    public boolean isCourseAllowed(){
        if(CourseInfo.course-'A' >= 16 || (CourseInfo.course-'A') % 4 == 0){
            return true ;
        }
        else{
            int temp = 4 * ((CourseInfo.course-'A')/4) ;
            for(int i=temp+'A'; i<CourseInfo.course;i++){
                if(CourseInfo.currSemCourses.contains((char)i)){
                    return false ;
                }
                else if(!CourseInfo.coursesAlloted.contains((char)i)){
                    return false ;
                }
            }
            return true ;
        }
    } 

}