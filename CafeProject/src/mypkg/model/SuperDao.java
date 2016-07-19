package mypkg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDao {
	protected Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "cafe";
	private String password = "oracle";
	
	protected Connection getConnection() {// 커넥션 객체를 구해주는 메소드		
		try {
			return DriverManager.getConnection(url, id, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	public void closeConnection(){
		conn = null;
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}			
//		}
	}	
	public SuperDao() {// 드라이버 로딩
		try {
			Class.forName(driver);
			this.conn = getConnection();
			if (conn != null) {
				//System.out.println("얏따!");
			} else {
				System.out.println("똑바로 하시지 말입니다!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("오타 또는 jar 파일 위치 확인 요망");
			e.printStackTrace();
		}
	}
}

/*  dml 작업을 하기 위한 탬플릿
	
	System.out.println( bean.toString() ); 
	String sql = "" ; //개발자가 수정할 곳 1
	PreparedStatement pstmt = null ;
	int cnt = -99999 ;
	try {
		if( conn == null ){ super.conn = super.getConnection() ; }
		conn.setAutoCommit( false );
		pstmt = super.conn.prepareStatement(sql) ;
		//개발자가 수정할 곳 2 : ? 수정할 것
		
		cnt = pstmt.executeUpdate() ; 
		conn.commit(); 
	} catch (Exception e) {
		SQLException err = (SQLException)e ;			
		cnt = - err.getErrorCode() ;			
		e.printStackTrace();
		try {
			conn.rollback(); 
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	} finally{
		try {
			if( pstmt != null ){ pstmt.close(); }
			super.closeConnection(); 
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return cnt ;

*/

