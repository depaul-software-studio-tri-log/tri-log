package com.depaul.trilog.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "runs")
public class Run implements Serializable {
	
	private static final long serialVersionUID = 2L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "runid")
	private long id; 
	
	
	@Column (name = "distance")
	private int distance;
	
	
	@Column (name = "time")
	private int time;
	
	@Column (name = "rundate")
	private Date runDate;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	

}
