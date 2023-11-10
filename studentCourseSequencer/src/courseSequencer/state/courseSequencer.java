package courseSequencer.state;

import java.util.HashMap;

public class courseSequencer {
    CourseSequencerStateI State1 ;
    CourseSequencerStateI State2 ;
    CourseSequencerStateI State3 ;
    CourseSequencerStateI State4 ;
    CourseSequencerStateI State5 ;
    CourseSequencerStateI GraduatedState ;

    courseSequencerHelper CourseSequencerHelper ;
    CourseSequencerStateI state ;
    HashMap<Integer,CourseSequencerStateI> map ;
    public boolean isGraduated ;
    public int NumStateChanges ;

    public courseSequencer(courseInfo CourseInfo){

        CourseSequencerHelper = new courseSequencerHelper(this,CourseInfo) ;

        State1 = new State1(this,CourseSequencerHelper) ;
        State2 = new State2(this,CourseSequencerHelper) ;
        State3 = new State3(this,CourseSequencerHelper) ;
        State4 = new State4(this,CourseSequencerHelper) ;
        State5 = new State5(this,CourseSequencerHelper) ;
        GraduatedState = new GraduatedState(this,CourseSequencerHelper) ;

        map = new HashMap<>() ;
        map.put(0,State1) ;
        map.put(1,State2) ;
        map.put(2,State3) ;
        map.put(3,State4) ;
        map.put(4,State5) ;
        map.put(5,GraduatedState) ;

        isGraduated = false ;

        NumStateChanges = 0 ;
        state = State1 ;
    }

    public void registerCourse(char courseIn){
        state.processPreference(courseIn) ;
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

    public CourseSequencerStateI getState1(){
        return State1 ;
    }

    public CourseSequencerStateI getState2(){
        return State2 ;
    }

    public CourseSequencerStateI getState3(){
        return State3 ;
    }

    public CourseSequencerStateI getState4(){
        return State4 ;
    }

    public CourseSequencerStateI getState5(){
        return State5 ;
    }

    public CourseSequencerStateI getGraduatedState(){
        return GraduatedState ;
    }

}
