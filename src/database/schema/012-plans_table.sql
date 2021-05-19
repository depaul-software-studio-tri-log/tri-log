CREATE TABLE IF NOT EXISTS `plans` (
    `date` date DEFAULT NULL,
    `activity` int NOT NULL DEFAULT '1',
    `details` varchar(2048) DEFAULT NULL,
    `id` int NOT NULL AUTO_INCREMENT,
    `user` int NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FK_plans_activity
    FOREIGN KEY (`activity`) REFERENCES sport_type(id),
    CONSTRAINT FK_plans_user
    FOREIGN KEY (`user`) REFERENCES user(id)
);