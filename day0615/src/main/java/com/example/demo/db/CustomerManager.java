package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CustomerVO;
import com.example.demo.vo.GoodsVO;

public class CustomerManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생: " + e.getMessage());
		}
	}
	
	public static List<CustomerVO> findAll() {
		SqlSession session = sqlSessionFactory.openSession();
		List<CustomerVO> list = session.selectList("com.sist.customer.findAll"); //GoodsMapper에 있는 이름
		session.close();
		return list;
	}
}
