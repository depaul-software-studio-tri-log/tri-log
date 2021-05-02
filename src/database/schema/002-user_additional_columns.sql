SELECT count(*)
INTO @columncount
FROM information_schema.columns
WHERE table_schema = 'trilog'
and COLUMN_NAME = 'birthdate'
AND table_name = 'user' LIMIT 1;

set @query = IF(@columncount <= 0, 'ALTER TABLE `user`
ADD COLUMN `birthdate` DATE NULL AFTER `enabled`,
ADD COLUMN `height` INT NULL AFTER `birthdate`,
ADD COLUMN `weight` INT NULL AFTER `height`',
'select \'Skipped adding columns\' as message');

prepare stmt from @query;

EXECUTE stmt;