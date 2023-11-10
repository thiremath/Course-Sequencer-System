package courseSequencer.state;

public class GraduatedState implements CourseSequencerStateI {

    // Graduate State
    private courseSequencer courseSeq ;
    private courseSequencerHelper CourseSequencerHelper ;

    public GraduatedState(courseSequencer courseSequencerIn, courseSequencerHelper CourseSequencerHelperIn){
        courseSeq = courseSequencerIn ;
        CourseSequencerHelper = CourseSequencerHelperIn ;
    }

    @Override
    public void processPreference(char courseIn) {
        courseSeq.isGraduated = true ;
        return ;
    }

    @Override
    public void updateState(courseInfo CourseInfoIn) {
        courseSeq.isGraduated = true ;
        return ;
    }

    @Override
    public void changeState(CourseSequencerStateI courseSequencerStateIn) {
        courseSeq.isGraduated = true ;
        return ;
    }

    @Override
    public boolean isGradEligible(courseInfo CourseInfoIn) {
        courseSeq.isGraduated = true ;
        return true;
    }

    @Override
    public boolean iscourseAlreadyOpted(courseInfo CourseInfoIn) {
        courseSeq.isGraduated = true ;
        return true;
    }

    @Override
    public boolean isCourseAllowed(courseInfo CourseInfoIn) {
        courseSeq.isGraduated = true ;
        return true;
    }
    
}