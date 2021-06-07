ALTER TABLE `goals`
ADD COLUMN `distanceprogress` INT NULL AFTER `distance`,
ADD COLUMN `minutesprogress` INT NULL AFTER `minutes`;