package banking5;

import java.io.Serializable;

/*
최상위 클래스인 Account 를 추상클래스로 정의한다.
즉 Account를 통해서는 인스턴스 생성이 불가능하고 상속의 목적으로만 사용하게 한다. 
*/
// 계좌정보를 표현 클래스로 NormalAccount, HighCreditAccount의 부모클래스
public abstract class Account implements Serializable {
	
	// 멤버변수(고객의 계좌정보 - 계좌번호(String형), 이름(String형), 잔액(int형))
	private String accountID;
	private String customName;
	private int accMoney;
	
	// 생성자
	public Account(String id, String name, int money) {
		this.accountID = id;
		this.customName = name;
		this.accMoney = money;
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("---------------");
		System.out.println("계좌번호 : "+ accountID);
		System.out.println("고객이름 : "+ customName);
		System.out.println("잔고 : "+ accMoney);
	}

	// getter/setter 정의
	public String getAccountID() {
		return accountID;
	}
	
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public int getaccMoney() {
		return accMoney;
	}

	public void setaccMoney(int accMoney) {
		this.accMoney = accMoney;
	}
	
	// 입금처리
	public boolean plusAccMoney(int money) {
		accMoney += money;
		return true;
	}

	// 출금처리
	public boolean minusAccMoney(int money) {
		
		if(accMoney < money) {
			// 잔고보다 많은 금액을 출금요청할 경우
			System.out.println(Color.RED +"잔고가 부족합니다."+ Color.EXIT);
			System.out.println("금액전체를 출금할까요?(YES or NO 기입) : ");
			AccountManager.scan.nextLine();
			String yesORno = AccountManager.scan.nextLine();
			if(yesORno.equalsIgnoreCase("YES")) {
				// 금액전체 출금처리
				accMoney = 0;
			}
			else {
				System.out.println(Color.RED +"출금요청이 취소되었습니다."+ Color.EXIT);
				return false;
			}
		}
		else {
			// 잔고보다 출금액이 작거나 같은 경우
			accMoney -= money;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		// 멤버변수의 hash값을 얻어온다.
		int accCode = this.accountID.hashCode();
		return accCode; 
	}
	
	@Override
	public boolean equals(Object obj) {
		// 매개변수로 전달된 객체를 다운캐스팅한다.
		Account ac = (Account) obj;
		if(ac.accountID.equals(this.accountID)) {
			// accountID를 비교해 동일한 객체가 발견되면 true를 반환(set에 저장되지않는다.)
			return true;
		}
		else {
			// 동일한 객체가 발견되지 않으면 false를 반환(set에 저장된다.)
			return false;
		}
	}
	
}
