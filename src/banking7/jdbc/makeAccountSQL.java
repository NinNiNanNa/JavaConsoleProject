package banking7.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

// 계좌개설 - insert문으로 구현(PreparedStatement 인터페이스 이용)
public class makeAccountSQL extends IConnectImpl {
	
	// 키보드 입력
	Scanner scan = new Scanner(System.in);

	// 생성자 : 부모클래스의 생성자를 호출하여 연결
	public makeAccountSQL() {
		super("education", "1234");
	}
	
	// 실행부
	@Override
	public void execute() {
		// PreparedStatement 인터페이스 이용
		try {
			// 계좌개설 쿼리문 준비
			String sql = "INSERT INTO banking_tb VALUES (seq_banking.nextval, ?, ?, ?)";
			// PreparedStatement() 객체 생성
			psmt = con.prepareStatement(sql);
			
			// insert할 내용 입력받기
			System.out.print("계좌번호 : ");
			String accID = scan.nextLine();
			System.out.print("고객이름 : ");
			String cusName = scan.nextLine();
			System.out.print("잔고 : ");
			int accMonye = scan.nextInt();
			
			// 인파라미터 설정
			psmt.setString(1, accID);
			psmt.setString(2, cusName);
			psmt.setInt(3, accMonye);
			
			// 쿼리실행 및 결과값 반환
			psmt.executeUpdate();
			System.out.println(Color.GREEN +"\n계좌계설이 완료되었습니다."+ Color.EXIT);
		}
		catch(SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
}
