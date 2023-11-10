package courseSequencer.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import courseSequencer.projectmanager.ProjectManager;
import courseSequencer.util.FileProcessor;
import courseSequencer.util.Pair;

public class courseSequencerHelper {
    Queue<Character> queue ;
    courseSequencer sequencer ;
    courseInfo CourseInfo ;
    HashMap<Integer,CourseSequencerStateI> map ;

    public courseSequencerHelper(courseSequencer sequencerIn, courseInfo CourseInfoIn){
        sequencer = sequencerIn ;
        CourseInfo = CourseInfoIn ;
        queue = new LinkedList<>() ;
        map = new HashMap<>() ;
        map.put(0,sequencerIn.State1) ;
        map.put(1,sequencerIn.State2) ;
        map.put(2,sequencerIn.State3) ;
        map.put(3,sequencerIn.State4) ;
        map.put(4,sequencerIn.State5) ;
        map.put(5,sequencerIn.GraduatedState) ;
    }

    public void processPreference(char c){
        // StringBuilder s = new StringBuilder() ;
        // FileProcessor fp = ProjectManager.inputFileProcessor ;
        // Pair pair = fp.readLine() ;
        // int b_Number = pair.b_Number ;
        // char[] prefs = pair.prefs;
        // if(prefs != null){
        //     for(char c: prefs){
        CourseInfo.course = c ;
        if(!sequencer.isGradEligible(CourseInfo)){
            if(CourseInfo.currSemCourses.size() == 3){
                CourseInfo.currSemCourses = new ArrayList<>();
                int qsize = queue.size() ;
                while(qsize-- != 0){
                    if(CourseInfo.currSemCourses.size() != 3){
                        CourseInfo.course = queue.remove() ;
                        allotCourse();
                        CourseInfo.course = c ;
                        if(sequencer.isGradEligible(CourseInfo)){
                            CourseInfo.currSemCourses = new ArrayList<>() ;
                            break ;
                        }
                    }
                    else{
                        CourseInfo.currSemCourses = new ArrayList<>();
                    }
                }
            }

            if(sequencer.isGradEligible(CourseInfo)){
                sequencer.state = sequencer.getState(5) ;
                return ;
            }
            allotCourse() ;
        }
        else{
            // Eligible for graduation !!
            sequencer.state = sequencer.getState(5) ;
            return ;
        }

    }

    public void allotCourse(){
        // System.out.println(cIn);
        if(!sequencer.iscourseAlreadyOpted(CourseInfo)){
            if(sequencer.isCourseAllowed(CourseInfo)){
                addCourse();
                sequencer.checkState(CourseInfo);
            }
            else{
                queue.add(CourseInfo.course) ;
            }
        }
        else{
            // Course is already Opted....
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

    public int numStateChanges(){
        return sequencer.NumStateChanges ;
    }

}