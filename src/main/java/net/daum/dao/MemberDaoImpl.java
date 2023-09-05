package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository //Repository 어노테이션등록 -> Spring이 DAO로 빈관리
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertMember(MemberVO m) {
		sqlSession.insert("mem_in", m);
		/*	MyBatis에서 Insert메서드는 레코드를 저장시킨다.
		 * 	mem_in은 /net/daum/mappers/mybatis/member_test.xml에서 설정한 유일 아이디명이다.
		 * */
		
	}
}