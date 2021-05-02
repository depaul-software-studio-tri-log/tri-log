select count(*) into @columncount from user where email = 'bob.smith@none.local' and user_name = 'bob';

set @query = IF(@columncount <= 0, 'INSERT INTO `user`
(`first_name`,
`last_name`,
`email`,
`user_name`,
`password`,
`enabled`,
`birthdate`,
`height`,
`weight`)
VALUES
(\'Bob\',
\'Smith\',
\'bob.smith@none.local\',
\'bob\',
\'\',
1,
\'1980-04-25\',
183,
84)',
'select \'User bob already exists\' as message');

prepare stmt from @query;

EXECUTE stmt;