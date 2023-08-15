package banking2;

import java.util.Scanner;

// 컨트롤 클래스로 프로그램의 전반적인 기능을 구현
public class AccountManager {
	
    public static final String RED  = "\u001B[31m" ;
    public static final String EXIT = "\u001B[0m" ;

	public static Scanner scan = new Scanner(System.in);
	// 여러 계좌정보를 저장하기 위해 인스턴스(객채)배열 생성 - 계좌정보 최대 50개(차후 변경됨)
	static Account[] accountArr = new Account[50];
	// 개설된 계좌 카운트 변수
	static int accCount = 0;
	
	// 메뉴 출력
	public void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print(RED +"선택:"+ EXIT);
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		
		// 계좌개설 선택시 계좌선택메뉴 출력
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print(RED +"선택:"+ EXIT);
		
		int keyInput = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호 : ");
		String accID = scan.next();
		System.out.print("고객이름 : ");
		String cusName = scan.next();
		System.out.print("잔고 : ");
		int accMonye = scan.nextInt();

		// 부모클래스 Account로 하위클래스의 인스턴스를 저장하기위해 객체생성
		Account addAcc = null;
		
		if(keyInput==1) {	// 보통계좌 선택
			System.out.print("기본이자%(정수형태로입력) : ");
			int interest = scan.nextInt();
			scan.nextLine();
			
			addAcc = new NormalAccount(accID, cusName, accMonye, interest);
			
		}
		else if(keyInput==2) {	// 신용신뢰계좌 선택
			System.out.print("기본이자%(정수형태로입력) : ");
			int interest = scan.nextInt();
			scan.nextLine();
			System.out.print("신용등급(A,B,C등급) : ");
			String credit = scan.nextLine();
			
			addAcc = new HighCreditAccount(accID, cusName, accMonye, interest, credit);
		}

		// 객체배열에 저장후 카운트 변수 1 증가
		accountArr[accCount++] = addAcc;
		
		System.out.println("계좌개설이 완료되었습니다.");
		System.out.println();
		
	}
	
	// 입금
	public void depositMoney() {
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
	public void withdrawMoney() {
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
	public void showAccInfo() {
		for(int i=0; i<accCount; i++) {
			accountArr[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
	
}
