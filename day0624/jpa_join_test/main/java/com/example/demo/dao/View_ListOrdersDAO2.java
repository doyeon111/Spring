package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.OrdersViewId;
import com.example.demo.vo.View_ListOrders2;

@Repository
public interface View_ListOrdersDAO2 extends JpaRepository<View_ListOrders2, OrdersViewId> { //이제는 integer가 아닌 합쳐진 아이디가 주식별자

}
