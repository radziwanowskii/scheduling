package org.scheduling;

public class Tutor {
    private String name;
    private int[] time;

    private int maxTutorials;

    public Tutor(String name, int maxTutorials, int[] time)

    {
        this.name = name;
        this.time = time;
        this.maxTutorials = maxTutorials;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxTutorials() {
        return maxTutorials;
    }

    public void setMaxTutorials(int maxTutorials) {
        this.maxTutorials = maxTutorials;
    }
    public int[] getTime() {
        return time;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

}

