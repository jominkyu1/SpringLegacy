package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardDao;

@Service //Service 어노테이션을 추가함으로써 스프링에 Service임을 인식
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
}
