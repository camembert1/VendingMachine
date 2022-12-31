package com.kh.jdbc.model.vo;

public class Drink {
	
	private int rowNum;
	private int drinkNo;
	private String drinkName;
	private int drinkPrice;
	private int drinkStock;
	
	public Drink() {
		super();
	}
	public Drink(int rowNum, int drinkNo, String drinkName, int drinkPrice, int drinkStock) {
		super();
		this.rowNum = rowNum;
		this.drinkNo = drinkNo;
		this.drinkName = drinkName;
		this.drinkPrice = drinkPrice;
		this.drinkStock = drinkStock;
	}

	public int getRowNum() {
		return rowNum;
	}
	
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public int getDrinkNo() {
		return drinkNo;
	}

	public void setDrinkNo(int drinkNo) {
		this.drinkNo = drinkNo;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	public int getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(int drinkPrice) {
		this.drinkPrice = drinkPrice;
	}

	public int getDrinkStock() {
		return drinkStock;
	}

	public void setDrinkStock(int drinkStock) {
		this.drinkStock = drinkStock;
	}

	
	@Override
	public String toString() {
		return "Drink [rowNum=" + rowNum + ", drinkNo=" + drinkNo + ", drinkName=" + drinkName + ", drinkPrice=" + drinkPrice + ", drinkStock="
				+ drinkStock + "]";
	}
	
}
