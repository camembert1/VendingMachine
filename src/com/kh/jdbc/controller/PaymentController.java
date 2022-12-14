package com.kh.jdbc.controller;

import com.kh.jdbc.model.dao.PaymentDAO;

public class PaymentController {
	PaymentDAO pDao = new PaymentDAO();
	
	/**
	 * 결제정보 INSERT 성공여부 반환
	 * @param choiceRowNum
	 * @return
	 */
	public int paymentByRowNum(int choiceRowNum, String paymentMethod) {
		int result = pDao.insertPaymentByRowNum(choiceRowNum, paymentMethod);
		return result;
	}
	/**
	 * 수익 조회 결과 반환
	 * @param paymentMethod
	 * @return
	 */
	public int checkProfit(String paymentMethod) {
		PaymentDAO paymentDAO = new PaymentDAO();
		int profit = paymentDAO.selectSumProfit(paymentMethod);
		return profit;
	}

}
