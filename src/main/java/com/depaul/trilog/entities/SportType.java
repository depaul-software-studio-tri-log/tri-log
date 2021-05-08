package com.depaul.trilog.entities;

import javax.persistence.*;

@Entity
public class SportType {

    @Id
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
