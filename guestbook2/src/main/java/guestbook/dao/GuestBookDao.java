package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guestbook.Vo.GuestBookVo;


public class GuestBookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
		Class.forName("org.mariadb.jdbc.Driver");
		
		String url = "jdbc:mariadb://192.168.0.202:3306/webdb?charset=utf-8";
		
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: "+e);
		} 
		return conn;
	}
	


	public void deleteByNo(Long no, String pw) {
		try(
			Connection conn = getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no=? and password=?");  
		){
			pstmt.setLong(1, no);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error : "+e);
		}
		
	}

	
  public int insert(GuestBookVo vo) {
	    Connection conn = null;
	    PreparedStatement pstmt1 = null;
	    PreparedStatement pstmt2 = null;
	    PreparedStatement pstmt3 = null;
	    int result = 0;

	    try {
	      conn = getConnection();
	      pstmt1 = conn.prepareStatement("update guestbook_log set count = count +1 where date = current_date();");
	      pstmt2 = conn.prepareStatement("insert into guestbook_log value(current_date(), 1)");  // 결과가 0이면 insert 
	      pstmt3 = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?, now())");
	      pstmt3.setString(1, vo.getName());
	      pstmt3.setString(2, vo.getPassword());
	      pstmt3.setString(3, vo.getContents());
	      
	      
	      // TX:BEGIN ///////
	      conn.setAutoCommit(false);
	      
	      // DML1
	      int rowCount = pstmt1.executeUpdate();
	      
	      // DML2
	      if(rowCount == 0) {
	    	  pstmt2.executeUpdate();
	      }
	      
	      // DML3	      
	      result = pstmt3.executeUpdate();
	      
	  	// Tx: end (SUCCESS) /////
			conn.commit();
			
	    } catch (SQLException e) {		// 중간에 fail이 발생하면 catch 문으로 들어감 
	      System.out.println("Error:" + e);
	      
		      try {
		    	  conn.rollback();
		      } catch(SQLException ignored) {
		    	  
		      }
	      
	    } finally {
	      try {
	        if (pstmt3 != null) {
	          pstmt1.close();
	        }
	        if (pstmt2 != null) {
		          pstmt1.close();
		        }
	        if (pstmt1 != null) {
		          pstmt1.close();
		        }
	        if (conn != null) {
	          conn.close();
	        }
	      } catch (SQLException ignored) {
	      }
	    }
	    return result;
	  }

	public List<GuestBookVo> findAll(){
		List<GuestBookVo> result = new ArrayList<>();
		
		try(
				Connection conn = getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("select no,name,password,contents,reg_date from guestbook order by no desc");  
					
			
			){
			
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					GuestBookVo vo = new GuestBookVo();
					
					Long no = rs.getLong(1);
					String name = rs.getString(2);
					String pw = rs.getString(3);
					String contents = rs.getString(4);
					String regDate = rs.getString(5);
					
					vo.setNo(no);
					vo.setName(name);
					vo.setPassword(contents);
					vo.setContents(contents);
					vo.setRegDate(regDate);
					
					result.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("error : "+e);
			}

		
		return result;
	}
}

