package courseSequencer.state;

import java.util.HashMap;

public class courseSequencer {
    CourseSequencerStateI State1 ;
    CourseSequencerStateI State2 ;
    CourseSequencerStateI State3 ;
    CourseSequencerStateI State4 ;
    CourseSequencerStateI State5 ;

    int NumStateChanges ;
    HashMap<Integer,CourseSequencerStateI> map = new HashMap<>() ;
    courseSequencerHelper CourseAllocHelper = new courseSequencerHelper(this) ;

    CourseSequencerStateI state ;

    public courseSequencer(){
        State1 = new State1(this) ;
        State2 = new State2(this) ;
        State3 = new State3(this) ;
        State4 = new State4(this) ;
        State5 = new State5(this) ;

        map.put(0,State1) ;
        map.put(1,State2) ;
        map.put(2,State3) ;
        map.put(3,State4) ;
        map.put(4,State5) ;

        NumStateChanges = 0 ;
        state = State1 ;
    }

    public void registerCourse(){
        CourseAllocHelper.processPreference();
    }

    public boolean isGradEligible(courseInfo courseInfoIn){
        return state.isGradEligible(courseInfoIn) ;
    }

    public void checkState(courseInfo courseInfoIn){
        state.updateState(courseInfoIn);
    } 

    public boolean iscourseAlreadyOpted(courseInfo courseInfoIn){
        return state.iscourseAlreadyOpted(courseInfoIn) ;
    } 

    public boolean isCourseAllowed(courseInfo courseInfoIn){
        return state.isCourseAllowed(courseInfoIn) ;
    } 

    public void setState(CourseSequencerStateI stateIn){
        state = stateIn ;
        NumStateChanges++ ;
    }

    public CourseSequencerStateI getCurrState(){
        return state ;
    }

    public CourseSequencerStateI getState(int i){
        return map.get(i) ;
    }

}
