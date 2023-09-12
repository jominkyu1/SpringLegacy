package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

/*	TBL_USER 테이블
 * 	
 * */

@Setter @Getter
public class UserVO {
	private String uid2; //회원아이디 PK
	private String upw;	//비밀번호
	private String uname; //회원이름
	private int upoint; // 보낸메세지 하나당 +10점 (기본값: 0)
}
