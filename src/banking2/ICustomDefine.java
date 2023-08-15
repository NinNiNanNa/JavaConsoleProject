package banking2;

/*
- interface로 생성한다. 
- 메뉴선택과 이자율 지정을 위한 인터페이스형 상수를 정의한다. 
- 메뉴 : 계좌개설, 입금, 출금, 전체계좌정보출력, 종료를 1~5까지로 지정한다.
- 이자율 : 고객의 신용등급을 A, B, C로 나눠서 7%, 4%, 2%로 지정한다.
*/
public interface ICustomDefine {
	// 메뉴
	int MAKE=1, DEPOSIT=2, WITHDRAW=3, INQUIRE=4, EXIT=5;
	
	// 이자율
	int A=7, B=4, C=2;
}
