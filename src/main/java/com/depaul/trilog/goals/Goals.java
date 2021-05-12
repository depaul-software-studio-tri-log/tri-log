package com.depaul.trilog.goals;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Goals {
    @Id
    private int id;

    @Column(name = "goalname")
    private String goalname;
    @Column(name = "activity")
    private String activity;
    @Column(name = "distance")
    private int distance;
    @Column(name = "minutes")
    private int minutes;
    @Column(name = "note")
    private String note;
    @Column(name = "user_id")
    private int user_id;

    public String getGoalname() {
        return goalname;
    }

    public void setGoalname(String goalname) {
        this.goalname = goalname;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
