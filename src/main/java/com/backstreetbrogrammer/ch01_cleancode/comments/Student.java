package com.backstreetbrogrammer.ch01_cleancode.comments;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private int age; // Student's age - useless
    private String semester;

    // Getter and Setters
    public int getId() {
        return id;
    }

    // Get Students name - useless
    public String getName() {
        return name;
    }

    public static void main(final String[] args) {
        final List<Course> courses = new ArrayList<>();
        // loop through courses - useless
        for (final Course course : courses) {
            if (course.getTitle().equals("Java")) {
                System.out.println("my favorite course");
            }
        }
    }

}
