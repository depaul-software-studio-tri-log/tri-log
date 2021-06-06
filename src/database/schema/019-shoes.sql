create table if not exists shoes(
	shoeID INT NOT NULL AUTO_INCREMENT,
	mileage int,
	shoeBrand varchar(30),
	shoeName varchar(30),
	userID	int not null,
	runID int not null,
	

	PRIMARY KEY(shoeID),
	FOREIGN KEY(userID) REFERENCES user(id),
	FOREIGN KEY(runID) REFERENCES runs(runid)
);