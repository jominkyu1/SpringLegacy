package net.daum.vo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO {
	private String userid;
	private String userpw;
	private String username;
	private String email;
	private Timestamp regdate;
}
