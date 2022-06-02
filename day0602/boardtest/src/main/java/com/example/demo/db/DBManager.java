package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;

public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
	}
	
	public static List<BoardVO> listBoard(HashMap map) {
		List<BoardVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("board.findAll", map);
		session.close();
		
		return list;
	}
	
	public static BoardVO findByNo(int no) {
		BoardVO b = null;
		SqlSession session = sqlSessionFactory.openSession();
		b = session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}
	
	public static int insertBoard(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("board.insert", b);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("board.getNextNo");
		session.close();
		return no;
	}
	
	public static int updateBoard(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("board.update", b);
		session.commit();
		session.close();
		
		return re;
	}
	
	public static int deleteBoard(int no, String pwd) { //매개변수를 2개 받을 경우 Map을 이용한다.(no와 pwd를 받아와야 함)
		int re = -1;
		HashMap map = new HashMap(); //매개변수의 값 2개를 받아오기 위하여 hashmap 함수를 사용한다.
		map.put("no", no);
		map.put("pwd", pwd);
		SqlSession session = sqlSessionFactory.openSession();
		re = session.delete("board.delete", map);
		session.commit();
		session.close();
		return re;
	}
	
	public static void updateStep(int b_ref, int b_step) {
		SqlSession session = sqlSessionFactory.openSession();
		HashMap map = new HashMap();
		map.put("b_ref", b_ref);
		map.put("b_step", b_step);
		
		session.update("board.updateStep", map);
		session.commit();
		session.close();
	}
	
	public static int getTotalRecord() {
		int cnt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		cnt = session.selectOne("board.totalRecord");
		session.close();
		
		return cnt;
	}
	
}
