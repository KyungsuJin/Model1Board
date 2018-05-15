package com.model1.board.driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DbConnection {
	public static Connection DbConn() throws ClassNotFoundException, SQLException {
		System.out.println("DbConnection.DbConn 메서드 실행");
		Connection connection = null;
		//로컬용
		String jdbcDriver = "jdbc:mysql://localhost:3306/pds?useUnicode=true&characterEncoding=utf-8";
		String dbId = "root";
		String dbPw = "java0000";
		
		/* 호스팅용
		String jdbcDriver = "jdbc:mysql://localhost:3306/kyungsu93?useUnicode=true&characterEncoding=utf-8";
		String dbId = "kyungsu93";
		String dbPw = "aa456456!!";
		*/
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcDriver, dbId, dbPw);
		System.out.println("DbConnection.DbConn 메서드 종료");
		return connection;
	}
}
