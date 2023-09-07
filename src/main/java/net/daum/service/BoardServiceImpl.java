package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}