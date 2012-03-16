insert into user values(1, 1, '2099-12-12', '2099-12-12', null, '2099-12-12', 'asd@asd.com', 'test', 'test', null, true, 'test', 'test', 1, 1);

insert into instructor values (1, 1, SYSDATE(), SYSDATE(), 1, 1, 1);

insert into role values(1, 1, SYSDATE(), SYSDATE(), 'Instructor Role', 'Instructor', 1, 1);

insert into user_role values(1, 1);


insert into course values(1, 1, SYSDATE(), SYSDATE(), 'course 101', 1, 1, 1, 1);
insert into course values(2, 1, SYSDATE(), SYSDATE(), 'course 102', 1, 1, 1, 2);
insert into course values(3, 1, SYSDATE(), SYSDATE(), 'course 200', 1, 1, 1, 2);


insert into semester values(1, 1, SYSDATE(), SYSDATE(), '2010-10-10', '2010 Fall', 1, 1);
insert into semester values(2, 1, SYSDATE(), SYSDATE(), '2012-12-12', '2012 Fall', 1, 1);
