ALTER TABLE `swims`
    ADD COLUMN `userid` INT NULL AFTER `swim_date`,
    RENAME COLUMN `swimDate` TO `swim_date`,
    ADD Foreign Key(userid) references user(id);