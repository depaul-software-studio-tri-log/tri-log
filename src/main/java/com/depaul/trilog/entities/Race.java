package com.depaul.trilog.entities;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "races")
public class Race {

    @Id
    private int id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    private int userid;

}
