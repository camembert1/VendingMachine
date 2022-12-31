package com.kh.jdbc.run;

import java.util.List;

import com.kh.jdbc.controller.DrinkController;
import com.kh.jdbc.controller.PaymentController;
import com.kh.jdbc.model.vo.Drink;
import com.kh.jdbc.view.VmView;

public class VmRun {
	static VmView vmView = new VmView();
	static DrinkController dCon = new DrinkController();
	static PaymentController pCon = new PaymentController();
	
	
	
	/*
	 * 여기서 언급하는 '품번'은 DRINK_TBL의 DRINK_NO가 아니고
	 * V_DRINK의 '가상품번(ROWNUM)'임!!
	 * 편의상 그냥 품번이라고 하겠음!!!!!!
	 */

	/**
	 * 메인메소드
	 * @param args
	 */
	public static void main(String[] args) {

		// 음료 구매 or 자판기 관리 메인 메뉴 while문
		goodbye: while (true) {
			int choice = vmView.mainMenu();
			switch (choice) {

			case 1: // 1. 음료 구매
				buyDrink();
				break;

			case 2: // 2. 자판기 관리
				manageVMachine();
				break;

			case 0: // 0. 안 사요(프로그램 종료)
				vmView.printMessage("BYE!");
				break goodbye;

			default: // 0, 1, 2 이외의 번호 선택 시!
				vmView.printDefaultMessage();
				break;
				
			}
		}
	}

	/**
	 * 메인메뉴 1. 음료 구매 현금 or 카드 while문
	 */
	public static void buyDrink() {
		
		int result = 0;
		Drink drink = null;
		
		// 얘네 둘은 또 안 쓰면 걍 안으로 넣기
		int resultStock = 0;
		int drinkPrice = 0;
		
		toMain:
		while (true) {
			int choicePayment = vmView.paymentMenu();
			switch (choicePayment) {

			case 1: // 1. 현금

				printDrinkAll(); // 제품 목록 출력
				int inputMoney = vmView.inputMoney(); // 투입 금액 입력 받음
				
				end:
				while (true) {
					printDrinkAll(); // 예외 상황 늘어나면 제품 목록 묻혀서 걍 반복문에 넣어줌
					vmView.printInputMoney(inputMoney); // 투입 금액 출력
					
					// 구매할 음료 품번 입력 받기
					int choiceRowNum = vmView.inputRowNum(inputMoney);
					
					// 입력 받은 품번이 0이라면 투입 금액을 돌려주고 종료
					if (choiceRowNum == 0) {
						inputMoney = 0;
						break end;
					} 
					
					drink = dCon.drinkByRowNum(choiceRowNum);
					drinkPrice = drink.getDrinkPrice();
					
					resultStock = dCon.stockByRowNum(choiceRowNum);
					switch (resultStock) {
					case -1: // 품번을 잘못입력했다면
						vmView.printDefaultMessage();
						break;
					
					case 0: // 품번은 제대로 입력했으나 해당 상품의 재고가 없다면
						vmView.printMessage("재고가 없습니다!");
						break;
						
					default:
						if (drinkPrice > inputMoney) {
							// 품번, 재고 ㄱㅊ 근데
							// 구매하고자 하는 음료보다 투입 금액이 부족하다면
							vmView.printMessage("투입 금액이 부족합니다!");
							
						} else { // 물건을 구매할 수 있는 상태라면
							
							// 결제내역 추가하고
							pCon.minusStockByRowNum(choiceRowNum);
							
							// 재고 -1하고 음료 하나 뽑힘
							result = dCon.minusStockByRowNum(choiceRowNum);
							if (result > 0) vmView.printMessage(drink.getDrinkName() + "(이)가 나왔습니다!");
							
							// 만약에 재고 -1 실패하면
							else vmView.printMessage("음료가 안 나왔습니다!\n관리자를 호출하세요!");
							
							
							// 투입금액 차감하고 출력하기
							inputMoney -= drink.getDrinkPrice();
							vmView.printInputMoney(inputMoney);
							
							// 구매 후 남은 투입 금액이 0이 되면 걍 자동종료!
							if (inputMoney == 0) break end;
						}
						break;
					}
				}
				break;

			case 2: // 2. 카드

				break;

			case 0: break toMain; // 0. 시작화면으로 돌아가기

			default:
				vmView.printDefaultMessage();
				break;

			}
		}
	}

	/**
	 * 메인메뉴 2. 자판기 관리 재고 or 수익 while문
	 */
	public static void manageVMachine() {

		toMain: 
		while (true) {
			int choiceManage = vmView.manageMenu();
			switch (choiceManage) {
			
			case 1: // 1. 재고 확인 및 수정

				break;

			case 2: // 2. 수익 확인

				break;

			case 0: // 0. 시작화면으로 돌아가기

				break toMain;

			default:
				vmView.printDefaultMessage();
				break;
			}
		}
	}
	
	
	// view랑 controller 다 써서 Run에다 만들었는데 ㄱㅊ한가??
	/**
	 * 음료 목록 전체 출력
	 */
	public static void printDrinkAll() {
		
		List<Drink> dList = null;
		
		dList = dCon.printAll();
		if (!dList.isEmpty()) vmView.showAll(dList);
		else vmView.printMessage("음료가 없습니다!");
		
	}
	
}
