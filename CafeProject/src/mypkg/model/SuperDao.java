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
	
	protected Connection getConnection() {// Ŀ�ؼ� ��ü�� �����ִ� �޼ҵ�		
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
	public SuperDao() {// ����̹� �ε�
		try {
			Class.forName(driver);
			this.conn = getConnection();
			if (conn != null) {
				//System.out.println("���!");
			} else {
				System.out.println("�ȹٷ� �Ͻ��� ���Դϴ�!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("��Ÿ �Ǵ� jar ���� ��ġ Ȯ�� ���");
			e.printStackTrace();
		}
	}
}

/*  dml �۾��� �ϱ� ���� ���ø�
	
	System.out.println( bean.toString() ); 
	String sql = "" ; //�����ڰ� ������ �� 1
	PreparedStatement pstmt = null ;
	int cnt = -99999 ;
	try {
		if( conn == null ){ super.conn = super.getConnection() ; }
		conn.setAutoCommit( false );
		pstmt = super.conn.prepareStatement(sql) ;
		//�����ڰ� ������ �� 2 : ? ������ ��
		
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

