package mypkg.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends SuperDao {
	public int InsertData( Member bean ){
		System.out.println( bean.toString() ); 
		String sql = " insert into members(mid, name, email, password, zipcode, address1, address2, phnumber) " ; 
		sql += " values(                     ?,    ?,     ?,        ?,       ?,        ?,        ?,        ?) " ;

		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			//�����ڰ� ������ �� 2 : ? ������ ��
			pstmt.setString(1, bean.getMid());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPassword());
			pstmt.setString(5, bean.getZipcode() );
			pstmt.setString(6, bean.getAddress1());
			pstmt.setString(7, bean.getAddress2() );
			pstmt.setString(8, bean.getPhnumber() );
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;
			//getErrorCode() : ����Ŭ ���� ����� ����
			//�� : not null �̸� 1400 
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
	}
	public int UpdateData( Member bean ){
		System.out.println( bean.toString() ); 
		String sql = " update members set ";
		sql += " name=?, password=?, email=?, phnumber=?, " ;    
		sql += " zipcode=?, address1=?, address2=?  " ;
		sql += " where mid = ? " ; 
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			//�����ڰ� ������ �� 2 : ? ������ ��

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhnumber());
			pstmt.setString(5, bean.getZipcode());
			pstmt.setString(6, bean.getAddress1());
			pstmt.setString(7, bean.getAddress2());
			pstmt.setString(8, bean.getMid());			
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
	}
	
	
//	public int DeleteData( String pmkey ){		
//		String sql = " delete from members where id = ? " ;
//		PreparedStatement pstmt = null ;
//		int cnt = -99999 ;
//		try {
//			if( conn == null ){ super.conn = super.getConnection() ; }
//			conn.setAutoCommit( false );
//			pstmt = super.conn.prepareStatement(sql) ;
//			pstmt.setString(1, pmkey);			
//			cnt = pstmt.executeUpdate() ; 
//			conn.commit(); 
//		} catch (Exception e) {
//			SQLException err = (SQLException)e ;			
//			cnt = - err.getErrorCode() ;			
//			e.printStackTrace();
//			try {
//				conn.rollback(); 
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		} finally{
//			try {
//				if( pstmt != null ){ pstmt.close(); }
//				super.closeConnection(); 
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return cnt ;
//	}
//	
//	public List<Member> SelectDataList() {
//		//��� �����͸� ��ȸ�Ѵ�.
//		PreparedStatement pstmt = null ;
//		ResultSet rs = null ;
//		String sql = "select * from members order by name " ;
//		List<Member> lists = new ArrayList<Member>();
//		try {
//			if( conn == null ){ super.conn = super.getConnection() ; }
//			pstmt = super.conn.prepareStatement(sql) ;			
//			rs = pstmt.executeQuery() ;			
//			while( rs.next() ){
//				Member bean = new Member();
//				bean.setAddress1( rs.getString("address1") );
//				bean.setAddress2( rs.getString("address2") );
//				bean.setGender( rs.getString("gender") );
//				
//				String[] hobby = rs.getString("hobby").split(",");
//				bean.setHobby( hobby );
//				
//				bean.setId( rs.getString("id") );
//				bean.setJob( rs.getString("job") );
//				bean.setZipcode( rs.getString("zipcode") );
//				bean.setPassword( rs.getString("password") );
//				bean.setName( rs.getString("name") );
//				
//				//��¥�� String.valueOf( rs.getDate() ) �� ����
//				bean.setHiredate( String.valueOf(rs.getDate("hiredate")) );
//				
//				//���ڴ� Integer.parsteInt()
//				bean.setMpoint( Integer.parseInt(rs.getString("mpoint")) );
//				bean.setSalary( Integer.parseInt(rs.getString("salary")) );
//							 				 
//				lists.add( bean ) ;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			try {
//				if( rs != null ){ rs.close(); }
//				if( pstmt != null ){ pstmt.close(); }
//				super.closeConnection(); 
//			} catch (Exception e2) {
//				e2.printStackTrace(); 
//			}
//		}
//		
//		return lists ;
//	}
//	
	public List<Member> SelectDataList(int beginRow, int endRow) {
		//��ŷ�� �̿��Ͽ� ��� �����͸� ��ȸ�Ѵ�.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select mid, name, password, email, address1, address2, phnumber, mpoint, ranking "; 
		sql += " from " ; 
		sql += " ( " ;
		sql += " select mid, name, password, email, address1, address2, phnumber, mpoint, rank() over( order by mid asc ) as ranking " ;
		sql += " from members  " ;
		sql += " ) " ;
		sql += " where ranking between ? and ? " ; 

		List<Member> lists = new ArrayList<Member>() ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow); 
			
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				Member bean = new Member() ; 
				bean.setMid( rs.getString("mid") );				
				bean.setName( rs.getString("name") );
				bean.setPassword( rs.getString("password") );
				bean.setEmail( rs.getString("email") );
				bean.setAddress1( rs.getString("address1") );
				bean.setAddress2( rs.getString("address2") );
				bean.setPhnumber( rs.getString("Phnumber") );
				bean.setMpoint(rs.getInt("mpoint"));
				
				lists.add( bean ) ; 
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return lists  ;
	}
	
	public MemberDao() {
		
	}
	
	//Ư�� ȸ���� �� ���� ����
	public Member SelectDataByPk( String mid ){
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = " select * " ;
		sql += " from members " ; 
		sql += " where mid = ? " ;
		Member bean = null ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			
			//placehodelr ġȯ�� �ݵ�� execute �޼ҵ� ���� �ٷ� ������ �ϼ���. 
			pstmt.setString( 1, mid   ); 
			rs = pstmt.executeQuery() ;
			
			if ( rs.next() ) {
				//1 ���� �߰ߵ�
				bean = new Member() ; 
				bean.setMid( rs.getString("mid") );
				bean.setName( rs.getString("name") );
				bean.setPassword( rs.getString("password") );
				bean.setEmail( rs.getString("email")) ;
				bean.setAddress1( rs.getString("address1") );
				bean.setAddress2( rs.getString("address2") );
				bean.setZipcode(rs.getString("zipcode"));
				bean.setPhnumber( rs.getString("phnumber") );
				bean.setMpoint(rs.getInt("mpoint"));
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return bean  ;
	}
	
	
	public int SelectTotalCount() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select count(*) as cnt from members";
		int cnt = 0; //���� ����� �⺻��
		
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }
			pstmt = this.conn.prepareStatement( sql ) ;
			rs = pstmt.executeQuery() ; 
			if ( rs.next() ) { 
				cnt = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( rs != null ){ rs.close(); }
				if( pstmt != null ){ pstmt.close(); }
				this.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return cnt ;
	}

	
	public List<Zipcode> SelectDataZipcode(String dong) {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from zipcodes " ;
		sql += " where dong like '" + dong + "%'" ;
		sql += " order by sido asc, gugun asc, dong asc " ; 
		List<Zipcode> lists = new ArrayList<Zipcode>();
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			pstmt = super.conn.prepareStatement(sql) ;			
			 
			rs = pstmt.executeQuery() ;			
			while( rs.next() ){
				Zipcode bean = new Zipcode();
				bean.setSido( rs.getString("sido") );
				bean.setBunji( rs.getString("bunji") );
				bean.setDong( rs.getString("dong") );
				bean.setGugun( rs.getString("gugun") );
				bean.setZipcode( rs.getString("zipcode") );
				bean.setSeqnum( Integer.parseInt( rs.getString("seqnum") ));
				 
				lists.add( bean ) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( rs != null ){ rs.close(); }
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}		
		return lists ;
	}
}