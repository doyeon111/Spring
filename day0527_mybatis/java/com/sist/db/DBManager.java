package com.sist.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BookVO;

public class DBManager {
	//멤버로 선언
	static SqlSessionFactory sqlSessionFactory = null;
	static {
		
		try {
		String resource = "com/sist/db/sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory =
		  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
	}
	
	public static List<BookVO> listBook() {
		List<BookVO> list = null;
		
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("book.findAll");
		session.close();
		
		return list;
	}
	
	public static int insertBook(BookVO b) {
		int re = -1;
		
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("book.insert", b); //pstmt.excuteUpdate와 같은 기능
		session.commit(); //commit을 해야만 db에 반영이 된다 !
		session.close();
		
		return re;
	}
	
}
