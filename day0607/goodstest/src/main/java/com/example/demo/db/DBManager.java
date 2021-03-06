package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.GoodsVO;

public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory=null;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			System.out.println("μμΈλ°μ: " + e.getMessage());
		}
	}
	
	public static List<GoodsVO> listGoods(HashMap map) {
		System.out.println(map);
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("goods.findAll", map);
		session.close();
		
		return list;
	}
	
	
	public static int insertGoods(GoodsVO g) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("goods.insert", g);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("goods.getNextNo");
		session.close();
		return no;
	}
	
	public static GoodsVO findByNo(int no) {
		GoodsVO g = null;
		SqlSession session = sqlSessionFactory.openSession();
		g = session.selectOne("goods.findByNo", no);
		session.close();
		return g;
	}
	
	public static int updateGoods(GoodsVO g) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("goods.update", g);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteGoods(int no) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.delete("goods.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getTotalRecord(String keyword, String searchColumn) {
		HashMap map = new HashMap();
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		int cnt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		cnt = session.selectOne("goods.totalRecord", map);
		session.close();
		
		return cnt;
	}
}
