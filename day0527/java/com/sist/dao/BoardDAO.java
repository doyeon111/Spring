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
	
	public int pageSIZE = 10; //�� ȭ�鿡 ������ ���ڵ��� �� (10���������� �Խù��� ������)
	public int totalPage = 1; //��ü ������ ���� ����
	public int totalRecord = 0; //��ü ���ڵ� ��
	
	//�̹� �޷��ִ� ��۵��� b_step�� 1�� ������Ű�� �޼ҵ�
	public void updateStep(int b_ref, int b_step) {
		//���� �����ִ� �۵� �߿��� ����� �޷��� �ϴ� �θ���� b_step���� �� ū �۵鿡 ���Ͽ� b_step�� 1�� ������Ų��.
		String sql = "update board set b_step = b_step + 1 where b_ref = ? and b_step > ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_ref);
			pstmt.setInt(2, b_step);
			pstmt.executeUpdate();
			
			ConnectionProvider.close(null, pstmt, conn);
			
			
		} catch(Exception e) {
			System.out.println("���ܹ߻�: " + e.getMessage());
		}
	}
	
	
	//��ü ���ڵ��� ���� ��ȯ�ϴ� �޼ҵ�
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
			System.out.println("���ܹ߻�: " + e.getMessage());
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
			System.out.println("���ܹ߻�: " + e.getMessage());
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
			System.out.println("���ܹ߻�: " + e.getMessage());
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
			System.out.println("���ܹ߻�: " + e.getMessage());
		}
		
	}
	
	
	//�̸޼ҵ忡�� ��ȸ���� ������ ���� �ִ�. �׷���, �� �޼ҵ�� '�󼼺���' �� �ƴ϶� '�����ϱ�'�� �� ���� �Խù��� ������ ����ͼ� ���±׿� ����� ���� �� �޼ҵ尡 �ʿ��ϴ�.
	// �׷��� �� �޼ҵ� �ȿ��� ������ ��ȸ���� �����ϸ� �ȵȴ�. ����, ��ȸ���� ��������, �������� ������ boolean�� ������ �޾Ƽ� ó���� �� �ִ�.
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
			System.out.println("���ܹ߻�: " + e.getMessage());
		}
		
		
		return b;
		
	}
	
	
	
	public ArrayList<BoardVO> listBoard(int pageNUM) {
		
		System.out.println("pageNUM: " + pageNUM);
		
		//totalRecord�� ����ϴ� ���
		totalRecord = getTotalRecord();
		totalPage = (int)Math.ceil(totalRecord / (double)pageSIZE);
		
		/*totalPage = totalRecord / pageSIZE;
		
		if(totalRecord % pageSIZE != 0) { //���� ���� �������� �� �������� 0�� �ƴ϶�� ������ ���� �ϳ� �÷��ش�.
			totalPage++;
		}*/
		
		
		//���� �������� ������ ���۷��ڵ带 ���
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
			System.out.println("���ܹ߻�: " + e.getMessage());
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
			System.out.println("���ܹ߻�: " + e.getMessage());
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
			
			//connectionProvider���� �޾ƿ�
			ConnectionProvider.close(null, pstmt, conn);
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�: " + e.getMessage());
		}
		
		
		return re;
	}
	
}
