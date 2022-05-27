package com.sist.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BookVO;

public class DBManager {
	//����� ����
	static SqlSessionFactory sqlSessionFactory = null;
	static {
		
		try {
		String resource = "com/sist/db/sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory =
		  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			System.out.println("���ܹ߻�: " + e.getMessage());
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
		re = session.insert("book.insert", b); //pstmt.excuteUpdate�� ���� ���
		session.commit(); //commit�� �ؾ߸� db�� �ݿ��� �ȴ� !
		session.close();
		
		return re;
	}
	
}
