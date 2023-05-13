package com.backstreetbrogrammer.ch01_cleancode.goodclasses;

public class Student {

    private int id;
    private String name;
    private int age; // Student's age
    private String semester;

    // Getter and Setters
    public int getId() {
        return id;
    }

    // Get Students name
    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    double feesCalculator() {
        return 0D;
    }

    void sendEmail() {
    }

}
