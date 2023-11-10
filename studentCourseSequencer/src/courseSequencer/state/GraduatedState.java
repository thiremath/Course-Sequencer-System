package courseSequencer.state;

public class GraduatedState implements CourseSequencerStateI {
    private courseSequencer courseSeq ;
    private courseSequencerHelper CourseSequencerHelper ;

    public GraduatedState(courseSequencer courseSequencerIn, courseSequencerHelper CourseSequencerHelperIn){
        courseSeq = courseSequencerIn ;
        CourseSequencerHelper = CourseSequencerHelperIn ;
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
        return true ;
    }

    @Override
    public boolean iscourseAlreadyOpted(courseInfo CourseInfoIn) {
        return true ;
    }

    @Override
    public boolean isCourseAllowed(courseInfo CourseInfoIn) {
        return true ;
    }
    
}