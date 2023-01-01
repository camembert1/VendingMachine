package com.kh.jdbc.controller;

import com.kh.jdbc.model.dao.ManagerDAO;

public class ManagerController {
	
	/**
	 * 관리자 로그인 성공 여부 반환
	 * @param managerId
	 * @param managerPwd
	 * @return
	 */
	public int loginManager(String managerId, String managerPwd) {
		ManagerDAO mDao = new ManagerDAO();
		int result = mDao.managerLogin(managerId, managerPwd);
		return result;
	}

}
