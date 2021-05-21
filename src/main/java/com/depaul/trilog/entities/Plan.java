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

    @Column(name = "swimworkout")
    private String swimworkout;

    @Column(name = "swimdistance")
    private double swimdistance;

    @Column(name = "swimtime")
    private int swimtime;

    @Column(name = "cycleworkout")
    private String cycleworkout;

    @Column(name = "cycledistance")
    private double cycledistance;

    @Column(name = "cycletime")
    private int cycletime;

    @Column(name = "runworkout")
    private String runworkout;

    @Column(name = "rundistance")
    private double rundistance;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "userid")
    private int user;
}
