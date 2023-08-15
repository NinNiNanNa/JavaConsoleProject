package banking7.jdbc;

import java.util.Scanner;

public class BankingSystemMain {

	// 키보드 입력
	public static Scanner scan = new Scanner(System.in);
	
	// 메뉴 출력
	public static void showMenu() {
		System.out.println(Color.CYAN_BACK +"------------------------ MENU ------------------------"+ Color.EXIT);
		System.out.println(Color.CYAN_BACK +" 1.계좌개설 | 2.입	금 | 3.출	금 | 4.계좌정보출력 | 5.프로그램종료 "+ Color.EXIT);
		System.out.println(Color.CYAN_BACK +"------------------------------------------------------"+ Color.EXIT);
		System.out.print(Color.CYAN +"메뉴를 선택하세요 >> "+ Color.EXIT);
	}

	public static void main(String[] args) {
		
		boolean run = true;
		
		while(run) {
			showMenu();
			
			int keyInput = scan.nextInt();
			scan.nextLine();
			
			if(keyInput == ICustomDefine.MAKE) {
				System.out.println(Color.GREEN +"****************** 신규계좌개설 ******************"+ Color.EXIT);
				new makeAccountSQL().execute();
				System.out.println(Color.GREEN +"**********************************************"+ Color.EXIT);
			}
			else if(keyInput == ICustomDefine.DEPOSIT) {
				System.out.println(Color.GREEN +"******************** 입  금 ********************"+ Color.EXIT);
				new depositMoneySQL().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
			}
			else if(keyInput == ICustomDefine.WITHDRAW) {
				System.out.println(Color.GREEN +"******************** 출  금 ********************"+ Color.EXIT);
				new withdrawMoneySQL().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
			}
			else if(keyInput == ICustomDefine.INQUIRE) {
				System.out.println(Color.GREEN +"****************** 계좌정보출력 ******************"+ Color.EXIT);
				new showAccInfoSQL().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
			}
			else if(keyInput == ICustomDefine.EXIT) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}

}
