package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.ReplyDao;
import net.daum.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;

	@Override
	public void addReply(ReplyVO vo) {
		replyDao.addReply(vo);
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
	public void deleteReply(int rno) {
		replyDao.deleteReply(rno);
	}
}
