package courseSequencer.state;

import java.util.ArrayList;

import courseSequencer.util.StateHelper;

public class State5 implements CourseSequencerStateI {
    courseSequencer courseSeq ;

    public State5(courseSequencer courseSequencerIn){
        courseSeq = courseSequencerIn ;
    }
    
    @Override
    public void updateState(ArrayList<ArrayList<Character>> courses_grpsIn) {
        int temp = courses_grpsIn.get(4).size() ;
        for(int i=0;i<courses_grpsIn.size();i++){
            if(courses_grpsIn.get(i).size() > temp) {
                changeState(courseSeq.getState(i));
            }
        }
    }

    @Override
    public boolean isGradEligible(ArrayList<ArrayList<Character>> courses_grpsIn) {
        return StateHelper.isGradEligible(courses_grpsIn) ;
    }

    @Override
    public boolean iscourseAlreadyOpted(ArrayList<Character> coursesAllotedIn, char courseIn) {
        return StateHelper.iscourseAlreadyOpted(coursesAllotedIn,courseIn) ;
    }

    @Override
    public boolean isCourseAllowed(ArrayList<Character> coursesAllotedIn, ArrayList<Character> semCoursesIn, char courseIn) {
        return StateHelper.isCourseAllowed(coursesAllotedIn,semCoursesIn,courseIn) ;
    }

    @Override
    public void changeState(CourseSequencerStateI courseSequencerStateIn) {
        courseSeq.setState(courseSequencerStateIn);
    }
    
}
