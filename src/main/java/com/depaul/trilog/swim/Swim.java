package com.depaul.trilog.swim;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "Swims")
public class Swim implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "swim_id")
	private Long id;
	
	@Column (name = "distance")
	private int distance;
	
	
	@Column (name = "time")
	private int time;
	
	@Column (name = "swimDate")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date swimDate;

}
