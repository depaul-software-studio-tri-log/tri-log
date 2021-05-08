CREATE TABLE IF NOT EXISTS `goals` (
    `goalname` varchar(256) DEFAULT NULL,
    `activity` varchar(256) DEFAULT NULL,
    `distant` varchar(256) NOT NULL,
    `note` varchar(1024) DEFAULT NULL,
    `user_id` int NOT NULL,
    `id` int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (user_id) REFERENCES user(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;