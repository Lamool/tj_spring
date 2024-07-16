drop database if exists springday08board;
create database springday08board;
use springday08board;

drop table if exists board;
create table board(
	bno int auto_increment,
    bdate datetime default now(),
    btitle varchar(30),
    bview int default 0,
    bcontent longtext,
    bpassword varchar(30),
    primary key (bno)
);

insert into board(btitle, bcontent, bpassword) values('제목', '내용', '1234');
insert into board(btitle, bcontent, bpassword) values('제목', '내용', '1234');
insert into board(btitle, bcontent, bpassword) values('제목', '내용', '1234');
insert into board(btitle, bcontent, bpassword) values('제목', '내용', '1234');
insert into board(btitle, bcontent, bpassword) values('제목', '내용', '1234');

select * from board;

select btitle from board where bno = 2 and bpassword = '1234';
