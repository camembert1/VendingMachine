package com.kh.jdbc.controller;

import com.kh.jdbc.model.dao.PaymentDAO;

public class PaymentController {
	PaymentDAO pDao = new PaymentDAO();
	
	/**
	 * 결제정보 INSERT 성공여부 반환
	 * @param choiceRowNum
	 * @return
	 */
	public int minusStockByRowNum(int choiceRowNum) {
		int result = pDao.insertPaymentByRowNum(choiceRowNum);
		return result;
	}

}
