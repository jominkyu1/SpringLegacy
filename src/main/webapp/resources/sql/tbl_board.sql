create table tbl_board(
    bno number(38) primary key,
    writer varchar2(100) not null,
    title varchar2(200) not null,
    content varchar2(4000) not null,
    viewcnt number(38) default 0,
    regdate timestamp
);

select * from tbl_board order by bno desc;

create sequence bno_seq
increment by 1
start with 1
nocache;

