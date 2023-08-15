package banking2;

// main 메서드를 포함한 클래스. 프로그램은 여기서 실행
public class BankingSystemMain {

	public static void main(String[] args) {
		
		AccountManager accMgr = new AccountManager();
		
		boolean run = true;
		
		while(run) {
			accMgr.showMenu();
			
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
		}
		System.out.println("프로그램 종료");

	}

}
