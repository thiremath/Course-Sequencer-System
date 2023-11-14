package courseSequencer.state;

import courseSequencer.util.courseInfo;
import courseSequencer.util.courseSequencerHelper;

public class courseSequencer {
    CourseSequencerStateI State1 ;
    CourseSequencerStateI State2 ;
    CourseSequencerStateI State3 ;
    CourseSequencerStateI State4 ;
    CourseSequencerStateI State5 ;
    CourseSequencerStateI GraduatedState ;

    courseSequencerHelper CourseSequencerHelper ;
    public CourseSequencerStateI state ;
    public int NumStateChanges ;

    public courseSequencer(courseInfo CourseInfo){

        CourseSequencerHelper = new courseSequencerHelper(this,CourseInfo) ;

        State1 = new State1(this,CourseSequencerHelper) ;
        State2 = new State2(this,CourseSequencerHelper) ;
        State3 = new State3(this,CourseSequencerHelper) ;
        State4 = new State4(this,CourseSequencerHelper) ;
        State5 = new State5(this,CourseSequencerHelper) ;
        GraduatedState = new GraduatedState(this,CourseSequencerHelper) ;

        CourseSequencerHelper.createStateMap(State1,State2,State3,State4,State5,GraduatedState) ;

        NumStateChanges = 0 ;
        state = State1 ;
    }

    public void registerCourse(char courseIn){
        state.processPreference(courseIn) ;
    }

    public boolean isGraduated(){
        return state.isGraduated() ;
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
        return CourseSequencerHelper.getState(i) ;
    }

}
