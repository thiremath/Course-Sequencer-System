package courseSequencer.state;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import courseSequencer.projectmanager.ProjectManager;
import courseSequencer.util.FileProcessor;
import courseSequencer.util.Pair;

public class courseSequencerHelper {
    Queue<Character> queue ;
    courseSequencer sequencer ;
    courseInfo CourseInfo ;

    public courseSequencerHelper(courseSequencer sequencerIn){
        sequencer = sequencerIn ;
        CourseInfo = new courseInfo() ;
        queue = new LinkedList<>() ;
    }

    public void processPreference(){
        StringBuilder s = new StringBuilder() ;
        FileProcessor fp = ProjectManager.inputFileProcessor ;
        Pair pair = fp.readLine() ;
        int b_Number = pair.b_Number ;
        char[] prefs = pair.prefs;
        if(prefs != null){
            CourseInfo.currSemCourses = new ArrayList<>();
            CourseInfo.semwiseCourses = new ArrayList<ArrayList<Character>>() ;
            // queue = new LinkedList<>() ;
            for(char c: prefs){
                CourseInfo.course = c ;
                if(!sequencer.isGradEligible(CourseInfo)){
                    if(CourseInfo.currSemCourses.size() == 3){
                        CourseInfo.semwiseCourses.add(CourseInfo.currSemCourses) ;
                        CourseInfo.currSemCourses = new ArrayList<>();
                        int qsize = queue.size() ;
                        while(qsize-- != 0){
                            if(CourseInfo.currSemCourses.size() != 3){
                                CourseInfo.course = queue.remove() ;
                                allotCourse();
                                CourseInfo.course = c ;
                                if(sequencer.isGradEligible(CourseInfo)){
                                    CourseInfo.semwiseCourses.add(CourseInfo.currSemCourses) ;
                                    CourseInfo.currSemCourses = new ArrayList<>() ;
                                    break ;
                                }
                            }
                            else{
                                CourseInfo.semwiseCourses.add(CourseInfo.currSemCourses) ;
                                CourseInfo.currSemCourses = new ArrayList<>();
                            }
                        }
                    }
                    if(sequencer.isGradEligible(CourseInfo)){
                        break ;
                    }
                    allotCourse() ;
                }
                else{
                    // Eligible for graduation, write results to output file...
                    // CourseInfo.semwiseCourses.add(coursesAlloted) ;
                    break ;
                }
            }
            if(CourseInfo.currSemCourses.size() > 0){
                CourseInfo.semwiseCourses.add(CourseInfo.currSemCourses) ;
            }
        }
        
        s.append(String.valueOf(b_Number)+" ") ;
        for(char tempChar: CourseInfo.coursesAlloted){
            s.append(String.valueOf(tempChar)) ;
            s.append(" ") ;
        }
        s.append("-- ") ;
        if(sequencer.isGradEligible(CourseInfo)){
            s.append(CourseInfo.semwiseCourses.size()) ;
            s.append(" "+numStateChanges()) ;
        }
        else{
            s.append("0") ;
            s.append(" "+numStateChanges()) ;
            s.append("\n The Student Does not Graduate!!") ;
        }
        System.out.println(CourseInfo.semwiseCourses+" semwise courses");
        System.out.println(" "+s+"\n");
        System.out.println(CourseInfo.courses_grps+"courses_grps");
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