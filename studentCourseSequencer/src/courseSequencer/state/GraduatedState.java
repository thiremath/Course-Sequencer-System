package courseSequencer.state;

import courseSequencer.util.courseInfo;
import courseSequencer.util.courseSequencerHelper;

public class GraduatedState implements CourseSequencerStateI {

    public GraduatedState(courseSequencer courseSequencerIn, courseSequencerHelper CourseSequencerHelperIn){
    }

    @Override
    public boolean isGraduated(){
        return true ;
    }

    @Override
    public void processPreference(char courseIn) {
        return ;
    }

    @Override
    public void updateState(courseInfo CourseInfoIn) {
        return ;
    }

    @Override
    public void changeState(CourseSequencerStateI courseSequencerStateIn) {
        return ;
    }

    @Override
    public boolean isGradEligible(courseInfo CourseInfoIn) {
        return true;
    }

    @Override
    public boolean iscourseAlreadyOpted(courseInfo CourseInfoIn) {
        return true;
    }

    @Override
    public boolean isCourseAllowed(courseInfo CourseInfoIn) {
        return true;
    }
    
}