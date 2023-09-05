--TBL_MEMBER TABLE CREATE
create table tbl_member(
    userid varchar2(50) primary key,
    userpw varchar2(50) not null,
    username varchar2(50) not null,
    email varchar2(100),
    regdate timestamp default sysdate
);

select * from tbl_member order by userid desc;

create SEQUENCE tbl_sec
START WITH 1
INCREMENT by 1
nocache;

select tbl_sec.nextval from dual;