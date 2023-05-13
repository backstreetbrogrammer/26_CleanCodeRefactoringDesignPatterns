package com.backstreetbrogrammer.ch01_cleancode.comments;

public class Course {

    private int cn; // course number
    private int courseNumber;
    private boolean systemIsUp;
    private String title;
    private String info;

    public String getTitle() {
        return title;
    }

    private String getInfo() {
        return String.format("%d~%s~%s", courseNumber, title, info);
    }

    public String getCourseInfo(Course course, int pin) {
        if (systemIsUp) {
            if (course != null && course.getTitle().equals("")) {
                return "Invalid Name";
            }
            return "System is down at the moment";
        } // end of first if statement
        return course.getInfo();
    }
}
