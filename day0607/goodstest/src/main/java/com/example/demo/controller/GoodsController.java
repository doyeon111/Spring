package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import lombok.Setter;

@Controller
@Setter
public class GoodsController {
	
	//페이징 처리와 관련된 변수 추가
	public int pageSIZE = 4; 	//한 화면에 보여줄 레코드의 수
	public int totalRecord = 0; //전체 레코드의 수
	public int totalPage = 1;	//전체 페이지의 수
	

	@Autowired
	private GoodsDAO dao;

	@RequestMapping("/listGoods")
	public void list(Model model, @RequestParam(value="pageNUM", defaultValue = "1") int pageNUM, String sortColumn, HttpSession session, String searchColumn, String keyword) {
		System.out.println("정렬컬럼: " + sortColumn);
		System.out.println("검색컬럼: " + searchColumn);
		System.out.println("검색어: " + keyword);
		
		//검색어가 전달되지 않았다면 (null이라면)
		if(keyword == null) {
			//혹시 세션에 검색정보가 있다면 읽어온다.
			//검색어와 검색컬럼을 갖고온다.
			if(session.getAttribute("keyword") != null) {
				keyword = (String)session.getAttribute("keyword");
				searchColumn = (String)session.getAttribute("searchColumn");
				
			}
		}
		
		
		totalRecord = dao.getTotalRecord(keyword,searchColumn);
		totalPage = (int)Math.ceil(totalRecord /(double) pageSIZE); //레코드 나누기 사이즈를 하였을 때 나머지수가 있으면 반올림 해준다.
		
		//현재 페이지에 보여줄 시작레코드와 마지막레코드 계산
		int start = (pageNUM - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		//정렬 컬럼이 전달되지 않으면 혹시 세션에 정렬컬럼이 있는지 가져오기
		//null이 아니면 새로운 정렬하겠다는 것이니 그 값을 이용해야 한다.
		if(sortColumn == null) {
			//혹시 세션에 정렬정보가 있다면 읽어온다.
			if(session.getAttribute("sortColumn") != null) {
				sortColumn = (String)session.getAttribute("sortColumn");
			}
		}
		
		
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("sortColumn", sortColumn);
		map.put("keyword", keyword);
		map.put("searchColumn", searchColumn);
		
		
		model.addAttribute("list", dao.listGoods(map));
//		model.addAttribute("totalPage", totalPage);
		
		String pageStr = "";
		for(int i=1; i<=totalPage; i++) {
			pageStr += "<a href='listGoods?pageNUM=" + i + "'>" + i + "</a>&nbsp;";
		}
		
		model.addAttribute("pageStr", pageStr);
		
		//새로운 정렬 칼럼을 눌렀으면 세션에 상태유지 하기
		if(sortColumn != null) { //sortColumn 이 null이 아니면!
			//상태유지
			session.setAttribute("sortColumn", sortColumn);
		}
		
		//검색어가 null이 아니라면 session에 상태유지한다.
		//검색어 저장할 때에 검색칼럼도 같이 저장한다.
		if(keyword != null) { 
			//상태유지
			session.setAttribute("keyword", keyword);
			session.setAttribute("searchColumn", searchColumn);
		}
	}

	@RequestMapping(value = "/insertGoods", method = RequestMethod.GET)
	public void insertForm(Model model) {
	}

	@RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
	public ModelAndView insertSubmit(GoodsVO g, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		String path = request.getRealPath("images");
		System.out.println(path);

		MultipartFile uploadFile = g.getUploadFile();
		String fname = null;
		fname = uploadFile.getOriginalFilename();

		if (fname != null && !fname.equals("")) {
			g.setFname(fname); // 업로드한 파일이 있을경우 그 파일 이름을 실어준다.
		} else {
			g.setFname("");
		}

//		int no = dao.getNextNo();
//		g.setNo(no);
		int re = dao.insertGoods(g);
		if (re == 1) {
			if (fname != null && !fname.equals("")) {

				try {
					byte[] data = uploadFile.getBytes();
					FileOutputStream fos = new FileOutputStream(path + "/" + fname);
					fos.write(data);
					fos.close();

				} catch (Exception e) {
					System.out.println("예외발생: " + e.getMessage());
				}
			}

		} else {
			mav.addObject("msg", "상품 등록에 실패하였습니다.");
			mav.setViewName("error");
		}

		return mav;
	}

	@RequestMapping("/detailGoods")
	public void detail(Model model, int no) {
		GoodsVO g = dao.findByNo(no);
		model.addAttribute("g", g);

	}

	@RequestMapping(value = "/updateGoods", method = RequestMethod.GET)
	public void updateForm(Model model, int no) {
		model.addAttribute("g", dao.findByNo(no));
	}

	@RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
	public ModelAndView updateSubmit(GoodsVO g, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		
		String oldFname = g.getFname();
		
		//실경로 알아오기
		String path = request.getRealPath("images");

		//업로드한 파일의 정보를 가져온다.
		MultipartFile uploadFile = g.getUploadFile();
		
		String fname = null;
		
		fname = uploadFile.getOriginalFilename(); // 파일이름을 읽어온다.

		if (fname != null && !fname.equals("")) {
			g.setFname(fname); // vo에 fname을 실어준다.

		}

	
		int re = dao.updateGoods(g);
		
		if(re == 1) {
			if(fname != null && !fname.equals("")) {
				try {
					byte []data = uploadFile.getBytes();
					FileOutputStream fos = new FileOutputStream(path + "/" + fname);
					fos.write(data);
					fos.close();
					
					//원래 파일이 있었다면
					if(oldFname != null && !oldFname.equals("")) {
						File file = new File(path + "/" + oldFname);
						file.delete();
					}
					
				} catch(Exception e) {
					System.out.println("예외발생: " + e.getMessage());
				}
			}
		} else {
			mav.addObject("msg", "상품 수정에 실패하였습니다.");
			mav.setViewName("error");
		}

		return mav;
	}
	
	
	
	@RequestMapping("/deleteGoods")
	public ModelAndView delete(int no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
	
						
		//지울 파일이름을 가져오기
		String oldFname =  dao.findByNo(no).getFname();
		
		int re = dao.deleteGoods(no);
		if(re == 1) {
			//만약 게시물 삭제에 성공했다면 해당 파일을 삭제
			if(oldFname != null && !oldFname.equals("")) {
				String path = request.getRealPath("images");
				File file = new File(path + "/" + oldFname);
				file.delete();
			}
			
		} else {
			mav.addObject("msg", "상품 삭제에 실패하였습니다.");
			mav.setViewName("error");
			
		}
	
		
		return mav;
	}
}
