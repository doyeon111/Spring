package com.sist.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.db.ConnectionProvider;
import com.sist.vo.BoardVO;

public class BoardDAO {
	
	public int pageSIZE = 10; //한 화면에 보여줄 레코드의 수 (10페이지씩만 게시물을 보여줌)
	public int totalPage = 1; //전체 페이지 수를 저장
	public int totalRecord = 0; //전체 레코드 수
	
	//이미 달려있는 답글들의 b_step을 1씩 증가시키는 메소드
	public void updateStep(int b_ref, int b_step) {
		//서로 관련있는 글들 중에서 답글을 달려고 하는 부모글의 b_step보다 더 큰 글들에 대하여 b_step을 1씩 증가시킨다.
		String sql = "update board set b_step = b_step + 1 where b_ref = ? and b_step > ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_ref);
			pstmt.setInt(2, b_step);
			pstmt.executeUpdate();
			
			ConnectionProvider.close(null, pstmt, conn);
			
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
	}
	
	
	//전체 레코드의 수를 반환하는 메소드
	public int getTotalRecord() {
		int n = 0;
		String sql = "select count(*) from board";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				n = rs.getInt(1);
			}
			
			ConnectionProvider.close(rs, stmt, conn);
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		return n;
	}
	
	public int deleteBoard(int no, String pwd) {
		int re = -1;
		String sql = "delete board where no = ? and pwd = ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, pwd);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(null, pstmt, conn);
			
					
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		return re;
	}
	
	public int updateBoard(BoardVO b) {
		int re = -1;
		String sql = "update board set title = ?, content = ?, fname = ? where no = ? and pwd = ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFname());
			pstmt.setInt(4, b.getNo());
			pstmt.setString(5, b.getPwd());
			
			re = pstmt.executeUpdate();
			
			ConnectionProvider.close(null, pstmt, conn);
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		return re;
	}
	
	public void updateHit(int no) {
		String sql = "update board set hit=hit+1 where no = ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			ConnectionProvider.close(null, pstmt, conn);
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
	}
	
	
	//이메소드에서 조회수를 증가할 수도 있다. 그런데, 이 메소드는 '상세보기' 뿐 아니라 '수정하기'할 때 원래 게시물의 정보를 갖고와서 폼태그에 출력할 때도 이 메소드가 필요하다.
	// 그래서 이 메소드 안에서 무조건 조회수를 증가하면 안된다. 따라서, 조회수를 증가할지, 증가하지 않을지 boolean의 변수를 받아서 처리할 수 있다.
	public BoardVO getBoard(int pno) {
		BoardVO b = null;
		String sql = "select * from board where no = ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {

				b = new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), null, rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
				
			}
			
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		
		return b;
		
	}
	
	
	
	public ArrayList<BoardVO> listBoard(int pageNUM) {
		
		System.out.println("pageNUM: " + pageNUM);
		
		//totalRecord를 계산하는 명령
		totalRecord = getTotalRecord();
		totalPage = (int)Math.ceil(totalRecord / (double)pageSIZE);
		
		/*totalPage = totalRecord / pageSIZE;
		
		if(totalRecord % pageSIZE != 0) { //위의 값을 나누었을 때 나머지가 0이 아니라면 페이지 수를 하나 늘려준다.
			totalPage++;
		}*/
		
		
		//현재 페이지에 보여줄 시작레코드를 계산
		int start = pageSIZE * pageNUM - pageSIZE + 1;
		//int start1 = (pageNUM-1) * pageSIZE+1;
		System.out.println("start: " + start);
		//System.out.println("start: " + start1);
		
		int end = start+pageSIZE-1;
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "select no, title, writer, pwd, content, regdate, hit, fname, b_ref, b_step, b_level "
				+ "from ( select rownum n, no, title, writer, pwd, content, regdate, hit, fname, b_ref, b_step, b_level "
				+ "from (select * from board order by b_ref, b_step)) "
				+ "where n between ? and ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String pwd = rs.getString(4);
				String content = rs.getString(5);
				Date regdate = rs.getDate(6);
				int hit = rs.getInt(7);
				String fname = rs.getString(8);
				
				int b_ref = rs.getInt(9);
				int b_step = rs.getInt(10);
				int b_level = rs.getInt(11);
				
				BoardVO b = new BoardVO();
				b.setNo(no);
				b.setTitle(title);
				b.setWriter(writer);
				b.setPwd(pwd);
				b.setContent(content);
				b.setRegdate(regdate);
				b.setHit(hit);
				b.setFname(fname);
				b.setB_ref(b_ref);
				b.setB_step(b_step);
				b.setB_level(b_level);
				
				list.add(b);
				
				
				
			}
			
			ConnectionProvider.close(rs, pstmt, conn);
			
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		return list;
	}
	
	
	
	public int getNextNo () {
		int no = 0;
		
		String sql = "select nvl(max(no), 0)+1 from board";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
					
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			ConnectionProvider.close(rs, stmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		return no;
	}
	
	public int insertBoard(BoardVO b) {
		int re = -1;
		String sql = "insert into Board(no, title, writer, pwd, content, regdate, hit, fname, b_ref, b_step, b_level) values(?,?,?,?,?,sysdate,0,?,?,?,?)";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNo());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getWriter());
			pstmt.setString(4, b.getPwd());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getFname());
			pstmt.setInt(7, b.getB_ref());
			pstmt.setInt(8, b.getB_step());
			pstmt.setInt(9, b.getB_level());
			re = pstmt.executeUpdate();
			
			//connectionProvider에서 받아옴
			ConnectionProvider.close(null, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
		
		
		return re;
	}
	
}
