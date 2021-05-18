UPDATE `user` SET `primarysport` = 4 where `primarysport` IS NULL;

ALTER TABLE `user` MODIFY COLUMN `primarysport` INT NOT NULL;

ALTER TABLE `user` ALTER `primarysport` SET DEFAULT 4;