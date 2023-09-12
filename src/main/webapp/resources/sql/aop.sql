-- 스프링 AOP와 트랜잭션 실습을위한 샘플 테이블 설계

create table tbl_user (
    uid2 varchar2(50) primary key, -- 회원 아이디
    upw varchar2(50) not null, -- 비밀번호
    uname varchar2(100) not null, -- 회원이름
    upoint number(38) default 0  -- 메세지가 보내지면 포인트 10점
);


insert into tbl_user (uid2, upw, uname) values ('user00', '12345', '홍길동');
insert into tbl_user (uid2, upw, uname) values ('user01', '56789', '강감찬');

select * from tbl_user order by uid2;

create table tbl_message(
    mid number(38) primary key,
    targetid varchar2(50) not null, --Foreign key ( tbl_user테이블의 ui2 )
    sender varchar2(50) not null, --보내는사람
    message varchar2(1000) not null, --보낸메세지
    senddate timestamp 
);

alter table tbl_message add constraint tbl_message_targetid_fk
foreign key(targetid) references tbl_user(uid2);

create sequence mid_no_seq
start with 1
increment by 1
nocache;

select * from tbl_message order by mid;

select mid_no_seq.nextval as "mid_no_seq 다음 번호값" from dual;