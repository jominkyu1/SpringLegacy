package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.daum.dao.BoardDao;
import net.daum.dao.ReplyDao;
import net.daum.vo.ReplyVO;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private BoardDao boardDao;

	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) { //댓글추가되면 tbl_board의 replycnt +1
		replyDao.addReply(vo); 
		//replycnt +1로직
		//boardDao.addReplyCnt(vo.getBno());
		boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return replyDao.listReply(bno);
	}

	@Override
	public void editReply(ReplyVO vo) {
		replyDao.editReply(vo);
	}

	@Transactional
	@Override
	public void deleteReply(int rno) { //댓글삭제되면 tbl_board의 replycnt -1
		//replycnt-1 로직 (카운트 감소가 선행되어야함)
		//boardDao.minusReplyCnt(rno);
		int bno = replyDao.getBno(rno);
		boardDao.updateReplyCnt(bno, -1);
		replyDao.deleteReply(rno);
	}
}
