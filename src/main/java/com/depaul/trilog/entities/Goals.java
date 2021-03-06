package com.depaul.trilog.entities;

import com.depaul.trilog.entities.SportType;
import lombok.Data;

import javax.persistence.*;

@Entity
public class Goals {
    @Id
    private int id;

    @Column(name = "goalname")
    private String goalname;
    @Column(name = "activity")
    private int activity;
    @Column(name = "distance")
    private int distance;
    @Column(name = "distanceprogress")
    private int distanceprogress;
    @Column(name = "minutes")
    private int minutes;
    @Column(name = "minutesprogress")
    private int minutesprogress;
    @Column(name = "note")
    private String note;
    @Column(name = "user_id")
    private int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalname() {
        return goalname;
    }

    public void setGoalname(String goalname) {
        this.goalname = goalname;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistanceprogress() {
        return distanceprogress;
    }

    public void setDistanceprogress(int distanceprogress) {
        this.distanceprogress = distanceprogress;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutesprogress() {
        return minutesprogress;
    }

    public void setMinutesprogress(int minutesprogress) {
        this.minutesprogress = minutesprogress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
