package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.BoardDao;
import net.daum.vo.BoardVO;

@Service //Service 어노테이션을 추가함으로써 스프링에 Service임을 인식
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public void insertBoard(BoardVO b) {
		// TODO Auto-generated method stub
		boardDao.insertBoard(b);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return boardDao.getTotalCount();
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return boardDao.getBoardList(b);
	}

	//조회수 증가+내용보기 -> 스프링의 AOP를 통한 트랜잭션 적용대상 (데이터 불일치 현상 제거)
	@Transactional(isolation = Isolation.READ_COMMITTED)
	//트랜잭션 격리(isolation)
	//READ_COMMITED는 커밋된 데이터에 대해 읽기 허용
	@Override
	public BoardVO getBoardCont(int bno) { 
		boardDao.updateHit(bno);
		return boardDao.getBoardCont(bno);
	}

	//수정폼 진입시 조회수증가는 하지않고 내용만 리턴
	@Override
	public BoardVO getBoardCont2(int bno) {
		return boardDao.getBoardCont(bno);
	}

	@Override
	public void updateBoard(BoardVO b) {
		boardDao.updateBoard(b);
	}

	@Override
	public void deleteBoard(int bno) {
		boardDao.deleteBoard(bno);
	}
}