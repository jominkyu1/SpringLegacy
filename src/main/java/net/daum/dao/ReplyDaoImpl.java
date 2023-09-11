package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao{

	@Autowired //MyBatis
	private SqlSession sqlSession;

	@Override
	public void addReply(ReplyVO vo) {
		sqlSession.insert("reply_in", vo);
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return sqlSession.selectList("list_in", bno);
	}

	@Override
	public void editReply(ReplyVO vo) {
		sqlSession.update("reply_edit", vo);
	}

	@Override
	public void deleteReply(int rno) {
		sqlSession.delete("reply_del", rno);
	}
}	
