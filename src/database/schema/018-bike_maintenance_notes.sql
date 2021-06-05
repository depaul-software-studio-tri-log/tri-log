create table if not exists bike_maintenance(
	
	maintenanceID INT NOT NULL AUTO_INCREMENT,
	note varchar(500),
	date Date,
	userid int not null,
	
	PRIMARY KEY(maintenanceID),
	FOREIGN KEY(userid) REFERENCES user(id)

);