package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;

public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory; //변수 선언하는 문장을 밖에 선언
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
	}
	
	public static List<DeptVO> listDept() {
		List<DeptVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("dept.findAll");
		session.close();
		return list;
	}
	
	
}
