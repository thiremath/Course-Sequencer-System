package courseSequencer.state;

import java.util.ArrayList;

public class courseInfo {
    public ArrayList<Character> Group1;
    public ArrayList<Character> Group2;
    public ArrayList<Character> Group3;
    public ArrayList<Character> Group4;
    public ArrayList<Character> Group5;

    public ArrayList<ArrayList<Character>> courses_grps ;
    public ArrayList<ArrayList<Character>> semwiseCourses ;

    public ArrayList<Character> coursesAlloted ;
    public ArrayList<Character> currSemCourses ;
    public char course ;

    public courseInfo(){
        Group1 = new ArrayList<>() ;
        Group2 = new ArrayList<>() ;
        Group3 = new ArrayList<>() ;
        Group4 = new ArrayList<>() ;
        Group5 = new ArrayList<>() ;

        courses_grps = new ArrayList<>(){{
            add(Group1);
            add(Group2);
            add(Group3);
            add(Group4);
            add(Group5);
        }};

        coursesAlloted = new ArrayList<>() ;
        currSemCourses = new ArrayList<>() ;
        semwiseCourses = new ArrayList<ArrayList<Character>>() ;

        course = Character.MIN_VALUE ;
    }
}