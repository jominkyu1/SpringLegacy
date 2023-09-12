package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.MessageDao;
import net.daum.dao.PointDao;
import net.daum.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private PointDao pointDao;
	@Autowired
	private MessageDao messageDao;
	
	//메세지 insert + 메세지를 보낸사람에게 포인트 10점 업데이트
	//	-> 스프링의 AOP를 통한 트랜잭션 적용 대상
	
	@Transactional //트랜잭션 적용 (데이터 불일치현상 제거)
	@Override
	public void insertMessage(MessageVO vo) {
		messageDao.insertMessage(vo); //메세지추가
		pointDao.updatePoint(vo.getSender(), 10); //메세지를 보낸사람에게 포인트+10
	}
}
