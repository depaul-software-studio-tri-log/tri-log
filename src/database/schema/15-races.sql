CREATE TABLE IF NOT EXISTS `races` (
    `id` int NOT NULL AUTO_INCREMENT,
    `date` date NOT NULL,
    `name` varchar(256) DEFAULT '',
    `description` varchar(2048) DEFAULT '',
    `userid` int NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`userid`) REFERENCES user(id)
);