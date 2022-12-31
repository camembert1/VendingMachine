package com.kh.oop;

public class Beverage {
	private String name;
	private int price;
	private int stock;
	public int itemNum;

	public Beverage() {}

	// setter �޼ҵ�
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	

	// getter �޼ҵ�
	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public int getStock() {
		return this.stock;
	}

}
