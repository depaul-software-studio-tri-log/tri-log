
create table if not exists Swims(
swim_id INT NOT NULL AUTO_INCREMENT,
distance INT,
time  TIME,
swimDate DATETIME,

PRIMARY KEY(swim_id)
);