package com.kh.jdbc.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.model.vo.Drink;

public class VmView {
	
	Scanner sc = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("#,###");
	String dividingLine = "=== === === === === ===";
	
	/**
	 * 구매 or 관리 메뉴 출력, 선택 번호 반환
	 * @return
	 */
	public int mainMenu() {
		System.out.println(dividingLine);
		System.out.println("1. 음료 구매");
		System.out.println("2. [주의] 자판기 관리");
		System.out.println("0. 안 사요");
		System.out.print(">> ");
		int choice = sc.nextInt();
		System.out.println();
		return choice;
	}
	
	/**
	 * 결제 수단 메뉴 출력, 선택 번호 반환
	 * @return
	 */
	public int paymentMenu() {
		System.out.println("결제수단선택" + dividingLine);
		System.out.println("1. 현금");
		System.out.println("2. 카드");
		System.out.println("0. 시작화면으로 돌아가기");
		System.out.print(">> ");
		int choicePayment = sc.nextInt();
		System.out.println();
		return choicePayment;
	}
	
	/**
	 * 관리자 메뉴 출력, 선택 번호 반환
	 * @return
	 */
	public int manageMenu() {
		System.out.println("관리자메뉴선택" + dividingLine);
		System.out.println("1. 재고 확인 및 수정");
		System.out.println("2. 수익 확인");
		System.out.println("0. 시작화면으로 돌아가기");
		System.out.print(">> ");
		int choiceManage = sc.nextInt();
		System.out.println();
		return choiceManage;
	}
	
	/**
	 * 지폐투입구 - 투입 금액 입력 받아서 반환
	 * @return
	 */
	public int inputMoney() {
		System.out.print("[지폐투입구] >> ");
		int inputMoney = sc.nextInt();
		return inputMoney;
	}
	
	/**
	 * 음료 품번 입력받아서 반환
	 * @return
	 */
	public int inputRowNum(int inputMoney) {
		System.out.println("구매할 음료 품번을 입력하세요!");
		System.out.println("(투입 금액 반환을 원할 경우 0을 입력하세요!)");
		System.out.print(">> ");
		int choiceRowNum = sc.nextInt();
		if (choiceRowNum == 0) {
			System.out.printf("투입하신 %s원을 반환합니다!%n%n", df.format(inputMoney));
		}
		return choiceRowNum;
	}
	
	/**
	 * 음료 목록 전체 출력
	 * @param dList
	 */
	public void showAll(List<Drink> dList) {
		System.out.println("음료 목록 전체 출력" + dividingLine);
		for (Drink dOne : dList) {
			System.out.printf("[%d] %s%n%9s원"
							, dOne.getRowNum(), dOne.getDrinkName(), df.format(dOne.getDrinkPrice()));
			if (dOne.getDrinkStock() == 0) System.out.print(" [X]");
			System.out.printf("%n%n");
		}
		System.out.println();
	}
	
	/**
	 * 투입 금액 출력
	 * @param inputMoney
	 */
	public void printInputMoney(int inputMoney) {
		String inputMoneyStr = df.format(inputMoney);
		System.out.println("투입금액 : " + inputMoneyStr);
		System.out.println();
	}
	
	/**
	 * 입력 받은 문구 출력
	 * @param msg
	 */
	public void printMessage(String msg) {
		System.out.println(msg);
		System.out.println();
	}
	
	/**
	 * switch문 default메시지 - 잘못입력하심!
	 */
	public void printDefaultMessage() {
		System.out.println(dividingLine);
		System.out.println("잘못입력하셨습니다!");
		System.out.println(dividingLine);
		System.out.println();
	}

	

	

	
	


}
