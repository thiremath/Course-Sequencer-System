package courseSequencer.state;

import courseSequencer.util.StateHelper;

public class State4 implements CourseSequencerStateI {
    private courseSequencer courseSeq ;
    private courseSequencerHelper CourseSequencerHelper ;

    public State4(courseSequencer courseSequencerIn, courseSequencerHelper CourseSequencerHelperIn){
        courseSeq = courseSequencerIn ;
        CourseSequencerHelper = CourseSequencerHelperIn ;
    }

    public void processPreference(char courseIn){
        CourseSequencerHelper.processPreference(courseIn);
    }

    @Override
    public void updateState(courseInfo CourseInfoIn) {
        CourseSequencerStateI currState = courseSeq.getCurrState() ;
        CourseSequencerStateI tempState = currState ;
        int Size = CourseInfoIn.courses_grps.get(3).size() ;
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
