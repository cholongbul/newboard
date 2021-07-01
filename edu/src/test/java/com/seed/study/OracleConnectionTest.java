package com.seed.study;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConnectionTest {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "SEED";
	private static final String PW = "SEED";

	@Test
	public void test() throws ClassNotFoundException {
		Class.forName(DRIVER);
		try (Connection conn = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println("오라클 연결");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
