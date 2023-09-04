package net.daum.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class DataSourceTest {

	@Inject // Defendency Injection 설정: 참조변수에 객체주소를 주입해서 실제 사용가능하게 한다.
	private DataSource ds;
	
	@Test
	public void testCon() throws Exception{
		try(Connection con = ds.getConnection()){
			log.info("getSchema:: {}", con.getSchema());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
