package net.daum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.daum.dao.MemberDao;
import net.daum.dao.MemberDaoImpl;
import net.daum.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MemberDaoTest {
	
	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO m = new MemberVO();
		m.setUserid("testid");
		m.setUserpw("123456");
		m.setUsername("홍길동");
		m.setEmail("test@test.com");
		
		memberDao.insertMember(m);
	}
}
