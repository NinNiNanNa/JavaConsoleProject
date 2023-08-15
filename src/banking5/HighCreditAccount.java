package banking5;

// 신용신뢰계좌 > Account의 자식클래스로 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌
public class HighCreditAccount extends Account {

	// 멤버변수 : 기본이자율, 신용등급
	int interRate;
	String creditGrade;

	// 생성자 : 상속 받은 변수와 확장한 변수 초기화
	public HighCreditAccount(String id, String name, int money, int rate, String credit) {
		super(id, name, money);
		this.interRate = rate;
		this.creditGrade = credit;
	}

	// Account 클래스에서 오버라이딩한 메서드
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		
		System.out.println("기본이자 : "+ interRate +"%");
		System.out.println("신용등급(A,B,C등급) : "+ creditGrade);
		System.out.println("---------------");
	}
	
	// Account 클래스의 plusAccMoney를 오버라이딩을 통해 재정의
	@Override
	public boolean plusAccMoney(int money) {
		/*
		- 이자계산은 입금시에만 잔고를 대상으로 계산. 출금할때는 이자계산 X
		- 고객의 신용등급을 A, B, C로 나누고 계좌개설시 이 정보를 등록
		- A,B,C 등급별로 각각 기본이율에 7%, 4%, 2%의 이율을 추가로 제공
		[ 이자계산방식 - 신용계좌 ]
		: 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
		*/
		
		// Account 클래스의 잔고(get), 이자계산
		int accMoney = getaccMoney();
		double calMoney = 0;
		
		// equalsIgnoreCase : 대소문자 구분없이 비교 (equals는 대소문자 구분함)
		if(creditGrade.equalsIgnoreCase("A")) {
			calMoney = accMoney + (accMoney * (interRate/100.0)) + (accMoney * (ICustomDefine.A/100.0)) + money;
		}
		else if(creditGrade.equalsIgnoreCase("B")) {
			calMoney = accMoney + (accMoney * (interRate/100.0)) + (accMoney * (ICustomDefine.B/100.0)) + money;
		}
		else if(creditGrade.equalsIgnoreCase("C")) {
			calMoney = accMoney + (accMoney * (interRate/100.0)) + (accMoney * (ICustomDefine.C/100.0)) + money;
		}
		
		// Account 클래스의 잔고(set)에 이자계산
		setaccMoney((int)calMoney);
		
		return true;
	}
	
}
