package banking5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

// 컨트롤 클래스로 프로그램의 전반적인 기능을 구현
public class AccountManager {

	public static Scanner scan = new Scanner(System.in);
	
	// 인스턴스배열 기반의 프로그램을 HashSet<E>컬렉션으로 변경
	HashSet<Account> accountArr = new HashSet<Account>();
	
	// 메뉴 출력
	public void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.프로그램종료");
		System.out.print(Color.RED +"선택:"+ Color.EXIT);
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		
		// 계좌개설 선택시 계좌선택메뉴 출력
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print(Color.RED +"선택:"+ Color.EXIT);
		
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
		
		// 계좌번호가 동일한 경우 중복된 계좌로 판단하여 다음과 같이 처리한다.
		boolean isOverlap = accountArr.add(addAcc);
		if(isOverlap==false) {	// 중복된 계좌 발견
			System.out.println(Color.RED +"중복된 계좌가 발견되었습니다."+ Color.EXIT);
			System.out.println("덮어쓸까요?(YES or NO 기입) : ");
			String yesORno = scan.nextLine();
			if(yesORno.equalsIgnoreCase("YES")) {	// YES 선택시
				// 기존정보를 삭제하고
				accountArr.remove(addAcc);
				// 새로 입력된 정보로 덮어쓰기
				accountArr.add(addAcc);
				System.out.println(Color.RED +"새로운 정보로 업데이트 되었습니다.\n"+ Color.EXIT);
			}
			else {	// NO 선택시
				// 기존의 정보를 유지한다. 즉 새로운 정보는 무시된다.
				System.out.println(Color.RED +"기존 계좌로 유지하겠습니다."+ Color.EXIT);
			}
		}
		else {	// 중복된 계좌가 없으면 계좌개설
			System.out.println("계좌개설이 완료되었습니다.\n");
		}
		
	}
	
	// 입금
	public void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요");

		System.out.print("계좌번호 : ");
		String accID = scan.next();
		// 입금액 입력시 문자를 입력할 수 없다.
		try {
			System.out.print("입금액 : ");
			int money = scan.nextInt();
		
			// 음수를 입금할 수 없다.
			if(money<=0) {
				System.out.println(Color.RED +"음수는 입금할 수 없습니다."+ Color.EXIT);
				return;
			}
			// 입금액은 500원단위로 가능하다. Ex) 1000, 1500원 입금가능, 1600원 입금불가.
			if(money%500 != 0) {
				System.out.println(Color.RED +"입금액은 500원단위로 가능합니다."+ Color.EXIT);
				return;
			}
			
			// 이터레이터 객체 생성
			Iterator<Account> itr = accountArr.iterator();
			
			// 입금결과 확인용
			boolean isDmoney = false;
			
			// 반환할 객체가 있는지 확인
			while(itr.hasNext()) {
				/*
				이터레이터는 next() 메서드를 통해 객체를 출력한다.
				한번 호출할때마다 다음 객체로 이동하게 되므로 루프내에서 한번 이상 호출하면
				예외가 발생할 수 있어 반드시 아래와 같이 사용해야 한다.
				*/
				Account ac = itr.next();
				
				// 입력계좌와 기존계좌 비교
				if(accID.compareTo(ac.getAccountID())==0) {
					ac.plusAccMoney(money);
					isDmoney = true;
					
				}
			}
			if(isDmoney==false) {
				System.out.println(Color.RED +"입금이 취소되었습니다.\n"+ Color.EXIT);
			}
			else {
				System.out.println(Color.RED +"입금이 완료되었습니다.\n"+ Color.EXIT);
			}
			
		}
		catch(InputMismatchException e) {
			System.out.println(Color.RED +"입금액 입력시 숫자만 입력 가능합니다."+ Color.EXIT);
			scan.nextLine();
		}
	}
	
	// 출금
	public void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		
		System.out.print("계좌번호 : ");
		String accID = scan.next();
		// 출금액 입력시 문자를 입력할 수 없다.
		try {
			System.out.print("출금액 : ");
			int money = scan.nextInt();
			
			// 음수를 출금할 수 없다.
			if(money<=0) {
				System.out.println(Color.RED +"음수는 출금할 수 없습니다.\n"+ Color.EXIT);
				return;
			}
			// 출금은 1000원 단위로만 출금이 가능하다. Ex)2000원 출금가능, 1100원을 출금불가.
			if(money%1000 != 0) {
				System.out.println(Color.RED +"출금액은 1000원단위로 가능합니다.\n"+ Color.EXIT);
				return;
			}
			
			// 이터레이터 객체 생성
			Iterator<Account> itr = accountArr.iterator();
			
			// 출금결과 확인용
			boolean isWmoney = false;
			
			// 반환할 객체가 있는지 확인
			while(itr.hasNext()) {
				/*
				이터레이터는 next() 메서드를 통해 객체를 출력한다.
				한번 호출할때마다 다음 객체로 이동하게 되므로 루프내에서 한번 이상 호출하면
				예외가 발생할 수 있어 반드시 아래와 같이 사용해야 한다.
				*/
				Account ac = itr.next();
				
				// 입력계좌와 기존계좌 비교
				if(accID.compareTo(ac.getAccountID())==0) {
					ac.minusAccMoney(money);
					isWmoney = true;
					
				}
			}
			if(isWmoney==false) {
				System.out.println(Color.RED +"출금이 취소되었습니다.\n"+ Color.EXIT);
			}
			else {
				System.out.println(Color.RED +"출금이 완료되었습니다.\n"+ Color.EXIT);
			}
			
		}
		catch(InputMismatchException e) {
			System.out.println(Color.RED +"출금액 입력시 숫자만 입력 가능합니다.\n"+ Color.EXIT);
			scan.nextLine();
		}
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		// 이터레이터 객체 생성
		Iterator<Account> itr = accountArr.iterator();
		
		// 반환할 객체가 있는지 확인
		while(itr.hasNext()) {
			/*
			이터레이터는 next() 메서드를 통해 객체를 출력한다.
			한번 호출할때마다 다음 객체로 이동하게 되므로 루프내에서 한번 이상 호출하면
			예외가 발생할 수 있어 반드시 아래와 같이 사용해야 한다.
			*/
			Account ac = itr.next();
			
			ac.showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
	}
	
	// 계좌정보삭제
	public void deleteAccInfo() {
		System.out.print("삭제할 계좌번호 : ");
		String delAccID = scan.next();
		
		// 이터레이터 객체 생성
		Iterator<Account> itr = accountArr.iterator();
		
		// 계좌정보 삭제 확인용
		boolean isaccID = false;
		
		// 반환할 객체가 있는지 확인
		while(itr.hasNext()) {
			Account ac = itr.next();
			if(delAccID.compareTo(ac.getAccountID())==0) {
				accountArr.remove(ac);
				isaccID = true;
				break;
			}
		}
		
		if(isaccID==false) {
			System.out.println(Color.RED +"존재하지 않는 계좌정보입니다.\n"+ Color.EXIT);
		}
		else {
			System.out.println(Color.RED +"계좌정보가 삭제되었습니다.\n"+ Color.EXIT);
		}
		
	}
	
	// 프로그램 종료시 계좌의 정보를 파일로 저장(직렬화)한다.
	public void saveAccInfo() {
		try {
			//계좌 정보를 파일에 저장하기 위한 출력스트림 생성
			ObjectOutputStream out = 
					new ObjectOutputStream(
							new FileOutputStream("src/banking5/AccountInfo.obj")
					);
			
			// 이터레이터 객체 생성
			Iterator<Account> itr = accountArr.iterator();
			
			while(itr.hasNext()) {
				// 파일에 저장한다. -> 직렬화
				out.writeObject(itr.next());
			}
			out.close();
			System.out.println("AccountInfo.obj 저장 완료");
		}
		catch(Exception e) {
			System.out.println("AccountInfo.obj 저장 중 예외발생");
		}
	}

	// 프로그램 시작시 저장된 파일을 통해 전체정보를 조회했을때 기존에 입력된 정보를 출력한다.
	public void readAccInfo() {
		try {
			// 파일을 복원(역직렬화)하기 위해 스트림 생성
			ObjectInputStream in = 
					new ObjectInputStream(
							new FileInputStream("src/banking5/AccountInfo.obj")
					);
			
			// 파일에 계좌정보가 몇개 저장되었는지 확인할 수 없으므로 무한루프로 구성
			while(true) {
				// 직렬화될때는 Object기반으로 저장되므로 역직렬화할때는 반드시 다운캐스팅을 한다.
				Account ac = (Account) in.readObject();
				
				// HashSet<E>컬렉션에 추가
				accountArr.add(ac);
			}
		}
		catch (Exception e) { }
	}
}
