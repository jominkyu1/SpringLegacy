package net.daum.controller;

import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MyBatisTest {

	@Autowired
	private SqlSessionFactory sqlFactory;
	@Autowired
	private ApplicationContext ac;
	
	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
		Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);
	}
	
	@Test
	public void testSession() throws Exception{
		try(SqlSession sqlSession = sqlFactory.openSession()){
			System.out.println(sqlSession);
			//myBatis sqlSession으로 쿼리문을 수행함
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
