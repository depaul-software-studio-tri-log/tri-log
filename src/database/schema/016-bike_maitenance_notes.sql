create table if not exists bike_maitenance(
	
	maitenanceID INT NOT NULL AUTO_INCREMENT,
	note varchar(500),
	date Date,
	userid int not null,
	
	PRIMARY KEY(maitenanceID),
	FOREIGN KEY(userid) REFERENCES user(id)

);