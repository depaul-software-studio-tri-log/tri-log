package com.depaul.trilog.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "plans")
public class Plan {

    @Id
    @Column(name = "id")
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date = new Date();

    @Column(name = "activity")
    private int activity;

    @Column(name = "details")
    private String details;

    @Column(name = "user")
    private int user;
}
