package shopping;

import java.sql.SQLException;
import java.util.Scanner;

/*
Statement객체를 사용하여 작성한다.
클래스명 : SelectShop
검색할 상품명을 입력받은 후 like를 통해 해당조건에 맞는 레코드만 출력한다. 
출력항목 : 일련번호, 상품명, 가격, 등록일, 제품코드
단, 등록일은 0000-00-00 00:00 형태로 출력해야 한다.	상품가격은 세자리마다 컴마를 찍어준다.
Statement객체는 인파라미터를 통한 동적쿼리를 작성할 수 없으므로 순수 Java변수를 사용한다.
*/
public class SelectShop extends IConnectImpl {
	
	Scanner scan = new Scanner(System.in);

	// 생성자 : 부모클래스의 생성자를 호출하여 연결
	public SelectShop() {
		super("education", "1234");
	}
	
	// 실행부
	@Override
	public void execute() {
		// Statement객체를 사용하여 작성
		try {
			System.out.println(Color.CYAN_BACK +"-------------------------- MENU --------------------------"+ Color.EXIT);
			System.out.println(Color.CYAN_BACK +"                1.전체상품조회 | 2.상품명으로조회                "+ Color.EXIT);
			System.out.println(Color.CYAN_BACK +"----------------------------------------------------------"+ Color.EXIT);
			System.out.print(Color.CYAN +"메뉴를 선택하세요 >> "+ Color.EXIT);
			int keyInput = scan.nextInt();

			stmt = con.createStatement();
			
			String sql = null;
			if(keyInput==1) {	// 1.전체상품조회를 선택했을 경우
				sql = "SELECT g_idx, goods_name, "
					+ " to_char(goods_price, '9,999,000'), "
					+ " to_char(regidate, ('yyyy-mm-dd hh24:mi')), p_code "
					+ " FROM sh_goods";
				
				rs = stmt.executeQuery(sql);
			}
			else if(keyInput==2) {	// 2.상품명으로조회를 선택했을 경우
				String product = scanValue("조회할 상품명");
				sql = "SELECT g_idx, goods_name, "
					+ " to_char(goods_price, '9,999,000'), "
					+ " to_char(regidate, ('yyyy-mm-dd hh24:mi')), p_code "
					+ " FROM sh_goods WHERE goods_name LIKE '%"+ product+ "%'";
				
				rs = stmt.executeQuery(sql);
			}
			else {
				System.out.println("메뉴를 다시 입력해주세요");
				return;
			}
			
			System.out.printf("%s\t | %10s\t\t | %5s\t | \t%s\t\t | %5s\t\n", "상품번호", "상품명", "가격", "등록일", "제품코드");
			
			while(rs.next()) {
				String idx = rs.getString(1); // g_idx컬럼
				String name = rs.getString(2); // goods_name컬럼
				String price = rs.getString(3); // goods_price컬럼
				String date = rs.getString(4).substring(0,16); // regidate컬럼
				String p_code = rs.getString(5); // p_code컬럼
				
				System.out.printf("%4s\t | %-14s\t | %s\t | %s\t | %5s\n",
						idx, name, price, date, p_code);
			}
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
