package net.daum.vo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BoardVO {
	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;
	private int replycnt;
	private Timestamp regdate;
	
	//페이징
	private int startrow; //시작행 번호
	private int endrow; //끝행 번호
	
}