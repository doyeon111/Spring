package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.BookService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.View_ListOrdersService;
import com.example.demo.service.View_ListOrdersService2;
import com.example.demo.vo.Orders;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Setter;

@Controller
@Setter
public class OrdersController {
	
	//모두 service를 받아옴
	
	@Autowired
	private BookService bs;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private OrdersService os;
	
	@Autowired
	private View_ListOrdersService vls; //뷰 서비스를 여기서 만들어준다.
	
	
	@Autowired
	private View_ListOrdersService2 vls2; //복합키 합쳐준 서비스!
	
	@GetMapping("/insertOrders")
	public void insert(Model model) { //상태유지
		model.addAttribute("c_list", cs.findAll()); //고객목록을 상태유지
		model.addAttribute("b_list", bs.findAll()); //도서목록 상태유지
		model.addAttribute("no", os.getNextNo()); //새로운 주문번호를 상태유지
	}
	
	@PostMapping("/insertOrders")
	@ResponseBody
	public String insertOK(Orders o) {
		os.insert(o);
		return "OK";
	}
	
//	@GetMapping("/listOrders")
//	public void listOrders(Model model) {
//		model.addAttribute("list", os.findAll());
//	}
	
	@GetMapping("/listOrders_view")
	public void listOrders(Model model) {
	model.addAttribute("list", vls.findAll());
	
	}
	
	@GetMapping("/listOrders_view2")
	public void listOrders2(Model model) {
		model.addAttribute("list", vls2.findAll());
	}
}
