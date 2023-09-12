package net.daum.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDaoImpl implements PointDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) { //메세지를 보낸사람에게 10점 업데이트
		Map<String, Object> pm = new HashMap<String, Object>();
		pm.put("sender", sender); //sender 키 이름에 보낸사람 저장
		pm.put("point", point); // point.xml에서는 키 이름을 참조해서 값을 가져온다
		
		sqlSession.update("pointUp", pm);
	}
}
