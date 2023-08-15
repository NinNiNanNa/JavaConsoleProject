package banking7.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

// 입금 - 이미 생성된 계좌에서 금액이 변동되는 것이므로 update문으로 구현(PreparedStatement 인터페이스 이용)
public class depositMoneySQL extends IConnectImpl {

	// 키보드 입력
	Scanner scan = new Scanner(System.in);
	
	// 생성자 : 부모클래스의 생성자를 호출하여 연결
	public depositMoneySQL() {
		super("education", "1234");
	}
	
	// 실행부
	@Override
	public void execute() {
		String sql = null;
		// PreparedStatement 인터페이스 이용
		try {
			// 입금을 진행할 계좌번호와 입금액 입력
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호 : ");
			String accID = scan.next();
			System.out.print("입금액 : ");
			int money = scan.nextInt();
			
			// 입금할 계좌번호 조회 쿼리문 준비
			sql = "SELECT balance FROM banking_tb WHERE accountID = ?";
			// PreparedStatement() 객체 생성
			psmt = con.prepareStatement(sql);
			psmt.setString(1, accID);
			rs = psmt.executeQuery();
			rs.next();
			int balance = rs.getInt(1);
			
			// 입금진행할 쿼리문 준비 - 수정
			sql = "UPDATE banking_tb SET balance = ? WHERE accountID = ?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, balance + money);
			psmt.setString(2, accID);
			
			// 쿼리실행 및 결과값 반환
			psmt.executeUpdate();
			System.out.println(Color.GREEN +"\n입금이 완료되었습니다."+ Color.EXIT);
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
