ALTER TABLE `goals` MODIFY COLUMN activity INT;

ALTER TABLE `goals`
    ADD CONSTRAINT FK_goals_activity FOREIGN KEY (activity) REFERENCES sport_type(id);