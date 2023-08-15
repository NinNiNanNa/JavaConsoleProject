package shopping;

public interface IConnect {
	
	// 멤버상수 : 오라클 드라이버명과 URL을 선언
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	/*
	커넥션URL => jdbc:oracle:thin:@오라클서버의IP주소:포트번호:SID명
	※ 서버환경에 따라 ip주소, 포트번호, sid는 변경될 수 있다.
	*/

	// 멤버추상메서드
	void connect(String user, String pass); // DB 연결
	void execute(); // 쿼리 실행
	void close(); // 자원 반납

	// 사용자로부터 입력을 받기위한 메서드
	String scanValue(String title);
	int scanInt(String title);
	
}
