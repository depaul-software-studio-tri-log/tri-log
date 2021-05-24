package com.depaul.trilog.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "cycling")
public class Cycling implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cyclingid")
    private long id;


    @Column (name = "distance")
    private int distance;


    @Column (name = "time")
    private int time;

    @Column (name = "cyclingdate")
    private Date cyclingDate;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}
