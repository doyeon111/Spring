
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.GoodsService;
import com.example.demo.vo.GoodsVO;

import lombok.Setter;

@Controller
@Setter
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService; //jpa에서는 dao 대신 service으로 담음
	
	
	@GetMapping("/listGoods") 
	public void listGoods(Model model){
		model.addAttribute("list", goodsService.findAll());
	}
	
	
	@PostMapping("/insertGoods")
	public ModelAndView insert(GoodsVO g) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		goodsService.save(g); //서비스에서 save를 가져옴
		return mav;
	}
	
}




