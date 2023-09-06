package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //Repository 어노테이션을 등록함으로써 스프링에 모델 DAO로 인식되게 한다.
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession; //MyBatis쿼리문을 수행할 sqlSession 주입
}