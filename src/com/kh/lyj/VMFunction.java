package com.kh.lyj;

import java.text.DecimalFormat;
import java.util.Scanner;

public class VMFunction {
	DecimalFormat df = new DecimalFormat("#,###");
	Scanner sc = new Scanner(System.in);
	private int vmCashProfit = 0;
	private int vmCardProfit = 0;
	private String PW = "0618";
	public String dividingLine = "========================================";

	Beverage[] beverages;

	/**
	 * 객체 배열 선언 및 초기화 + 기본 음료 추가
	 */
	public VMFunction() {
		beverages = new Beverage[30];
		for (int i = 0; i < beverages.length; i++) {
			beverages[i] = new Beverage();
		}

		beverages[25].setName("삼다수");
		beverages[25].setPrice(1100);
		beverages[25].setStock(3);

		beverages[26].setName("포카리스웨트(PET)");
		beverages[26].setPrice(2700);
		beverages[26].setStock(10);

		beverages[27].setName("펩시콜라");
		beverages[27].setPrice(1700);
		beverages[27].setStock(0);

		beverages[28].setName("칠성사이다");
		beverages[28].setPrice(1800);
		beverages[28].setStock(10);

		beverages[29].setName("조지아커피");
		beverages[29].setPrice(1000);
		beverages[29].setStock(10);
	}

	/**
	 * 구매 or 관리 선택
	 * 
	 * @return choice
	 */
	public int printMenu() {

		System.out.println(dividingLine);
		System.out.println("1. 음료 구매");
		System.out.println("2. 관리자 메뉴");
		System.out.println("0. 종료");
		System.out.print(">> ");
		int choice = sc.nextInt();
		System.out.println();
		return choice;
	}

	/**
	 * 음료 구매
	 */
	public void buyBeverage() {

		// 현금 or 카드

		// 이 while문을 나오면 buyBeverage메서드가 끝나고
		// 초기 화면으로 돌아감
		INIT: while (true) {

			// 결제방법 입력받기
			System.out.println(dividingLine);
			System.out.println("결제 수단 선택");
			System.out.println("1. 현금");
			System.out.println("2. 카드");
			System.out.print(">> ");
			int paymentMethodNum = sc.nextInt();
			System.out.println();

			switch (paymentMethodNum) {
			case 1: // 현금 결제
				int inputMoney = 0; // 투입금액
				String inputMoneyStr = df.format(inputMoney); // 투입 금액을 #,###꼴로 바꿔주는 문자열
				int inputItemNum = 0; // 구매 상품 품번
				int k = 0; // 품번 부여할 때 쓸 거

				System.out.println(dividingLine);

				// 제품명, 가격 목록 출력
				for (int i = 0; i < beverages.length; i++) {

					String name = beverages[i].getName();
					int price = beverages[i].getPrice();
					int stock = beverages[i].getStock();

					String priceStr = df.format(price); // 금액을 #,###꼴로 바꿔주는 문자열 만들기

					if (name != null) { // 30칸 중에 비어있는 칸은 출력 X

						// 진열 순서대로 품번 부여해서 출력
						k++;
						int itemNum = beverages[i].itemNum = k;
						System.out.printf("[%d] %s%n%9s원", itemNum, name, priceStr);
						if (stock == 0) {
							System.out.print(" [X]"); // 재고 0이면 X출력
						}
						System.out.println();
						System.out.println();
					}
				} // 목록 출력 끝

				System.out.print("투입 <<금액>>을 입력해 주세요 >> ");
				inputMoney = sc.nextInt();
				vmCashProfit += inputMoney;
				System.out.println();

				// 이 while문을 나오면
				// 결제수단 선택 switch문의 case1끝으로 이동해
				// break INIT을 만나 초기화면으로 돌아감
				EndCase1: while (true) {
					k = 0; // 품번 초기화

					// 제품명, 가격 목록 출력
					System.out.println(dividingLine);
					for (int i = 0; i < beverages.length; i++) {

						String name = beverages[i].getName();
						int stock = beverages[i].getStock();
						int price = beverages[i].getPrice();
						String priceStr = df.format(price); // 금액을 #,###꼴로 바꿔주는 문자열 만들기

						if (name != null) {

							// 가격 문자열 초기화
							priceStr = df.format(price);

							// 진열 순서대로 품번 부여해서 출력
							k++;
							int itemNum = beverages[i].itemNum = k;
							System.out.printf("[%d] %s%n%9s원", itemNum, name, priceStr);
							if (stock == 0) { // 재고가 없다면 끝에 X 출력
								System.out.print(" [X]");
							}
							System.out.println();
							System.out.println();
						}
					} // 목록 출력 끝

					inputMoneyStr = df.format(inputMoney);
					System.out.println(dividingLine);
					System.out.printf("투입 금액 : %s원%n", inputMoneyStr);

					// 품번 범위를 위해 품번의 최댓값 구하기
					int max = beverages[0].itemNum;
					for (int i = 0; i < beverages.length; i++) {
						if (beverages[i].itemNum > max) {
							max = beverages[i].itemNum;
						}
					}

					System.out.println("구매할 음료 품번을 입력하세요.");
					System.out.println("(투입 금액 반환을 원할 경우 0을 입력하세요.)");
					System.out.print(" >> ");
					inputItemNum = sc.nextInt();

					if (inputItemNum == 0) {
						System.out.println();
						System.out.println(dividingLine);
						System.out.printf("투입하신 %s원을 반환합니다.%n", inputMoneyStr);
						vmCashProfit -= inputMoney;
						inputMoney = 0;
						break EndCase1; // case1(현금)의 끝에서 break INIT;을 만나 초기화면으로 돌아감
					} else if (inputItemNum > max) {
						System.out.println();
						System.out.println(dividingLine);
						System.out.println("잘못 입력하셨습니다!!");
						continue;
					} else {
						// 입력한 품번과 같은 품번을 가진 제품 찾기
						for (int i = 0; i < beverages.length; i++) {

							int price = beverages[i].getPrice();
							int stock = beverages[i].getStock();
							int itemNum = beverages[i].itemNum;
							String name = beverages[i].getName();

							if (inputItemNum == itemNum) {
								if (stock == 0) {
									System.out.println();
									System.out.println(dividingLine);
									System.out.printf("재고가 없습니다!%n");
								} else if (price > inputMoney) {
									System.out.println();
									System.out.println(dividingLine);
									System.out.println("투입 금액이 부족합니다.");
								} else {
									beverages[i].setStock(stock - 1);
									; // 재고 감소
									inputMoney -= price; // 투입금액 감소
									System.out.println();
									System.out.println(dividingLine);
									System.out.printf("%s(이)가 나왔습니다.%n",name);
									System.out.println(dividingLine);
									
									inputMoneyStr = df.format(inputMoney);
									System.out.printf("투입 금액 : %s원%n", inputMoneyStr);

									// 여기서 투입금액이 0원이 되면 걍 자동종료 시키기
									if (inputMoney == 0) {
										System.out.println();
										break EndCase1; // case1(현금)의 끝에서 break INIT;을 만나 초기화면으로 돌아감
									}

									// 거스름돈 돌려받기
									ProductList: // 이 while문이 종료되면 다시 제품 목록으로 돌아감
									while (true) {
										System.out.println("더 구입하시겠습니까?");
										System.out.println("1. 네");
										System.out.println("2. 아니요");
										System.out.print(">> ");
										int buyPlus = sc.nextInt();
										System.out.println();

										switch (buyPlus) {
										case 1: // 더 살 거임
											break ProductList; // 다시 제품 목록으로 돌아가서 추가구입 상품 선택함
										case 2: // 더 살 거 없음
											inputMoneyStr = df.format(inputMoney);
											System.out.println(dividingLine);
											System.out.printf("거스름돈 %s원을 반환합니다.%n", inputMoneyStr);
											vmCashProfit -= inputMoney;
											inputMoney = 0;
											break INIT; // 초기화면으로 돌아감
										default:
											System.out.println(dividingLine);
											System.out.println("잘못 입력하셨습니다!!");
											System.out.println(dividingLine);
											break;
										}
									}
								}
							}
						}
					}
				}

				break INIT; // 초기화면으로 돌아가기

			case 2: // 카드 결제

				int paymentAmt = 0; // 카드 결제 예정 금액
				String paymentAmtStr = df.format(paymentAmt); // #,###꼴로 바꿔주는 문자열
				inputItemNum = 0; // 구매 상품 품번
				k = 0; // 품번 부여할 때 쓸 거
				
				String[] soldBeverage = new String[30]; //카드 결제시 선택한 음료들 담을 배열
				int cnt = 0; //선택한 음료 카운트 할 때 쓸 거

				EndCase2: while (true) {

					k = 0; // 품번 초기화

					// 제품명, 가격 목록 출력
					System.out.println(dividingLine);
					for (int i = 0; i < beverages.length; i++) {

						String name = beverages[i].getName();
						int stock = beverages[i].getStock();
						int price = beverages[i].getPrice();
						String priceStr = df.format(price); // 금액을 #,###꼴로 바꿔주는 문자열 만들기

						if (name != null) {

							// 가격 문자열 초기화
							priceStr = df.format(price);

							// 진열 순서대로 품번 부여해서 출력
							k++;
							int itemNum = beverages[i].itemNum = k;
							System.out.printf("[%d] %s%n%9s원", itemNum, name, priceStr);
							if (stock == 0) { // 재고가 없다면 끝에 X 출력
								System.out.print(" [X]");
							}
							System.out.println();
							System.out.println();
						}
					} // 목록 출력 끝

					// 품번 범위를 위해 품번의 최댓값 구하기
					int max = beverages[0].itemNum;
					for (int i = 0; i < beverages.length; i++) {
						if (beverages[i].itemNum > max) {
							max = beverages[i].itemNum;
						}
					}
					
					System.out.println(dividingLine);
					System.out.println("구매할 음료 품번을 입력하세요.");
					System.out.print(" >> ");
					inputItemNum = sc.nextInt();
					System.out.println();

					if (inputItemNum > max || inputItemNum == 0) {
						System.out.println();
						System.out.println(dividingLine);
						System.out.println("잘못 입력하셨습니다.");
						continue;
					} else {
						// 입력한 품번과 같은 품번을 가진 제품 찾기
						for (int i = 0; i < beverages.length; i++) {

							int price = beverages[i].getPrice();
							int stock = beverages[i].getStock();
							int itemNum = beverages[i].itemNum;
							String name = beverages[i].getName();

							if (inputItemNum == itemNum) {
								if (stock == 0) {
									System.out.println();
									System.out.println(dividingLine);
									System.out.println("재고가 없습니다!");
								} else {
									soldBeverage[cnt] = name; // 선택한 음료를 배열에 담기
									cnt++;
									beverages[i].setStock(stock - 1); // 재고 감소
									paymentAmt += price; // 결제 예정 금액 증가

									ProductList2: // 이 while문이 종료되면 다시 제품 목록으로 돌아감
									while (true) {
										System.out.println("더 구입하시겠습니까?");
										System.out.println("1. 네");
										System.out.println("2. 아니요");
										System.out.printf(">> ");
										int buyPlus = sc.nextInt();
										System.out.println();

										switch (buyPlus) {
										case 1: // 더 살 거임
											break ProductList2; // 다시 제품 목록으로 돌아가서 추가구입 상품 선택함
										case 2: // 더 살 거 없음
											System.out.println(dividingLine);
											paymentAmtStr = df.format(paymentAmt);
											System.out.printf("%s원 결제 완료되었습니다.", paymentAmtStr);
											vmCardProfit += paymentAmt;
											paymentAmt = 0;
											
											for (int j = 0; j < soldBeverage.length; j++) {
												
												if (soldBeverage[j] != null) {
													String soldBeverageName = soldBeverage[j];
													System.out.println();
													System.out.println(dividingLine);
													System.out.printf("%s(이)가 나왔습니다.",soldBeverageName);
												}
											}
											System.out.println();
											System.out.println(dividingLine);
											System.out.println("카드를 제거해 주세요.");
											System.out.println(dividingLine);
											System.out.println();
											cnt = 0; //카운트 초기화
											break EndCase2; // 초기화면으로 돌아감 INIT에서 수정
										default:
											System.out.println();
											System.out.println(dividingLine);
											System.out.println("잘못 입력하셨습니다!!");
											System.out.println(dividingLine);
											break;
										}
									}
								}
							}
						}
					}
				} // EndCase2 while문 끝임

				break INIT; // 초기화면으로 돌아가기

			default:
				System.out.println(dividingLine);
				System.out.println("잘못 입력하셨습니다!!");
				break;
			}
		}

	}

	/**
	 * 관리자 메뉴
	 */
	public void stockManage() {
		System.out.printf("비밀 번호를 입력해 주세요. >> ");
		String inputPW = sc.next();
		System.out.println();

		if (!(inputPW.equals(PW))) {
			System.out.println(dividingLine);
			System.out.println("틀렸습니다!!");
		} else {
			INIT: while (true) {
				System.out.println(dividingLine);
				System.out.println("1. 재고 확인 및 수정");
				System.out.println("2. 수익 확인");
				System.out.println("0. 처음으로 돌아가기");
				System.out.print(">> ");
				int answer = sc.nextInt();
				System.out.println();

				Manage: while (true) {
					switch (answer) {
					case 1:
						// 재고 확인 및 관리
						System.out.println(dividingLine);
						int k = 0;
						for (int i = 0; i < beverages.length; i++) {

							String name = beverages[i].getName();
							int price = beverages[i].getPrice();
							int stock = beverages[i].getStock();

							String priceStr = df.format(price); // 금액을 #,###꼴로 바꿔주는 문자열 만들기

							if (name != null) { // 30칸 중에 비어있는 칸은 출력 X

								// 진열 순서대로 품번 부여해서 출력
								k++;
								int itemNum = beverages[i].itemNum = k;
								System.out.printf("[%d] %s%n%9s원", itemNum, name, priceStr);
								System.out.printf("%n%6d개", stock);
								if (stock == 0) {
									System.out.print(" [X]"); // 재고 0이면 X출력
								}
								System.out.println();
								System.out.println();
							}
						} // 목록 출력 끝

						EXIT: while (true) {
							System.out.println(dividingLine);
							System.out.println("1. 제품 추가");
							System.out.println("2. 재고 및 가격 변경");
							System.out.println("0. 뒤로");
							System.out.print(">> ");
							int select = sc.nextInt();
							System.out.println();

							Manage2: while (true) {
								switch (select) {
								case 1: // 제품 추가
									
									for (int i = 0; i < 25; i++) {
										
										System.out.println(dividingLine);
										System.out.print("추가할 상품명 >> ");
										String name = sc.next();
										System.out.print("가격 >> ");
										int price = sc.nextInt();
										System.out.print("수량 >> ");
										int stock = sc.nextInt();
										System.out.println();
										
										beverages[i].setName(name);
										beverages[i].setPrice(price);
										beverages[i].setStock(stock);
										
										PLUS:
										while (true) {
											System.out.println(dividingLine);
											System.out.println("더 추가하시겠습니까 ? ");
											System.out.println("1. 네");
											System.out.println("2. 아니요");
											System.out.print(">> ");
											int plus = sc.nextInt();
											System.out.println();
											if (plus == 1) break PLUS;
											if (plus == 2) break Manage2;
											if (plus > 2) {
												System.out.println(dividingLine);
												System.out.println("잘못 입력하셨습니다!!");
											}
										}
									}
									break Manage2;
									
								case 2: // 재고 및 가격 변경
									
									// 품목
									k = 0;
									System.out.println(dividingLine);
									for (int i = 0; i < beverages.length; i++) {

										String name = beverages[i].getName();
										int price = beverages[i].getPrice();
										int stock = beverages[i].getStock();

										String priceStr = df.format(price); // 금액을 #,###꼴로 바꿔주는 문자열 만들기

										if (name != null) { // 30칸 중에 비어있는 칸은 출력 X

											// 진열 순서대로 품번 부여해서 출력
											k++;
											int itemNum = beverages[i].itemNum = k;
											System.out.printf("[%d] %s%n%9s원", itemNum, name, priceStr);
											System.out.printf("%n%6d개", stock);
											if (stock == 0) {
												System.out.print(" [X]"); // 재고 0이면 X출력
											}
											System.out.println();
											System.out.println();
										}
									} // 목록 출력 끝
									
									// 품번 범위를 위해 품번의 최댓값 구하기
									int max = beverages[0].itemNum;
									for (int i = 0; i < beverages.length; i++) {
										if (beverages[i].itemNum > max) {
											max = beverages[i].itemNum;
										}
									}

									System.out.println("수정할 상품 품번을 입력하세요.");
									System.out.print(" >> ");
									int inputItemNum = sc.nextInt();
									System.out.println();
									
									// 입력한 품번과 같은 품번을 가진 제품 찾기
									for (int i = 0; i < beverages.length; i++) {
										
										String name = beverages[i].getName();
										int price = beverages[i].getPrice();
										int stock = beverages[i].getStock();
										int itemNum = beverages[i].itemNum;
										
										if (inputItemNum == itemNum) {
											System.out.println(dividingLine);
											System.out.printf("%s의 가격을 얼마로 변경하시겠습니까 ? ", name);
											price = sc.nextInt();
											beverages[i].setPrice(price);
											
											System.out.printf("%s의 수량을 몇 개로 변경하시겠습니까 ? ", name);
											stock = sc.nextInt();
											beverages[i].setStock(stock);
											System.out.println();
											System.out.println(dividingLine);
											System.out.println("변경이 완료되었습니다.");
										}
									}


									break Manage2;
								case 0: // 뒤로
									break EXIT; // 관리자 메뉴 선택창으로 돌아가는 듯?
								default:
									System.out.println(dividingLine);
									System.out.println("잘못 입력하셨습니다!!");
									break Manage2;
								}
							}
						}

						break Manage; // case1 재고확인 및 관리를 끝내고 관리자 화면으로!
					case 2:
						// 수익 확인
						int vmProfit = vmCashProfit + vmCardProfit;
						String vmCashProfitStr = df.format(vmCashProfit);
						String vmCardProfitStr = df.format(vmCardProfit);
						String vmProfitStr = df.format(vmProfit);
						
						System.out.println(dividingLine);
						System.out.printf("현금 : %s원%n", vmCashProfitStr);
						System.out.printf("카드 : %s원%n", vmCardProfitStr);
						System.out.println("--------------");
						System.out.printf("총 : %s원%n", vmProfitStr);
						System.out.println();
						break Manage;
					case 0:
						break INIT;

					default:
						System.out.println(dividingLine);
						System.out.println("잘못 입력하셨습니다!!");
						break Manage;
					} // switch문 끝
				} // Manage while문 끝
			} // INIT while문 끝
		} // else문 끝

	}

	/**
	 * 종료
	 */
	public void printEnd() {
		System.out.println(dividingLine);
	}

	/**
	 * 예외 처리
	 */
	public void printException() {
		System.out.println(dividingLine);
		System.out.println("잘못 입력하셨습니다!!");
	}

}
