package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BoardVO;

@Repository //Repository 어노테이션을 등록함으로써 스프링에 모델 DAO로 인식되게 한다.
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession; //MyBatis쿼리문을 수행할 sqlSession 주입
	
	@Override
	public void insertBoard(BoardVO b) {
		sqlSession.insert("board_in", b);
	}

	@Override
	public int getTotalCount() {
			//MyBatis에서 selectOne메서드는 단 한개의 레코드값만 반환하고, board_count는 board.xml에서 설정한 유일한 아이디명.
		return sqlSession.selectOne("board_count");
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		List<BoardVO> list = sqlSession.selectList("board_list", b);
		return list;
	}

	@Override
	public BoardVO getBoardCont(int bno) {
		return sqlSession.selectOne("board_cont", bno);
	}

	//조회수증가
	@Override
	public void updateHit(int bno) {
		sqlSession.update("board_hit", bno);
	}

	@Override
	public void updateBoard(BoardVO b) {
		sqlSession.update("board_update", b);
	}

	@Override
	public void deleteBoard(int bno) {
		sqlSession.delete("board_delete", bno);
	}
}