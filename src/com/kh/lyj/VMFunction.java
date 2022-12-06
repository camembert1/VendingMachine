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
	 * ��ü �迭 ���� �� �ʱ�ȭ + �⺻ ���� �߰�
	 */
	public VMFunction() {
		beverages = new Beverage[30];
		for (int i = 0; i < beverages.length; i++) {
			beverages[i] = new Beverage();
		}

		beverages[25].setName("��ټ�");
		beverages[25].setPrice(1100);
		beverages[25].setStock(3);

		beverages[26].setName("��ī������Ʈ(PET)");
		beverages[26].setPrice(2700);
		beverages[26].setStock(10);

		beverages[27].setName("����ݶ�");
		beverages[27].setPrice(1700);
		beverages[27].setStock(0);

		beverages[28].setName("ĥ�����̴�");
		beverages[28].setPrice(1800);
		beverages[28].setStock(10);

		beverages[29].setName("������Ŀ��");
		beverages[29].setPrice(1000);
		beverages[29].setStock(10);
	}

	/**
	 * ���� or ���� ����
	 * 
	 * @return choice
	 */
	public int printMenu() {

		System.out.println(dividingLine);
		System.out.println("1. ���� ����");
		System.out.println("2. ������ �޴�");
		System.out.println("0. ����");
		System.out.print(">> ");
		int choice = sc.nextInt();
		System.out.println();
		return choice;
	}

	/**
	 * ���� ����
	 */
	public void buyBeverage() {

		// ���� or ī��

		// �� while���� ������ buyBeverage�޼��尡 ������
		// �ʱ� ȭ������ ���ư�
		INIT: while (true) {

			// ������� �Է¹ޱ�
			System.out.println(dividingLine);
			System.out.println("���� ���� ����");
			System.out.println("1. ����");
			System.out.println("2. ī��");
			System.out.print(">> ");
			int paymentMethodNum = sc.nextInt();
			System.out.println();

			switch (paymentMethodNum) {
			case 1: // ���� ����
				int inputMoney = 0; // ���Աݾ�
				String inputMoneyStr = df.format(inputMoney); // ���� �ݾ��� #,###�÷� �ٲ��ִ� ���ڿ�
				int inputItemNum = 0; // ���� ��ǰ ǰ��
				int k = 0; // ǰ�� �ο��� �� �� ��

				System.out.println(dividingLine);

				// ��ǰ��, ���� ��� ���
				for (int i = 0; i < beverages.length; i++) {

					String name = beverages[i].getName();
					int price = beverages[i].getPrice();
					int stock = beverages[i].getStock();

					String priceStr = df.format(price); // �ݾ��� #,###�÷� �ٲ��ִ� ���ڿ� �����

					if (name != null) { // 30ĭ �߿� ����ִ� ĭ�� ��� X

						// ���� ������� ǰ�� �ο��ؼ� ���
						k++;
						int itemNum = beverages[i].itemNum = k;
						System.out.printf("[%d] %s%n%9s��", itemNum, name, priceStr);
						if (stock == 0) {
							System.out.print(" [X]"); // ��� 0�̸� X���
						}
						System.out.println();
						System.out.println();
					}
				} // ��� ��� ��

				System.out.print("���� <<�ݾ�>>�� �Է��� �ּ��� >> ");
				inputMoney = sc.nextInt();
				vmCashProfit += inputMoney;
				System.out.println();

				// �� while���� ������
				// �������� ���� switch���� case1������ �̵���
				// break INIT�� ���� �ʱ�ȭ������ ���ư�
				EndCase1: while (true) {
					k = 0; // ǰ�� �ʱ�ȭ

					// ��ǰ��, ���� ��� ���
					System.out.println(dividingLine);
					for (int i = 0; i < beverages.length; i++) {

						String name = beverages[i].getName();
						int stock = beverages[i].getStock();
						int price = beverages[i].getPrice();
						String priceStr = df.format(price); // �ݾ��� #,###�÷� �ٲ��ִ� ���ڿ� �����

						if (name != null) {

							// ���� ���ڿ� �ʱ�ȭ
							priceStr = df.format(price);

							// ���� ������� ǰ�� �ο��ؼ� ���
							k++;
							int itemNum = beverages[i].itemNum = k;
							System.out.printf("[%d] %s%n%9s��", itemNum, name, priceStr);
							if (stock == 0) { // ��� ���ٸ� ���� X ���
								System.out.print(" [X]");
							}
							System.out.println();
							System.out.println();
						}
					} // ��� ��� ��

					inputMoneyStr = df.format(inputMoney);
					System.out.println(dividingLine);
					System.out.printf("���� �ݾ� : %s��%n", inputMoneyStr);

					// ǰ�� ������ ���� ǰ���� �ִ� ���ϱ�
					int max = beverages[0].itemNum;
					for (int i = 0; i < beverages.length; i++) {
						if (beverages[i].itemNum > max) {
							max = beverages[i].itemNum;
						}
					}

					System.out.println("������ ���� ǰ���� �Է��ϼ���.");
					System.out.println("(���� �ݾ� ��ȯ�� ���� ��� 0�� �Է��ϼ���.)");
					System.out.print(" >> ");
					inputItemNum = sc.nextInt();

					if (inputItemNum == 0) {
						System.out.println();
						System.out.println(dividingLine);
						System.out.printf("�����Ͻ� %s���� ��ȯ�մϴ�.%n", inputMoneyStr);
						vmCashProfit -= inputMoney;
						inputMoney = 0;
						break EndCase1; // case1(����)�� ������ break INIT;�� ���� �ʱ�ȭ������ ���ư�
					} else if (inputItemNum > max) {
						System.out.println();
						System.out.println(dividingLine);
						System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
						continue;
					} else {
						// �Է��� ǰ���� ���� ǰ���� ���� ��ǰ ã��
						for (int i = 0; i < beverages.length; i++) {

							int price = beverages[i].getPrice();
							int stock = beverages[i].getStock();
							int itemNum = beverages[i].itemNum;
							String name = beverages[i].getName();

							if (inputItemNum == itemNum) {
								if (stock == 0) {
									System.out.println();
									System.out.println(dividingLine);
									System.out.printf("��� �����ϴ�!%n");
								} else if (price > inputMoney) {
									System.out.println();
									System.out.println(dividingLine);
									System.out.println("���� �ݾ��� �����մϴ�.");
								} else {
									beverages[i].setStock(stock - 1);
									; // ��� ����
									inputMoney -= price; // ���Աݾ� ����
									System.out.println();
									System.out.println(dividingLine);
									System.out.printf("%s(��)�� ���Խ��ϴ�.%n",name);
									System.out.println(dividingLine);
									
									inputMoneyStr = df.format(inputMoney);
									System.out.printf("���� �ݾ� : %s��%n", inputMoneyStr);

									// ���⼭ ���Աݾ��� 0���� �Ǹ� �� �ڵ����� ��Ű��
									if (inputMoney == 0) {
										System.out.println();
										break EndCase1; // case1(����)�� ������ break INIT;�� ���� �ʱ�ȭ������ ���ư�
									}

									// �Ž����� �����ޱ�
									ProductList: // �� while���� ����Ǹ� �ٽ� ��ǰ ������� ���ư�
									while (true) {
										System.out.println("�� �����Ͻðڽ��ϱ�?");
										System.out.println("1. ��");
										System.out.println("2. �ƴϿ�");
										System.out.print(">> ");
										int buyPlus = sc.nextInt();
										System.out.println();

										switch (buyPlus) {
										case 1: // �� �� ����
											break ProductList; // �ٽ� ��ǰ ������� ���ư��� �߰����� ��ǰ ������
										case 2: // �� �� �� ����
											inputMoneyStr = df.format(inputMoney);
											System.out.println(dividingLine);
											System.out.printf("�Ž����� %s���� ��ȯ�մϴ�.%n", inputMoneyStr);
											vmCashProfit -= inputMoney;
											inputMoney = 0;
											break INIT; // �ʱ�ȭ������ ���ư�
										default:
											System.out.println(dividingLine);
											System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
											System.out.println(dividingLine);
											break;
										}
									}
								}
							}
						}
					}
				}

				break INIT; // �ʱ�ȭ������ ���ư���

			case 2: // ī�� ����

				int paymentAmt = 0; // ī�� ���� ���� �ݾ�
				String paymentAmtStr = df.format(paymentAmt); // #,###�÷� �ٲ��ִ� ���ڿ�
				inputItemNum = 0; // ���� ��ǰ ǰ��
				k = 0; // ǰ�� �ο��� �� �� ��
				
				String[] soldBeverage = new String[30]; //ī�� ������ ������ ����� ���� �迭
				int cnt = 0; //������ ���� ī��Ʈ �� �� �� ��

				EndCase2: while (true) {

					k = 0; // ǰ�� �ʱ�ȭ

					// ��ǰ��, ���� ��� ���
					System.out.println(dividingLine);
					for (int i = 0; i < beverages.length; i++) {

						String name = beverages[i].getName();
						int stock = beverages[i].getStock();
						int price = beverages[i].getPrice();
						String priceStr = df.format(price); // �ݾ��� #,###�÷� �ٲ��ִ� ���ڿ� �����

						if (name != null) {

							// ���� ���ڿ� �ʱ�ȭ
							priceStr = df.format(price);

							// ���� ������� ǰ�� �ο��ؼ� ���
							k++;
							int itemNum = beverages[i].itemNum = k;
							System.out.printf("[%d] %s%n%9s��", itemNum, name, priceStr);
							if (stock == 0) { // ��� ���ٸ� ���� X ���
								System.out.print(" [X]");
							}
							System.out.println();
							System.out.println();
						}
					} // ��� ��� ��

					// ǰ�� ������ ���� ǰ���� �ִ� ���ϱ�
					int max = beverages[0].itemNum;
					for (int i = 0; i < beverages.length; i++) {
						if (beverages[i].itemNum > max) {
							max = beverages[i].itemNum;
						}
					}
					
					System.out.println(dividingLine);
					System.out.println("������ ���� ǰ���� �Է��ϼ���.");
					System.out.print(" >> ");
					inputItemNum = sc.nextInt();
					System.out.println();

					if (inputItemNum > max || inputItemNum == 0) {
						System.out.println();
						System.out.println(dividingLine);
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						continue;
					} else {
						// �Է��� ǰ���� ���� ǰ���� ���� ��ǰ ã��
						for (int i = 0; i < beverages.length; i++) {

							int price = beverages[i].getPrice();
							int stock = beverages[i].getStock();
							int itemNum = beverages[i].itemNum;
							String name = beverages[i].getName();

							if (inputItemNum == itemNum) {
								if (stock == 0) {
									System.out.println();
									System.out.println(dividingLine);
									System.out.println("��� �����ϴ�!");
								} else {
									soldBeverage[cnt] = name; // ������ ���Ḧ �迭�� ���
									cnt++;
									beverages[i].setStock(stock - 1); // ��� ����
									paymentAmt += price; // ���� ���� �ݾ� ����

									ProductList2: // �� while���� ����Ǹ� �ٽ� ��ǰ ������� ���ư�
									while (true) {
										System.out.println("�� �����Ͻðڽ��ϱ�?");
										System.out.println("1. ��");
										System.out.println("2. �ƴϿ�");
										System.out.printf(">> ");
										int buyPlus = sc.nextInt();
										System.out.println();

										switch (buyPlus) {
										case 1: // �� �� ����
											break ProductList2; // �ٽ� ��ǰ ������� ���ư��� �߰����� ��ǰ ������
										case 2: // �� �� �� ����
											System.out.println(dividingLine);
											paymentAmtStr = df.format(paymentAmt);
											System.out.printf("%s�� ���� �Ϸ�Ǿ����ϴ�.", paymentAmtStr);
											vmCardProfit += paymentAmt;
											paymentAmt = 0;
											
											for (int j = 0; j < soldBeverage.length; j++) {
												
												if (soldBeverage[j] != null) {
													String soldBeverageName = soldBeverage[j];
													System.out.println();
													System.out.println(dividingLine);
													System.out.printf("%s(��)�� ���Խ��ϴ�.",soldBeverageName);
												}
											}
											System.out.println();
											System.out.println(dividingLine);
											System.out.println("ī�带 ������ �ּ���.");
											System.out.println(dividingLine);
											System.out.println();
											cnt = 0; //ī��Ʈ �ʱ�ȭ
											break EndCase2; // �ʱ�ȭ������ ���ư� INIT���� ����
										default:
											System.out.println();
											System.out.println(dividingLine);
											System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
											System.out.println(dividingLine);
											break;
										}
									}
								}
							}
						}
					}
				} // EndCase2 while�� ����

				break INIT; // �ʱ�ȭ������ ���ư���

			default:
				System.out.println(dividingLine);
				System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
				break;
			}
		}

	}

	/**
	 * ������ �޴�
	 */
	public void stockManage() {
		System.out.printf("��� ��ȣ�� �Է��� �ּ���. >> ");
		String inputPW = sc.next();
		System.out.println();

		if (!(inputPW.equals(PW))) {
			System.out.println(dividingLine);
			System.out.println("Ʋ�Ƚ��ϴ�!!");
		} else {
			INIT: while (true) {
				System.out.println(dividingLine);
				System.out.println("1. ��� Ȯ�� �� ����");
				System.out.println("2. ���� Ȯ��");
				System.out.println("0. ó������ ���ư���");
				System.out.print(">> ");
				int answer = sc.nextInt();
				System.out.println();

				Manage: while (true) {
					switch (answer) {
					case 1:
						// ��� Ȯ�� �� ����
						System.out.println(dividingLine);
						int k = 0;
						for (int i = 0; i < beverages.length; i++) {

							String name = beverages[i].getName();
							int price = beverages[i].getPrice();
							int stock = beverages[i].getStock();

							String priceStr = df.format(price); // �ݾ��� #,###�÷� �ٲ��ִ� ���ڿ� �����

							if (name != null) { // 30ĭ �߿� ����ִ� ĭ�� ��� X

								// ���� ������� ǰ�� �ο��ؼ� ���
								k++;
								int itemNum = beverages[i].itemNum = k;
								System.out.printf("[%d] %s%n%9s��", itemNum, name, priceStr);
								System.out.printf("%n%6d��", stock);
								if (stock == 0) {
									System.out.print(" [X]"); // ��� 0�̸� X���
								}
								System.out.println();
								System.out.println();
							}
						} // ��� ��� ��

						EXIT: while (true) {
							System.out.println(dividingLine);
							System.out.println("1. ��ǰ �߰�");
							System.out.println("2. ��� �� ���� ����");
							System.out.println("0. �ڷ�");
							System.out.print(">> ");
							int select = sc.nextInt();
							System.out.println();

							Manage2: while (true) {
								switch (select) {
								case 1: // ��ǰ �߰�
									
									for (int i = 0; i < 25; i++) {
										
										System.out.println(dividingLine);
										System.out.print("�߰��� ��ǰ�� >> ");
										String name = sc.next();
										System.out.print("���� >> ");
										int price = sc.nextInt();
										System.out.print("���� >> ");
										int stock = sc.nextInt();
										System.out.println();
										
										beverages[i].setName(name);
										beverages[i].setPrice(price);
										beverages[i].setStock(stock);
										
										PLUS:
										while (true) {
											System.out.println(dividingLine);
											System.out.println("�� �߰��Ͻðڽ��ϱ� ? ");
											System.out.println("1. ��");
											System.out.println("2. �ƴϿ�");
											System.out.print(">> ");
											int plus = sc.nextInt();
											System.out.println();
											if (plus == 1) break PLUS;
											if (plus == 2) break Manage2;
											if (plus > 2) {
												System.out.println(dividingLine);
												System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
											}
										}
									}
									break Manage2;
									
								case 2: // ��� �� ���� ����
									
									// ǰ��
									k = 0;
									System.out.println(dividingLine);
									for (int i = 0; i < beverages.length; i++) {

										String name = beverages[i].getName();
										int price = beverages[i].getPrice();
										int stock = beverages[i].getStock();

										String priceStr = df.format(price); // �ݾ��� #,###�÷� �ٲ��ִ� ���ڿ� �����

										if (name != null) { // 30ĭ �߿� ����ִ� ĭ�� ��� X

											// ���� ������� ǰ�� �ο��ؼ� ���
											k++;
											int itemNum = beverages[i].itemNum = k;
											System.out.printf("[%d] %s%n%9s��", itemNum, name, priceStr);
											System.out.printf("%n%6d��", stock);
											if (stock == 0) {
												System.out.print(" [X]"); // ��� 0�̸� X���
											}
											System.out.println();
											System.out.println();
										}
									} // ��� ��� ��
									
									// ǰ�� ������ ���� ǰ���� �ִ� ���ϱ�
									int max = beverages[0].itemNum;
									for (int i = 0; i < beverages.length; i++) {
										if (beverages[i].itemNum > max) {
											max = beverages[i].itemNum;
										}
									}

									System.out.println("������ ��ǰ ǰ���� �Է��ϼ���.");
									System.out.print(" >> ");
									int inputItemNum = sc.nextInt();
									System.out.println();
									
									// �Է��� ǰ���� ���� ǰ���� ���� ��ǰ ã��
									for (int i = 0; i < beverages.length; i++) {
										
										String name = beverages[i].getName();
										int price = beverages[i].getPrice();
										int stock = beverages[i].getStock();
										int itemNum = beverages[i].itemNum;
										
										if (inputItemNum == itemNum) {
											System.out.println(dividingLine);
											System.out.printf("%s�� ������ �󸶷� �����Ͻðڽ��ϱ� ? ", name);
											price = sc.nextInt();
											beverages[i].setPrice(price);
											
											System.out.printf("%s�� ������ �� ���� �����Ͻðڽ��ϱ� ? ", name);
											stock = sc.nextInt();
											beverages[i].setStock(stock);
											System.out.println();
											System.out.println(dividingLine);
											System.out.println("������ �Ϸ�Ǿ����ϴ�.");
										}
									}


									break Manage2;
								case 0: // �ڷ�
									break EXIT; // ������ �޴� ����â���� ���ư��� ��?
								default:
									System.out.println(dividingLine);
									System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
									break Manage2;
								}
							}
						}

						break Manage; // case1 ���Ȯ�� �� ������ ������ ������ ȭ������!
					case 2:
						// ���� Ȯ��
						int vmProfit = vmCashProfit + vmCardProfit;
						String vmCashProfitStr = df.format(vmCashProfit);
						String vmCardProfitStr = df.format(vmCardProfit);
						String vmProfitStr = df.format(vmProfit);
						
						System.out.println(dividingLine);
						System.out.printf("���� : %s��%n", vmCashProfitStr);
						System.out.printf("ī�� : %s��%n", vmCardProfitStr);
						System.out.println("--------------");
						System.out.printf("�� : %s��%n", vmProfitStr);
						System.out.println();
						break Manage;
					case 0:
						break INIT;

					default:
						System.out.println(dividingLine);
						System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
						break Manage;
					} // switch�� ��
				} // Manage while�� ��
			} // INIT while�� ��
		} // else�� ��

	}

	/**
	 * ����
	 */
	public void printEnd() {
		System.out.println(dividingLine);
	}

	/**
	 * ���� ó��
	 */
	public void printException() {
		System.out.println(dividingLine);
		System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
	}

}
