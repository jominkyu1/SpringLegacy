package net.daum.controller;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConTest {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "night";
	private static final String PW = "123456";
	
	@Test //JUnit Test Annotation
	public void testCon() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			// JAVA 7이후부터 추가된 try with resources문 (AutoClosable 인터페이스를 상속받은 객체 자동 close)
			System.out.println(con);
			assertNotNull(con);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
