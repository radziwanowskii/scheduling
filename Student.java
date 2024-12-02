package org.scheduling;

public class Student {
    private String name;
    private int[] time;

    public Student(String name , int[] time){

        this.name = name;
        this.time = time;

    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getTime() {
        return time;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

}

