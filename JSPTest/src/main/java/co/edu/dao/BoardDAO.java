package co.edu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.vo.BoardVO;
import co.edu.vo.Criteria;




public class BoardDAO {

	// ORACLE DB에 대한 정보
			private String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
			private String oracleUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			private String connectedId ="hr";
			private String connectedPwd ="hr";
			// 공통으로 사용되는 필드

			protected Connection conn;
			protected Statement stmt;
			protected PreparedStatement pstmt;
			protected ResultSet rs;

			// 한번만 실행시키고자 한다면 생성자 안에서 생성해도 됨
			public BoardDAO() {
				//dbConfig();
			}

			// DAO에는 DB접속,해제 메서드만 있는게 정석
			// DB접속 메서드
			public void connect() {
				try {
					Class.forName(jdbcDriver);

					conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);

				} catch (ClassNotFoundException e) {
					System.out.println("jdbc driver 로딩 실패");

				} catch (SQLException e) {
					System.out.println("DB 연결 실패");
				}
			}

			// DB해제 메서드
			public void disconnect() {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void addBoard(BoardVO vo) {
				String sql = "insert into test_board(seq, title, writer, content, write_date, visit_cnt) values(board_seq.nextval,?,?,?,sysdate,0)";
				connect();
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, vo.getTitle());
					pstmt.setString(2, vo.getWriter());
					pstmt.setString(3, vo.getContent());
					
					int r = pstmt.executeUpdate();
					System.out.println(r + "건 입력");
					
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
			}
			public List<BoardVO> getList(){      
			      List<BoardVO> list = new ArrayList<>();
			      String sql = "SELECT * FROM test_board order by 1";
			      
			      try {
			         connect();
			         pstmt = conn.prepareStatement(sql);         
			         rs = pstmt.executeQuery();
			         
			         while(rs.next()) {
			            BoardVO vo = new BoardVO();
			            vo.setSeq(rs.getString("seq"));
			            vo.setTitle(rs.getString("title"));
			            vo.setWriter(rs.getString("writer"));
			            vo.setContent(rs.getString("content"));
			            vo.setWriteDate(rs.getString("write_date"));
			            vo.setVisitCNT(rs.getString("visit_cnt"));
			            
			            list.add(vo);            
			         }
			         
			      }catch(SQLException e) {
			         e.printStackTrace();
			      }
			      
			      return list;
			   }
			//파라미터중 amount : 한 페이지에 보여줄 데이터 수, pageNum : 페이지 번호
			public List<BoardVO> getListPaging(Criteria cri){
				
				List<BoardVO> listPage = new ArrayList<>();
				connect();		
			try {
		        
		         String sql = "SELECT seq, title, content, writer, write_date, visit_cnt "
							+ "FROM (select rownum rn, seq, title, content, writer, write_date, visit_cnt "
							+ 		"FROM (SELECT * "
							+ "      	   FROM test_board "
							+ "      	   ORDER BY seq desc) "
							+ "		WHERE rownum <= ?) " //1: 0~10, 2: 10~20
							+ "WHERE rn > ?";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1, cri.getAmount()*cri.getPageNum());// 10*1
		         pstmt.setInt(2, cri.getAmount()*(cri.getPageNum()-1));
		         
		         rs = pstmt.executeQuery();
		       	        
		           while(rs.next()) {
		        	  BoardVO board = new BoardVO();
		        	  board.setSeq(rs.getString("seq"));
		        	  board.setTitle(rs.getString("title"));
		        	  board.setWriteDate(rs.getString("write_date"));
		        	  board.setWriter(rs.getString("writer"));
		        	  board.setContent(rs.getString("content"));
		        	  board.setVisitCNT(rs.getString("visit_cnt"));
		        	  
		        	  listPage.add(board);        
		                    
		         }
		         
		      }catch(SQLException e) {
		         e.printStackTrace();
		      }
				return listPage;
			}
}
