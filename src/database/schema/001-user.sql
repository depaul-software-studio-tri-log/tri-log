CREATE TABLE IF NOT EXISTS `user` (
  `first_name` varchar(256) DEFAULT NULL,
  `last_name` varchar(256) DEFAULT NULL,
  `email` varchar(256) NOT NULL,
  `user_name` varchar(256) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(256) DEFAULT NULL,
  `enabled` varchar(45) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;