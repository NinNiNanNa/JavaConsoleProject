package banking4;

/*
개발자가 직접 정의한 예외처리 클래스
*/
public class MenuSelectException extends Exception {

	public MenuSelectException() {
		super(Color.RED +"[예외발생] 지정된 메뉴의 숫자만 입력가능합니다."+ Color.EXIT);
	}
	
}
