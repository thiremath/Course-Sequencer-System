package courseSequencer.state;

import courseSequencer.util.StateHelper;
import courseSequencer.util.courseInfo;
import courseSequencer.util.courseSequencerHelper;

public class State1 implements CourseSequencerStateI {
    private courseSequencer courseSeq ;
    private courseSequencerHelper CourseSequencerHelper ;

    public State1(courseSequencer courseSequencerIn, courseSequencerHelper CourseSequencerHelperIn){
        courseSeq = courseSequencerIn ;
        CourseSequencerHelper = CourseSequencerHelperIn ;
    }

    @Override
    public boolean isGraduated(){
        return false ;
    }

    public void processPreference(char courseIn){
        CourseSequencerHelper.processPreference(courseIn);
    }

    @Override
    public void updateState(courseInfo CourseInfoIn) {
        CourseSequencerStateI currState = courseSeq.getCurrState() ;
        CourseSequencerStateI tempState = currState ;
        int Size = CourseInfoIn.courses_grps.get(0).size() ;
        for(int i=0;i<CourseInfoIn.courses_grps.size();i++){
            if(CourseInfoIn.courses_grps.get(i).size() > Size) {
                tempState = courseSeq.getState(i);
            }
        }
        if(tempState != currState) changeState(tempState);
    }

    @Override
    public void changeState(CourseSequencerStateI courseSequencerStateIn) {
        courseSeq.setState(courseSequencerStateIn);
    }

    @Override
    public boolean isGradEligible(courseInfo CourseInfoIn) {
        return StateHelper.isGradEligible(CourseInfoIn) ;
    }

    @Override
    public boolean iscourseAlreadyOpted(courseInfo CourseInfoIn) {
        return StateHelper.iscourseAlreadyOpted(CourseInfoIn) ;
    }

    @Override
    public boolean isCourseAllowed(courseInfo CourseInfoIn) {
        return StateHelper.isCourseAllowed(CourseInfoIn) ;
    }
    
}