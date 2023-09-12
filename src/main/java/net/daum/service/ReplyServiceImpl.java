package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDao;
import net.daum.dao.ReplyDao;
import net.daum.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private BoardDao boardDao;

	@Override
	public void addReply(ReplyVO vo) { //댓글추가되면 tbl_board의 replycnt +1
		replyDao.addReply(vo); 
		//replycnt +1로직
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return replyDao.listReply(bno);
	}

	@Override
	public void editReply(ReplyVO vo) {
		replyDao.editReply(vo);
	}

	@Override
	public void deleteReply(int rno) { //댓글삭제되면 tbl_board의 replycnt -1
		replyDao.deleteReply(rno);
		//replycnt-1 로직
	}
}
