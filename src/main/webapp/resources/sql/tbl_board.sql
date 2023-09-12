CREATE TABLE tbl_board
(
    bno     NUMBER(38) PRIMARY KEY,
    writer  VARCHAR2(100)  NOT NULL,
    title   VARCHAR2(200)  NOT NULL,
    content VARCHAR2(4000) NOT NULL,
    viewcnt NUMBER(38) DEFAULT 0,
    regdate TIMESTAMP
);


SELECT *
FROM tbl_board
ORDER BY bno DESC;

-- 댓글 개수를 저장할 replycnt 컬럼 추가
ALTER TABLE tbl_board
    ADD replycnt NUMBER(38) DEFAULT 0;

-- 각 게시판 번호에 해당하는 실제 댓글수를 카운트해서 세롭게 추가된 replycnt컬럼 레코드값으로 수정
update tbl_board
set replycnt = (select count(*) from tbl_reply where bno = tbl_board.bno);


-- tbl_board 테이블의 bno 컬럼에 시퀀스 추가
CREATE SEQUENCE bno_seq INCREMENT BY 1 START WITH 1 NOCACHE;

SELECT bno_seq.NEXTVAL
FROM dual;

SELECT *
FROM (SELECT ROWNUM rnum,
             bno,
             writer,
             title,
             content,
             viewcnt,
             regdate
      FROM (SELECT *
            FROM tbl_board
            ORDER BY bno DESC))
WHERE rnum >= 1
  AND rnum <= 10;

update tbl_board
SET viewcnt = + 1
where bno = 15;
commit;

update tbl_board
set title='AA',
    content='BB'
where bno = 23;

delete
from tbl_board
where bno = 23;

select *
from tbl_board
order by bno desc;

commit;


-- 댓글 테이블 생성
create table tbl_reply
(
    rno        number(38) primary key, --댓글번호
    bno        number(38) default 0,   -- 게시판 번호( 외래키 참조 )
    replyer    varchar2(50)   not null,
    replytext  varchar2(4000) not null,
    regdate    timestamp,              -- 댓글등록날짜
    updatedate timestamp               -- 댓글 수정날짜
);

select *
from tbl_reply
order by rno desc;

--tbl_reply 테이블의 bno 컬럼에 외래키 추가 설정
alter table tbl_reply
    add constraint tbl_reply_bno_fk
        foreign key (bno) references tbl_board (bno);

--tbl_reply rno에 사용할 시퀀스 생성
create sequence rno_seq
    start with 1
    increment by 1
    nocache
    nocycle;

select rno_seq.nextval
from dual;

select *
from tbl_reply
order by rno desc;
select *
from tbl_board;

delete
from tbl_reply
where rno = 2;
