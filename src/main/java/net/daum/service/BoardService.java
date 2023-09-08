package net.daum.service;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardService {

	public void insertBoard(BoardVO b);
	public int getTotalCount();
	public List<BoardVO> getBoardList(BoardVO b);
	
	//내용보기폼
	public BoardVO getBoardCont(int bno);
	//내용수정폼
	public BoardVO getBoardCont2(int bno);
	//내용수정
	public void updateBoard(BoardVO b);
	public void deleteBoard(int bno);
}
