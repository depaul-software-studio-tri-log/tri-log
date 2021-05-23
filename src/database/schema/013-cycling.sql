create table if not exists cycling(
    cyclingid INT NOT NULL AUTO_INCREMENT,
    distance INT,
    time  INT,
    cyclingdate DATE,
    userid int,
    PRIMARY KEY(cyclingid),
    Foreign Key(userid) references user(id)
    );