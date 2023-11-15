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
    
}