package com.kh.jdbc.model.vo;

import java.sql.Timestamp;

public class Payment {
	
	private int productNo;
	private int productQuantity;
	private String paymentMethod;
	private int paymentAmount;
	private Timestamp soldTime;
	
	public Payment() {
		super();
	}
	public Payment(int productNo, int productQuantity, String paymentMethod, int paymentAmount, Timestamp soldTime) {
		super();
		this.productNo = productNo;
		this.productQuantity = productQuantity;
		this.paymentMethod = paymentMethod;
		this.paymentAmount = paymentAmount;
		this.soldTime = soldTime;
	}

	
	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Timestamp getSoldTime() {
		return soldTime;
	}

	public void setSoldTime(Timestamp soldTime) {
		this.soldTime = soldTime;
	}

	
	@Override
	public String toString() {
		return "Payment [productNo=" + productNo + ", productQuantity=" + productQuantity + ", paymentMethod="
				+ paymentMethod + ", paymentAmount=" + paymentAmount + ", soldTime=" + soldTime + "]";
	}
	
}
