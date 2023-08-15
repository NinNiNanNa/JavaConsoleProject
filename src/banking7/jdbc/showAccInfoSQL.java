package banking7.jdbc;

import java.sql.SQLException;

// 전체계좌정보출력 - select문으로 구현(Statement 인터페이스 이용)
public class showAccInfoSQL extends IConnectImpl {
	
	// 생성자 : 부모클래스의 생성자를 호출하여 연결
	public showAccInfoSQL() {
		super("education", "1234");
	}
	
	// 실행부
	@Override
	public void execute() {
		// Statement 인터페이스 이용
		try {
			// 전체계좌정보출력 쿼리문 준비
			String sql = "SELECT * FROM banking_tb";
			// Statement()객체 생성
			stmt = con.createStatement();
			// 쿼리문을 오라클 데이터베이스로 전송
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String id = rs.getString("accountID");
				String name = rs.getString("name");
				int money = rs.getInt("balance");
				
				System.out.printf(" [ 계좌번호: %s | 고객이름: %s | 잔액: %d ] %n", id, name, money);
			}
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
