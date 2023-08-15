package banking2;

// 계좌정보를 표현 클래스로 NormalAccount, HighCreditAccount의 부모클래스
public class Account {
	
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
		accMoney -= money;
		return true;
	}
	
}
