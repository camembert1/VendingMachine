package com.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL         = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER        = "VENDINGMACHINE";
	private final String PASSWORD    = "VENDINGMACHINE";
	
	/**
	 * 입력받은 품번으로 PAYMENT에 결제 정보 INSERT하기
	 * @param choiceRowNum
	 * @return
	 */
	public int insertPaymentByRowNum(int choiceRowNum, String paymentMethod) {
		
		int result = 0;
		String sql = "INSERT INTO PAYMENT_TBL VALUES ((SELECT DRINK_NO FROM V_DRINK WHERE 가상품번 = ?), 1, ?, (SELECT DRINK_PRICE FROM V_DRINK WHERE 가상품번 = ?), DEFAULT)"; 
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, choiceRowNum);
			pstmt.setString(2, paymentMethod);
			pstmt.setInt(3, choiceRowNum);
			result = pstmt.executeUpdate();
			
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
