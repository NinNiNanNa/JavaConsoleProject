package banking1;

import java.util.Scanner;

//main 메서드를 포함한 클래스. 프로그램은 여기서 실행
public class BankingSystemMain {
	
    public static final String RED  = "\u001B[31m" ;
    public static final String EXIT = "\u001B[0m" ;

	public static Scanner scan = new Scanner(System.in);
	// 여러 계좌정보를 저장하기 위해 인스턴스(객채)배열 생성 - 계좌정보 최대 50개(차후 변경됨)
	static Account[] accountArr = new Account[50];
	// 개설된 계좌 카운트 변수
	static int accCount = 0;
	
	// 메뉴 출력
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print(RED +"선택:"+ EXIT);
	}
	
	// 계좌개설을 위한 함수
	public static void makeAccount() {
		System.out.print("계좌번호 : ");
		String accID = scan.next();
		System.out.print("고객이름 : ");
		String cusName = scan.next();
		System.out.print("잔고 : ");
		int accMonye = scan.nextInt();
		
		// 신규정보를 통한 객체생성
		Account addAcc = new Account(accID, cusName, accMonye);
		
		// 객체배열에 저장후 카운트 변수 1 증가
		accountArr[accCount++] = addAcc;
		
		System.out.println("계좌개설이 완료되었습니다.");
		System.out.println();
		
	}
	
	// 입금
	public static void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요");

		System.out.print("계좌번호 : ");
		String accID = scan.next();
		System.out.print("입금액 : ");
		int money = scan.nextInt();
		
		for(int i=0; i<accCount; i++) {
			if(accID.compareTo(accountArr[i].getAccountID())==0) {
				accountArr[i].plusAccMoney(money);
			}
		}
		System.out.println("입금이 완료되었습니다.");
		System.out.println();
	}
	
	// 출금
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		
		System.out.print("계좌번호 : ");
		String accID = scan.next();
		System.out.print("출금액 : ");
		int money = scan.nextInt();
		
		for(int i=0; i<accCount; i++) {
			if(accID.compareTo(accountArr[i].getAccountID())==0) {
				accountArr[i].minusAccMoney(money);
			}
		}
		System.out.println("출금이 완료되었습니다.");
		System.out.println();
	}
	
	// 전체계좌정보출력
	public static void showAccInfo() {
		for(int i=0; i<accCount; i++) {
			accountArr[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}

	public static void main(String[] args) {
		
		boolean run = true;
		
		while(run) {
			showMenu();
			
			int keyInput = scan.nextInt();
			scan.nextLine();
			
			if(keyInput == ICustomDefine.MAKE) {
				System.out.println("***신규계좌개설***");
				makeAccount();
			}
			else if(keyInput == ICustomDefine.DEPOSIT) {
				System.out.println("***입   금***");
				depositMoney();
			}
			else if(keyInput == ICustomDefine.WITHDRAW) {
				System.out.println("***출   금***");
				withdrawMoney();
			}
			else if(keyInput == ICustomDefine.INQUIRE) {
				System.out.println("***계좌정보출력***");
				showAccInfo();
			}

			else if(keyInput == ICustomDefine.EXIT) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");

	}

}
