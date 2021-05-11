CREATE TABLE IF NOT EXISTS `user` (
  `firstname` varchar(256) DEFAULT NULL,
  `lastname` varchar(256) DEFAULT NULL,
  `email` varchar(256) NOT NULL,
  `username` varchar(256) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(256) DEFAULT NULL,
  `enabled` varchar(45) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;