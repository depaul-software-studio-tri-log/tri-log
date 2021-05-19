select count(*) into @columncount from sport_type where id = 4;

set @query = IF(@columncount <= 0, 'INSERT INTO `sport_type` (`id`, `name`) VALUES (4, \'triathlete\')',
'select \'Triathlete value already exists\' as message');

prepare stmt from @query;

EXECUTE stmt;