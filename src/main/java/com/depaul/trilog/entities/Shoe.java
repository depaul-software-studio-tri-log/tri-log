package com.depaul.trilog.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;



@Data
@Table(name = "shoes")
@Entity
public class Shoe implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shoeid")
	private long shoeid; 
	
	@Column(name = "mileage")
	private int mileage;
	
	@Column(name = "shoe_brand")
	private String shoeBrand;
	
	@Column(name = "shoe_name")
	private String shoeName;
	
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	
	/*
	@JoinColumn(name = "runid")
	private Run runID;*/
	
	
	
	
	
	
	

}
