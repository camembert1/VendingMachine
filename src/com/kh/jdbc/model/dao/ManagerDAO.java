package com.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL         = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER        = "VENDINGMACHINE";
	private final String PASSWORD    = "VENDINGMACHINE";
	
	/**
	 * 관리자 로그인 - ID, PWD 입력받아서 같은 값 존재 여부 반환
	 * @param managerId
	 * @param managerPwd
	 * @return
	 */
	public int managerLogin(String managerId, String managerPwd) {
		
		int result = 0;
		String sql = "SELECT COUNT(*) AS M_COUNT FROM MANAGER_TBL WHERE MANAGER_ID = ? AND MANAGER_PWD = ?";
		
		try {
			
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, managerId);
			pstmt.setString(2, managerPwd);
			ResultSet rset = pstmt.executeQuery();
			
			if(rset.next()) result = rset.getInt(1);
			
			rset.close();
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
