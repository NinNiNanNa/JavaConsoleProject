package banking6;

// 보통예금계좌 > Account의 자식클래스로 최소한의 이자를 지급하는 자율 입출금식 계좌
public class NormalAccount extends Account {

	// 멤버변수 : 기본이자율
	int interRate;

	// 생성자 : 상속 받은 변수와 확장한 변수 초기화
	public NormalAccount(String id, String name, int money, int rate) {
		super(id, name, money);
		this.interRate = rate;
	}
	
	// Account 클래스에서 오버라이딩한 메서드
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		
		System.out.println("기본이자 : "+ interRate +"%");
		System.out.println("---------------");
	}
	
	// Account 클래스의 plusAccMoney를 오버라이딩을 통해 재정의
	@Override
	public boolean plusAccMoney(int money) {
		/*
		이자계산은 입금시에만 잔고를 대상으로 계산. 출금할때는 이자계산 X
		[ 이자계산방식 - 일반계좌 ]
		: 잔고 + (잔고 * 기본이자) + 입금액
		*/
		
		// Account 클래스의 잔고(get)
		int accMoney = getaccMoney();
		
		// 이자계산
		double calMoney = accMoney + (accMoney * (interRate/100.0)) + money;
		
		// Account 클래스의 잔고(set)에 이자계산
		setaccMoney((int)calMoney);
		
		return true;
	}
	
	@Override
	public String toString() {
		return "[보통예금계좌] "+ super.toString() +"기본이자율: "+ interRate +"%";
	}
	
}
