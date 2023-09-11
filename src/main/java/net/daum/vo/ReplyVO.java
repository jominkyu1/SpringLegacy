package net.daum.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data // setter(), getter(), toString()등의 메서드 자동 제공
public class ReplyVO {

	// tbl_reply 테이블
	private int rno;
	private int bno;
	private String replyer;
	private String replytext;
	private Timestamp regdate;
	private Timestamp updatedate;
}
