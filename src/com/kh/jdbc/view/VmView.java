package com.kh.jdbc.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.model.vo.Drink;
import com.kh.jdbc.model.vo.Manager;

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
		System.out.println("2. 자판기 관리\n   (관계자 外 금지!!)");
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
	 * 로그인을 위해 관리자 ID, PWD를 입력 받고 그걸 Manager 객체에 담아서 반환
	 * @return
	 */
	public Manager managerLoginInfo() {
		Manager manager = new Manager();
		System.out.print("관리자ID : ");
		manager.setManagerId(sc.next());
		System.out.print("관리자PWD : ");
		manager.setManagerPwd(sc.next());
		System.out.println();
		return manager;
	}
	
	/**
	 * 관리자 메뉴 출력, 선택 번호 반환
	 * @return
	 */
	public int manageMenu() {
		System.out.println("관리자메뉴선택" + dividingLine);
		System.out.println("1. 가격 및 수량 변경");
		System.out.println("2. 음료 추가");
		System.out.println("3. 수익 확인");
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
	 * 음료 품번 입력 받아서 반환(현금결제)
	 * (카드일 경우 inputMoney값으로 -1을 입력 받음)
	 * @return
	 */
	public int inputRowNumByCash(int inputMoney) {
		int choiceRowNum = 0;
		
		System.out.println("구매할 음료 품번을 입력하세요!");
		System.out.println("(투입 금액 반환을 원할 경우 0을 입력하세요!)");
		System.out.print(">> ");
		choiceRowNum = sc.nextInt();
		if (choiceRowNum == 0) {
		System.out.printf("투입하신 %s원을 반환합니다!%n%n", df.format(inputMoney));
		}
		return choiceRowNum;
	}
	
	/**
	 * 음료 품번 입력 받아서 반환(카드결제)
	 * @param paymentCardAmt
	 * @return
	 */
	public int inputRowNumByCard(int paymentCardAmt) {
		int choiceRowNum = 0;
		
		System.out.println("구매할 음료 품번을 입력하세요!");
		System.out.println("(더 이상 구매를 원하지 않을 경우 0을 입력하세요!)");
		System.out.print(">> ");
		choiceRowNum = sc.nextInt();
		if (choiceRowNum == 0) {
			if (paymentCardAmt != 0) {
				System.out.printf("%s원 결제중입니다!%n", df.format(paymentCardAmt));
				System.out.println("결제가 완료되었습니다. 카드를 제거하세요!");
			}
			System.out.println("카드를 제거해 주세요!");
		}
		return choiceRowNum;
	}
	
	/**
	 * 관리자 메뉴에서 쓸 품번 입력 받아서 반환
	 * @return
	 */
	public int inputRowNumForManage() {
		System.out.print("정보를 변경할 음료의 품번 : ");
		System.out.println("(정보 변경을 원하지 않을 경우 0을 입력하세요!)");
		int choiceRowNum = sc.nextInt();
		return choiceRowNum;
	}
	
	/**
	 * 새로 등록할 음료 정보 입력 받아서 Drink 객체에 담아 반환
	 * @return
	 */
	public Drink inputDrink() {
		Drink drink = new Drink();
		System.out.print("추가할 음료 제품명 :");
		System.out.println("(음료 추가를 원하지 않을 경우 0을 입력하세요!)");
		drink.setDrinkName(sc.next());
		if (!drink.getDrinkName().equals("0")) {
			System.out.print("추가할 음료 가격 : ");
			drink.setDrinkStock(sc.nextInt());
			System.out.print("추가할 음료 수량 : ");
			drink.setDrinkStock(sc.nextInt());
			System.out.println();
		}
		return drink;
	}
	
	/**
	 * 기존 음료의 수정 정보(가격, 재고) 입력 받아서 Drink 객체에 담아 반환
	 * @return
	 */
	public Drink modifyInfoDrink() {
		Drink drink = new Drink();
		System.out.print("변경 예정 가격 : ");
		drink.setDrinkPrice(sc.nextInt());
		System.out.print("변경 예정 수량 : ");
		drink.setDrinkStock(sc.nextInt());
		System.out.println();
		return drink;
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
	 * 관리자 창에서 음료 목록 전체 출력 (재고까지 출력 됨)
	 * @param dList
	 */
	public void showAllManageOpt(List<Drink> dList) {
		System.out.println("음료 목록 전체 출력" + dividingLine);
		for (Drink dOne : dList) {
			System.out.printf("[%d] %s%n%9s원"
							, dOne.getRowNum(), dOne.getDrinkName(), df.format(dOne.getDrinkPrice()));
			if (dOne.getDrinkStock() == 0) System.out.print(" [X]");
			else System.out.printf(" [%d]", dOne.getDrinkStock());
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

	
	/**
	 * 현금, 카드, 총 수익 출력
	 * @param cashProfit
	 * @param cardProfit
	 */
	public void printProfit(int cashProfit, int cardProfit) {
		System.out.printf("현금 : %s원%n", df.format(cashProfit));
		System.out.printf("카드 : %s원%n", df.format(cardProfit));
		System.out.println(dividingLine);
		System.out.printf("총 : %s원%n", df.format(cashProfit + cardProfit));
		System.out.println();
	}

	

	

	
	


}
