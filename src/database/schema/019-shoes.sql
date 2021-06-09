create table if not exists shoes(
	shoeid INT NOT NULL AUTO_INCREMENT,
	mileage int,
	shoe_brand varchar(30),
	shoe_name varchar(30),
	userid	int not null,

	

	PRIMARY KEY(shoeid),
	FOREIGN KEY(userid) REFERENCES user(id)
);