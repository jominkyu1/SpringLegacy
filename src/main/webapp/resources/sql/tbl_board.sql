CREATE TABLE tbl_board (
    bno     NUMBER(38) PRIMARY KEY,
    writer  VARCHAR2(100) NOT NULL,
    title   VARCHAR2(200) NOT NULL,
    content VARCHAR2(4000) NOT NULL,
    viewcnt NUMBER(38) DEFAULT 0,
    regdate TIMESTAMP
);


SELECT
    *
FROM
    tbl_board
ORDER BY
    bno DESC;

SELECT
    COUNT(*)
FROM
    tbl_board;

CREATE SEQUENCE bno_seq INCREMENT BY 1 START WITH 1 NOCACHE;

SELECT
    bno_seq.NEXTVAL
FROM
    dual;

SELECT
    *
FROM
    (
        SELECT
            ROWNUM rnum,
            bno,
            writer,
            title,
            content,
            viewcnt,
            regdate
        FROM
            (
                SELECT
                    *
                FROM
                    tbl_board
                ORDER BY
                    bno DESC
            )
    )
WHERE
        rnum >= 1
    AND rnum <= 10;
    
update tbl_board SET viewcnt =+ 1 where bno = 15;
commit;

update tbl_board set title='AA', content='BB' where bno=23;

delete from tbl_board where bno=23;

select * from tbl_board order by bno desc;

commit;