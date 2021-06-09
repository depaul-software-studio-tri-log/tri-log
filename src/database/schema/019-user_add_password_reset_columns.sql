SELECT count(*)
INTO @columncount
FROM information_schema.columns
WHERE table_schema = 'trilog'
and COLUMN_NAME = 'passwordresettoken'
AND table_name = 'user' LIMIT 1;

set @query = IF(@columncount <= 0, 'ALTER TABLE `user`
ADD COLUMN `passwordresettoken` VARCHAR(256) NULL AFTER `weight`,
ADD COLUMN `passwordresetexpires` DATETIME NULL AFTER `passwordresettoken`',
'select \'Skipped adding columns\' as message');

prepare stmt from @query;

EXECUTE stmt;