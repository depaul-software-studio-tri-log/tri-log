create table if not exists runs(
runid INT NOT NULL AUTO_INCREMENT,
distance INT,
time  INT,
rundate DATE,
userid int,
PRIMARY KEY(runid),
Foreign Key(userid) references user(id)
);