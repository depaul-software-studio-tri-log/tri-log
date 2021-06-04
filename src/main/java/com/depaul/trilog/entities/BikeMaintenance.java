package com.depaul.trilog.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Table(name = "bike_maintenance")
@Entity
public class BikeMaintenance {

	
	@Column(name = "maintenanceID")
	private int maintenanceID;
	
	
	@Column(name = "note")
	private String note;
	
	
	@Column(name = "date")
	private Date date;
	
	

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	
	
	
}
