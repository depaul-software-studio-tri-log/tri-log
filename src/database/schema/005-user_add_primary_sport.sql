SELECT count(*)
INTO @columncount
FROM information_schema.columns
WHERE table_schema = 'trilog'
and COLUMN_NAME = 'primarysport'
AND table_name = 'user' LIMIT 1;

set @query = IF(@columncount <= 0, 'ALTER TABLE `user`
ADD COLUMN `primarysport` int NULL AFTER `weight`',
'select \'Skipped adding column\' as message');

prepare stmt from @query;

EXECUTE stmt;

set @query = IF(@columncount <= 0, 'ALTER TABLE `user`
ADD CONSTRAINT FK_user_primarysport FOREIGN KEY (primarysport) REFERENCES sport_type(id)',
'select \'Skipped adding column\' as message');

prepare stmt from @query;

EXECUTE stmt;