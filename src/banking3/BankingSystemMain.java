package banking3;

import java.util.InputMismatchException;

import banking5.MenuSelectException;

// main 메서드를 포함한 클래스. 프로그램은 여기서 실행
public class BankingSystemMain {

	public static void main(String[] args) {
		
		AccountManager accMgr = new AccountManager();
		
		boolean run = true;
		
		while(run) {
			accMgr.showMenu();
			
			try {
				int keyInput = accMgr.scan.nextInt();
				accMgr.scan.nextLine();
				
				if(keyInput == ICustomDefine.MAKE) {
					System.out.println("***신규계좌개설***");
					accMgr.makeAccount();
				}
				else if(keyInput == ICustomDefine.DEPOSIT) {
					System.out.println("***입   금***");
					accMgr.depositMoney();
				}
				else if(keyInput == ICustomDefine.WITHDRAW) {
					System.out.println("***출   금***");
					accMgr.withdrawMoney();
				}
				else if(keyInput == ICustomDefine.INQUIRE) {
					System.out.println("***계좌정보출력***");
					accMgr.showAccInfo();
				}
				else if(keyInput == ICustomDefine.EXIT) {
					run = false;
				}
				else {
					throw new MenuSelectException();
				}
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.println(Color.RED +"[예외발생] 메뉴선택은 숫자만 가능합니다."+ Color.EXIT);
				accMgr.scan.nextLine();
			}
			
			
		}
		System.out.println("프로그램 종료");

	}

}
