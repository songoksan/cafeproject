package mypkg.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends SuperDao {
	public int InsertData( Product bean ){
		System.out.println( bean.toString() ); 
		String sql = "insert into products(pid, name, image, stock, price, category, pcontent, point) " ;
		sql += " values(pid.nextval, ?, ?, ?, ?, ?, ?, ?)" ;
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getImage());
			pstmt.setInt(3, bean.getStock());
			pstmt.setInt(4, bean.getPrice());
			pstmt.setString(5, bean.getCategory());
			pstmt.setString(6, bean.getPcontent());
			pstmt.setInt(7, bean.getPoint());
			
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
	
	
//	public int UpdateData( Product bean ){
//		System.out.println( bean.toString() ); 
//		String sql = "update products set " ;
//		sql += " name=?, company=?, image=?, stock=?, price=?, " ;
//		sql += " category=?, contents=?, point=?, inputdate=to_date(?, 'yyyy/MM/dd')  " ; 
//		sql += " where num = ? " ;
//
//		PreparedStatement pstmt = null ;
//		int cnt = -99999 ;
//		try {
//			if( conn == null ){ super.conn = super.getConnection() ; }
//			conn.setAutoCommit( false );
//			pstmt = super.conn.prepareStatement(sql) ;
//			pstmt.setString(1, bean.getName());
//			pstmt.setString(2, bean.getCompany());
//			pstmt.setString(3, bean.getImage());
//			pstmt.setInt(4, bean.getStock());
//			pstmt.setInt(5, bean.getPrice());
//			pstmt.setString(6, bean.getCategory());
//			pstmt.setString(7, bean.getContents());
//			pstmt.setInt(8, bean.getPoint());
//			pstmt.setString(9, bean.getInputdate());			
//			pstmt.setInt(10, bean.getNum() );
//			
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
//	public int DeleteData( int pmkey ){
//		String sql = " delete from products where num = ? " ;
//		PreparedStatement pstmt = null ;
//		int cnt = -99999 ;
//		try {
//			if( conn == null ){ super.conn = super.getConnection() ; }
//			conn.setAutoCommit( false );
//			pstmt = super.conn.prepareStatement(sql) ;
//			pstmt.setInt(1, pmkey);
//			
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
//	public ProductDao() {
//	
//	}
//	public List<Product> SelectDataList() {
//		//모든 데이터를 조회한다.
//		PreparedStatement pstmt = null ;
//		ResultSet rs = null ;
//		String sql = "select * from products order by num desc " ;
//		List<Product> lists = new ArrayList<Product>();
//		try {
//			if( conn == null ){ super.conn = super.getConnection() ; }
//			pstmt = super.conn.prepareStatement(sql) ;			
//			rs = pstmt.executeQuery() ;			
//			while( rs.next() ){
//				Product bean = new Product();
//				bean.setNum( rs.getInt("num") );
//				bean.setName( rs.getString("name") ); 
//				bean.setCompany( rs.getString("company") );				
//				bean.setImage( rs.getString("image") );		
//				bean.setStock( rs.getInt("stock") );
//				bean.setPrice( rs.getInt("price") );
//				bean.setCategory( rs.getString("category") );
//				bean.setContents( rs.getString("contents") );
//				bean.setPoint( rs.getInt("point") );
//				bean.setInputdate( String.valueOf( rs.getDate("inputdate"))) ;					 
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
	public List<Product> SelectDataList(int beginRow, int endRow) {
		//랭킹을 이용하여 모든 데이터를 조회한다.
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select pid, name, stock, price, category, image, pcontent, point, ranking "; 
		sql += " from " ; 
		sql += " ( " ;
		sql += " select pid, name, stock, price, category, image, pcontent, point, rank() over( order by pid asc ) as ranking " ;
		sql += " from products  " ;
		sql += " ) " ;
		sql += " where ranking between ? and ? " ; 

		List<Product> lists = new ArrayList<Product>() ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow); 
			
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				Product bean = new Product() ; 
				bean.setPid( rs.getInt("pid") );
				bean.setName( rs.getString("name") ); 
				bean.setImage( rs.getString("image") );		
				bean.setStock( rs.getInt("stock") );
				bean.setPrice( rs.getInt("price") );
				bean.setCategory( rs.getString("category") );
				bean.setPcontent( rs.getString("pcontent") );
				bean.setPoint( rs.getInt("point") );
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
	
	//상품 1건에 대한 상세 정보 보기
	public Product SelectDataByPk( int pid  ){
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = "select * " ;
		sql += " from products " ; 
		sql += " where pid = ? " ;
		Product bean = null ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			
			//placehodelr 치환은 반드시 execute 메소드 실행 바로 직전에 하세요. 
			pstmt.setInt( 1, pid   ); 
			rs = pstmt.executeQuery() ; 
			
			if ( rs.next() ) {
				bean = new Product() ;
				bean.setPid( rs.getInt("pid") );
				bean.setName( rs.getString("name") ); 
				bean.setStock( rs.getInt("stock") );				
				bean.setImage( rs.getString("image") );		
				bean.setPrice( rs.getInt("price") );
				bean.setCategory( rs.getString("category") );
				bean.setPcontent( rs.getString("pcontent") );
				bean.setPoint( rs.getInt("point") );
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
		
		String sql = "select count(*) as cnt from products";
		int cnt = 0; //없는 경우의 기본값
		
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

}











