package shopping;

import java.sql.SQLException;

/*
PreparedStatement객체를 사용하여 작성한다.
클래스명 : InsertShop
상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용한다. 
입력이 완료되면 입력된 행의 갯수를 반환하여 출력한다. 
*/
public class InsertShop extends IConnectImpl {

	// 생성자 : 부모클래스의 생성자를 호출하여 연결
	public InsertShop() {
		super("education", "1234");
	}
	
	// 실행부
	@Override
	public void execute() {
		// PreparedStatement객체를 사용하여 작성
		try {
			// 쿼리문 준비 : 값의 세팅을 위한 부분을 ?(인파리미터)로 기술
			String sql = "INSERT INTO sh_goods VALUES (seq_total_idx.NEXTVAL, ?, ?, sysdate, ?)";
			// prepareStatement()객체 생성 : 준비한 쿼리문을 인수로 전달
			psmt = con.prepareStatement(sql);
			
			// 상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용
			psmt.setString(1, scanValue("상품명"));
			psmt.setString(2, scanValue("상품가격"));
			psmt.setString(3, scanValue("상품코드[ 스마트폰(1), 태블릿(2), 워치(3), 갤럭시북(4), 버즈(5) ]"));
			
			// 입력이 완료되면 입력된 행의 갯수를 반환하여 출력
			int affected = psmt.executeUpdate();
			System.out.println(Color.GREEN + affected +"행이 입력되었습니다."+ Color.EXIT);
			
		}
		catch(SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		finally {
			// DB 자원반납
			close();
		}
	}

}
