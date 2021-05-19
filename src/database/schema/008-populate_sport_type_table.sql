select count(*) into @columncount from sport_type where id = 1;

set @query = IF(@columncount <= 0, 'INSERT INTO `sport_type` (`id`, `name`) VALUES (1, \'swimming\')',
'select \'Swimming value already exists\' as message');

prepare stmt from @query;

EXECUTE stmt;

select count(*) into @columncount from sport_type where id = 2;

set @query = IF(@columncount <= 0, 'INSERT INTO `sport_type` (`id`, `name`) VALUES (2, \'cycling\')',
'select \'Cycling value already exists\' as message');

prepare stmt from @query;

EXECUTE stmt;

select count(*) into @columncount from sport_type where id = 3;

set @query = IF(@columncount <= 0, 'INSERT INTO `sport_type` (`id`, `name`) VALUES (3, \'running\')',
'select \'Running value already exists\' as message');

prepare stmt from @query;

EXECUTE stmt;