package com.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.model.vo.Drink;

public class DrinkDAO {
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL         = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER        = "VENDINGMACHINE";
	private final String PASSWORD    = "VENDINGMACHINE";

	/**
	 * 음료 전체 조회
	 * @return
	 */
	public List<Drink> selectAll() {
		
		String sql = "SELECT ROWNUM, D.* FROM DRINK_TBL D";
		Drink drink = null;
		List<Drink> dList = new ArrayList<Drink>();
		
		try {
			
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				drink = new Drink(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getInt(4), rset.getInt(5));
				dList.add(drink);
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dList;
	}
	
	/**
	 * 입력받은 품번으로 Drink 반환
	 * @param choiceRowNum
	 * @return
	 */
	public Drink selectByRowNum(int choiceRowNum) {
		
		Drink drink = null;
		String sql = "SELECT * FROM V_DRINK WHERE 가상품번 = ?";

		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, choiceRowNum);
			ResultSet rset = pstmt.executeQuery();
			if (rset.next()) drink = new Drink(rset.getInt(1), rset.getInt(2), rset.getString(3), rset.getInt(4), rset.getInt(5));
			
			rset.close();
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drink;
	}
	
	/**
	 * 입력받은 품번으로 재고 조회해서 반환 (없는 품번 입력할 경우 -1 반환)
	 * @param choiceRowNum
	 * @return
	 */
	public int selectStockByRowNum(int choiceRowNum) {
		
		int result = 0;
		String sql = "SELECT DRINK_STOCK FROM V_DRINK WHERE 가상품번 = ?";
		
		try {
			
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, choiceRowNum);
			ResultSet rset = pstmt.executeQuery();
			
			if (!rset.next()) result = -1;
			else result = rset.getInt(1);
			
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
	
	/**
	 * 입력받은 품번으로 재고 -1하기(음료뽑기!)
	 * @param choiceRowNum
	 * @return
	 */
	public int updateStockByRowNum(int choiceRowNum) {
		
		int result = 0;
		String sql = "UPDATE DRINK_TBL SET DRINK_STOCK = DRINK_STOCK - 1 WHERE DRINK_NO = (SELECT DRINK_NO FROM V_DRINK WHERE 가상품번 = ?)";
		
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, choiceRowNum);
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
