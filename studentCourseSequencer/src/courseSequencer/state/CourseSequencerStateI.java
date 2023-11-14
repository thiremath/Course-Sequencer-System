package courseSequencer.state;

import courseSequencer.util.courseInfo;

public interface CourseSequencerStateI {
    public boolean isGraduated() ;
    public boolean isGradEligible(courseInfo helperIn) ;
    public void updateState(courseInfo helperIn) ;
    public boolean iscourseAlreadyOpted(courseInfo helperIn) ;
    public boolean isCourseAllowed(courseInfo helperIn) ;
    public void changeState(CourseSequencerStateI courseSequencerStateIn) ;
    public void processPreference(char courseIn) ;
}
