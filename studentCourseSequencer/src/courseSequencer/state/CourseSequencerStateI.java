package courseSequencer.state;

import courseSequencer.util.courseInfo;

public interface CourseSequencerStateI {
    public void processPreference(char courseIn) ;
    public void updateState(courseInfo helperIn) ;
    public boolean isGraduated() ;
}
