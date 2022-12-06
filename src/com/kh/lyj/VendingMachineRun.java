package com.kh.lyj;

public class VendingMachineRun {
	public static void main (String[] args) {
		
		VMFunction vmFunction = new VMFunction();
		
		ESCAPE:
		while(true) {
			int choice = vmFunction.printMenu();
			switch (choice) {
				case 1:
					vmFunction.buyBeverage();
					break;
				case 2:
					vmFunction.stockManage();
					break;
				case 0:
					vmFunction.printEnd();
					break ESCAPE;
				default:
					vmFunction.printException();
					break;
				}
			}
		
	}
}
