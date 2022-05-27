package com.sist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnectionProvider {
	
	//db연결 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			String driver="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@192.168.35.4:1521:XE";
			String username="c##sist";
			String password="sist";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		
		return conn;
	}
	
	//close 해주는 메소드
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}

	}
}
