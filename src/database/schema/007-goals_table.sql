CREATE TABLE IF NOT EXISTS `goals` (
    `goalname` varchar(256) DEFAULT NULL,
    `activity` varchar(256) DEFAULT NULL,
    `distance` varchar(256) NOT NULL,
    `minutes` varchar(256) DEFAULT NULL,
    `note` varchar(1024) DEFAULT NULL,
    `user_id` int NOT NULL,
    `id` int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (user_id) REFERENCES user(id)
    );