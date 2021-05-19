select count(*) into @columncount from user where email = 'christine.fuerte@none.local' and username = 'christine';

set @query = IF(@columncount <= 0, 'INSERT INTO `user`
(`firstname`,
`lastname`,
`email`,
`username`,
`password`,
`enabled`,
`birthdate`,
`height`,
`weight`,
`primarysport`)
VALUES
(\'Christine\',
\'Fuerte\',
\'christine.fuerte@none.local\',
\'christine\',
\'$2y$10$pHLVYoOyBwp0K/Yyke2nJOFpXJJb3872izCNbcKeh1Go14/S1KYLC\',
1,
\'1999-01-18\',
170,
60,
1)',
'select \'User christine already exists\' as message');

prepare stmt from @query;

EXECUTE stmt;