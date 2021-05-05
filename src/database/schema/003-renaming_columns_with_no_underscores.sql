SELECT count(*)
INTO @columncount
FROM information_schema.columns
WHERE table_schema = 'trilog'
and COLUMN_NAME = 'username'
AND table_name = 'user' LIMIT 1;

set @query = IF(@columncount <= 0, 'ALTER TABLE `user`
RENAME COLUMN `user_name` TO `username`,
RENAME COLUMN `first_name` TO `firstname`,
RENAME COLUMN `last_name` TO `lastname`',
'select \'Skipped renaming columns\' as message');

prepare stmt from @query;

EXECUTE stmt;