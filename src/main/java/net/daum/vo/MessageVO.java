package net.daum.vo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/*	TBL_MESSAGE 테이블
 * 
 * */

@Setter 
@Getter
public class MessageVO {
	private int mid; //PK
	private String targetid; //FK ->uid2
	private String sender;
	private String message;
	private Timestamp senddate;
}
