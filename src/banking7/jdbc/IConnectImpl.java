package banking7.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectImpl implements IConnect {

	// 멤버변수
	public Connection con; 			// DB 연결
	public Statement stmt; 			// 정적쿼리 실행
	public PreparedStatement psmt;  // 동적쿼리문 실행
//	public CallableStatement csmt;  // 프로시저 실행
	public ResultSet rs; 			// select 실행결과 반환
	
	// 인수생성자 : 아이디, 패스워드 매개변수로 받음
	public IConnectImpl(String user, String pass) {
		try {
			// 인터페이스에 선언된 멤버상수(ORACLE_DRIVER) 사용
			Class.forName(ORACLE_DRIVER);
			// 매개변수를 통해 받은 계정정보를 사용
			connect(user, pass);
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	// DB 연결
	@Override
	public void connect(String user, String pass) {
		try {
			// 인터페이스에서 선언된 멤버상수(ORACLE_URL) 사용
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		}
		catch(SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
	}

	// 오버라이딩의 목적으로 정의한 메서드
	@Override
	public void execute() {
		/*
		각 클래스에서 목적에 맞게 재정의
		*/
	}

	// 자원반납
	@Override
	public void close() {
		try {
			if(con!=null) con.close();		// Connection객체에 대한 자원반납 처리
			if(stmt!=null) stmt.close();	// Statement객체에 대한 자원반납 처리
			if(psmt!=null) psmt.close();	// PreparedStatement객체에 대한 자원반납 처리
//			if(csmt!=null) csmt.close();	// CallableStatement객체에 대한 자원반납 처리
			if(rs!=null) rs.close();		// ResultSet객체에 대한 자원반납 처리
			System.out.println("자원반납완료");
		}
		catch(Exception e) {
			System.out.println("지원반납시 오류발생");
			e.printStackTrace();
		}
	}
	
}
