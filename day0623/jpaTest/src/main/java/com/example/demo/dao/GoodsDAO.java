package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.GoodsVO;

@Repository
public interface GoodsDAO extends JpaRepository<GoodsVO, Integer> { //<> 안에 vo이름과, pk의 자료형만 써주면 된다.
	

}


