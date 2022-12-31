package com.kh.jdbc.controller;

import java.util.List;

import com.kh.jdbc.model.dao.DrinkDAO;
import com.kh.jdbc.model.vo.Drink;

public class DrinkController {
	DrinkDAO dDao = new DrinkDAO();
	
	/**
	 * 전체 출력을 위해 view로 전달할 list 반환
	 * @return
	 */
	public List<Drink> printAll() {
		List<Drink> dList = dDao.selectAll();
		return dList;
	}
	
	/**
	 * 입력받은 품번으로 Drink 반환
	 * @param choiceRowNum
	 * @return
	 */
	public Drink drinkByRowNum(int choiceRowNum) {
		Drink drink = dDao.selectByRowNum(choiceRowNum);
		return drink;
	}
	
	/**
	 * 입력받은 품번으로 재고 반환
	 * @param choiceRowNum
	 * @return
	 */
	public int stockByRowNum(int choiceRowNum) {
		int result = dDao.selectStockByRowNum(choiceRowNum);
		return result;
	}

	/**
	 * 재고 -1 성공여부 반환
	 * @param choiceRowNum
	 * @return
	 */
	public int minusStockByRowNum(int choiceRowNum) {
		int result = dDao.updateStockByRowNum(choiceRowNum);
		return result;
	}

}
