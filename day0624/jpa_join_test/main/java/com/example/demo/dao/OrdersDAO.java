package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.Orders;

public interface OrdersDAO extends JpaRepository<Orders, Integer> {
	
	//그냥 select 할 경우에는 @query만 작성하면 됨.
	@Query("select nvl(max(orderid), 0) + 1 from Orders") //쿼리문 직접 작성 => 쿼리문에 들어간 테이블 이름은 entity에 들어간 클래스 이름과 일치해야함!!!
	public int getNextNo(); //주문번호를 자동으로 생성해주기 위한 메소드
	
	//insert를 위한 쿼리
	//데이터베이스에 변동이 있는 sql을 사용할 때는 modifyiing, transactional도 같이 작성해줘야 한다.
	@Modifying
	@Query(value = "insert into Orders o(o.orderid, o.custid, o.bookid, o.saleprice, o.orderdate) "
			+ "values(:#{#o.orderid}, :#{#o.customer.custid}, :#{#o.book.bookid}, :#{#o.saleprice}, sysdate)", nativeQuery = true)
	@Transactional
	public void insert(@Param("o") Orders o);
	
	
}
