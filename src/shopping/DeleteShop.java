package shopping;

import java.sql.SQLException;
import java.sql.Types;

/*
프로시저 작성후 CallableStatement객체를 사용하여 호출하도록 한다.
프로시저명 : ShopDeleteGoods
In파라미터 : 삭제할 상품의 일련번호
Out파라미터 : 레코드 삭제 결과(1 혹은 0)
클래스명 : DeleteShop
*/
public class DeleteShop extends IConnectImpl {

	// 생성자 : 부모클래스의 생성자를 호출하여 연결
	public DeleteShop() {
        super("education", "1234");
    }
	
	// 실행부
	@Override
	public void execute() {
		// CallableStatement객체를 사용하여 호출
		try {
	        // 프로시져 호출을 위한 Callable Statement 객체 생성
			csmt = con.prepareCall("{call ShopDeleteGoods(?, ?) }");
			
	        // 인파라미터 설정
			csmt.setInt(1, scanInt("삭제할 상품의 일련번호"));
			csmt.registerOutParameter(2, Types.NUMERIC);
	        // 프로시져 실행
			csmt.execute();

	        // 아웃파라미터가 문자형이므로 getString() 으로 출력한다.
	        System.out.println(Color.GREEN + csmt.getString(2) + "행이 삭제되었습니다.\n"+ Color.EXIT);
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
