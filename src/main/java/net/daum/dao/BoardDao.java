package net.daum.dao;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardDao {
	public void insertBoard(BoardVO b);
	public int getTotalCount();
	public List<BoardVO> getBoardList(BoardVO b);
	
	//내용보기
	public BoardVO getBoardCont(int bno);
	public void updateHit(int bno);
	
	public void updateBoard(BoardVO b);
	public void deleteBoard(int bno);
}
