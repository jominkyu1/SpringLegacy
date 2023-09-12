package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MessageVO;

@Repository
public class MessageDaoImpl implements MessageDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertMessage(MessageVO vo) {
		// message_in이 message.xml에서 설정할 유일 아이디명, insert()메서드가 레코드를 저장시킴
		sqlSession.insert("message_in", vo);
	}
}
