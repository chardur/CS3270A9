package com.example.student.cs3270a9;

public class Assignment {
    private String name, due_at, points_possible;

    public Assignment(String name, String due_at, String points_possible) {
        this.name = name;
        this.due_at = due_at;
        this.points_possible = points_possible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    public String getPoints_possible() {
        return points_possible;
    }

    public void setPoints_possible(String points_possible) {
        this.points_possible = points_possible;
    }
}
