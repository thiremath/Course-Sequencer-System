package courseSequencer.state;

import java.util.ArrayList;

public interface CourseSequencerStateI {
    public boolean isGradEligible(ArrayList<ArrayList<Character>> courses_grpsIn) ;
    public void updateState(ArrayList<ArrayList<Character>> courses_grpsIn) ;
    public boolean iscourseAlreadyOpted(ArrayList<Character> coursesAllotedIn, char courseIn) ;
    public boolean isCourseAllowed(ArrayList<Character> coursesAllotedIn, ArrayList<Character> semCoursesIn, char courseIn) ;
    public void changeState(CourseSequencerStateI courseSequencerStateIn) ;
}
