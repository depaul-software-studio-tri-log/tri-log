CREATE TABLE IF NOT EXISTS `plans` (
    `id` int NOT NULL AUTO_INCREMENT,
    `date` date NOT NULL,
    `swimworkout` varchar(2048) DEFAULT '',
    `swimdistance` double DEFAULT NULL,
    `swimtime` int DEFAULT NULL,
    `cycleworkout` varchar(2048) DEFAULT '',
    `cycledistance` double DEFAULT NULL,
    `cycletime` int DEFAULT NULL,
    `runworkout` varchar(2048) DEFAULT '',
    `rundistance` double DEFAULT NULL,
    `runtime` int DEFAULT NULL,
    `userid` int NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT UC_plans_user_date UNIQUE (userid,date),
    CONSTRAINT FK_plans_user
    FOREIGN KEY (`userid`) REFERENCES user(id)
);