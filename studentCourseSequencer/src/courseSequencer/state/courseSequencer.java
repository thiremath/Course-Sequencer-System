package courseSequencer.state;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import courseSequencer.projectmanager.ProjectManager;
import courseSequencer.util.FileProcessor;
import courseSequencer.util.Pair;
import courseSequencer.util.Results;

public class courseSequencer {
    CourseSequencerStateI NotGraduated ;
    CourseSequencerStateI State1 ;
    CourseSequencerStateI State2 ;
    CourseSequencerStateI State3 ;
    CourseSequencerStateI State4 ;
    CourseSequencerStateI State5 ;

    int NumStateChanges ;
    ArrayList<Character> coursesAlloted ;
    ArrayList<Character> currSemCourses ;
    ArrayList<Character> Group1 ;
    ArrayList<Character> Group2 ;
    ArrayList<Character> Group3 ;
    ArrayList<Character> Group4 ;
    ArrayList<Character> Group5 ;
    Queue<Character> queue ;
    ArrayList<ArrayList<Character>> courses_grps ;
    ArrayList<ArrayList<Character>> semwiseCourses ;
    CourseSequencerStateI state = NotGraduated ;

    public courseSequencer(){
        State1 = new State1(this) ;
        State2 = new State2(this) ;
        State3 = new State3(this) ;
        State4 = new State4(this) ;
        State5 = new State5(this) ;
        coursesAlloted = new ArrayList<>();
        Group1 = new ArrayList<>();
        Group2 = new ArrayList<>();
        Group3 = new ArrayList<>();
        Group4 = new ArrayList<>();
        Group5 = new ArrayList<>();

        courses_grps = new ArrayList<>(){{
            add(Group1);
            add(Group2);
            add(Group3);
            add(Group4);
            add(Group5);
        }};

        NumStateChanges = 0 ;
        state = State1 ;
    }

    public void processPreference(){
        StringBuilder s = new StringBuilder() ;
        FileProcessor fp = ProjectManager.inputFileProcessor ;
        Pair pair = fp.readLine() ;
        int b_Number = pair.b_Number ;
        char[] prefs = pair.prefs;
        if(prefs != null){
            currSemCourses = new ArrayList<>();
            semwiseCourses = new ArrayList<ArrayList<Character>>() ;
            queue = new LinkedList<>() ;
            for(char c: prefs){
                if(!isGradEligible(courses_grps)){
                    if(currSemCourses.size() == 3){
                        semwiseCourses.add(currSemCourses) ;
                        currSemCourses = new ArrayList<>();
                        int qsize = queue.size() ;
                        while(qsize-- != 0){
                            if(currSemCourses.size() != 3){
                                allotCourse(queue.remove());
                                if(isGradEligible(courses_grps)){
                                    semwiseCourses.add(currSemCourses) ;
                                    break ;
                                }
                            }
                            else{
                                semwiseCourses.add(currSemCourses) ;
                                currSemCourses = new ArrayList<>();
                            }
                        }
                    }
                    if(isGradEligible(courses_grps)){
                        break ;
                    }
                    allotCourse(c) ;
                }
                else{
                    // Eligible for graduation, write results to output file...
                    // semwiseCourses.add(coursesAlloted) ;
                    break ;
                }
            }
        }
        
        s.append(String.valueOf(b_Number)+" ") ;
        for(char tempChar: coursesAlloted){
            s.append(String.valueOf(tempChar)) ;
            s.append(" ") ;
        }
        s.append("-- ") ;
        if(isGradEligible(courses_grps)){
            s.append(semwiseCourses.size()) ;
        }
        else{
            s.append("0 ") ;
        }
        s.append(numStateChanges()) ;
        System.out.println(semwiseCourses+" semwise courses");
        System.out.println(" "+s+" Output\n");
        System.out.println(courses_grps+"courses_grps");
    }

    public void allotCourse(char cIn){
        System.out.println(cIn);
        if(!iscourseAlreadyOpted(coursesAlloted,cIn)){
            if(isCourseAllowed(coursesAlloted,currSemCourses,cIn)){
                addCourse(cIn);
                if(currSemCourses.size() == 3){
                    // updateState();
                    checkState(courses_grps);
                }
            }
            else{
                queue.add(cIn) ;
            }
        }
        else{
            // Course is already Opted....
        }
    }

    public void addCourse(char cIn){
        currSemCourses.add(cIn) ;
        coursesAlloted.add(cIn) ;

        if(((cIn - 'A') / 4) >= 5){
            courses_grps.get(4).add(cIn) ;
        }
        else{
            courses_grps.get((cIn - 'A') / 4).add(cIn) ;
        }
    }

    public boolean isGradEligible(ArrayList<ArrayList<Character>> courses_grpsIn){
        return state.isGradEligible(courses_grpsIn) ;
    }

    public void checkState(ArrayList<ArrayList<Character>> courses_grpsIn){
        state.updateState(courses_grpsIn);
    } 

    public boolean iscourseAlreadyOpted(ArrayList<Character> coursesAllotedIn, char courseIn){
        return state.iscourseAlreadyOpted(coursesAllotedIn,courseIn) ;
    } 

    public boolean isCourseAllowed(ArrayList<Character> coursesAllotedIn, ArrayList<Character> currSemCoursesIn, char courseIn){
        return state.isCourseAllowed(coursesAllotedIn,currSemCoursesIn,courseIn) ;
    } 

    public void setState(CourseSequencerStateI stateIn){
        state = stateIn ;
        NumStateChanges++ ;
    }

    public CourseSequencerStateI getState(int i){
        if(i == 0){
            return State1 ;
        }
        else if(i == 1){
            return State2 ;
        }
        else if(i == 2){
            return State3 ;
        }
        else if(i == 3){
            return State4 ;
        }
        return State5 ;
    }

    public int numStateChanges(){
        return NumStateChanges ;
    }

}
