select count(*) into @columncount from user where email = 'bob.smith@none.local' and username = 'bob';

set @query = IF(@columncount <= 0, 'INSERT INTO `user`
(`firstname`,
`lastname`,
`age`,
`email`,
`username`,
`password`,
`enabled`,
`birthdate`,
`height`,
`weight`)
VALUES
(\'Bob\',
\'Smith\',
33,
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