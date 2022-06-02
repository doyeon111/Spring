package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	//페이징 처리와 관련된 변수 추가
	public int pageSIZE = 10; 	//한 화면에 보여줄 레코드의 수
	public int totalRecord = 0; //전체 레코드의 수
	public int totalPage = 1;	//전체 페이지의 수
	
	
	@Autowired
	private BoardDAO dao;

	
	@RequestMapping("/listBoard")
	public void list(Model model, @RequestParam(value="pageNUM", defaultValue = "1") int pageNUM) { //pageNUM의 기본 페이지는 1페이지로 설정
		System.out.println("pageNUM: " + pageNUM);
		totalRecord = dao.getTotalRecord();
		totalPage = (int)Math.ceil(totalRecord /(double) pageSIZE); //레코드 나누기 사이즈를 하였을 때 나머지수가 있으면 반올림 해준다.
		
		int start = (pageNUM - 1) * pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		
		model.addAttribute("list", dao.listBoard(map));
		model.addAttribute("totalPage", totalPage);
		
	}
	
	@RequestMapping("/detailBoard")
	public void detail(Model model, int no) {
		BoardVO b =dao.findByNo(no);
		model.addAttribute("b", b);
		
		//이미지 파일인지 아닌지 상태유지
		String fname = b.getFname();
		
		//첨부파일이 있었다면
		if(fname != null && !fname.equals("")) {
			//파일 이름의 끝자리가 png, jpg, gif로 끝나면 img로 상태유지
			if(fname.endsWith(".png") || fname.endsWith(".jpg") || fname.endsWith(".gif")) { 
				model.addAttribute("img", "yes");
			}
		}
		
		
	}
	
	
	
	@RequestMapping(value = "/insertBoard", method=RequestMethod.GET)
	public void insertForm(Model model, @RequestParam(value= "no", defaultValue = "0") int no) {
		//상태유지
		model.addAttribute("no", no); //답글달기 할 시 부모의 번호가 상태유지가 되고, 새 글을 작성할 시 0으로 유지가 된다.
		String title = "새 글 작성";
		
		if(no != 0) { //만약 no가 0이 아니라면(즉, 답글이라면)
			title = "답글 작성";
			
		}
		model.addAttribute("title", title);
		
	}
	
	@RequestMapping(value = "/insertBoard", method=RequestMethod.POST)
	public ModelAndView insertSubmit(BoardVO b, HttpServletRequest request) {
		
		//업로드한 실경로 알아오기
		String path = request.getRealPath("upload");
		System.out.println(path); 
		
		MultipartFile uploadFile = b.getUploadFile();
		String fname = null;
		fname = uploadFile.getOriginalFilename(); //파일이름을 읽어온다.";
		
		if(fname != null && !fname.equals("")) { //업로드한 파일이 있다면
			//업로드한 파일이 있어야지만 여기로 오게끔
			System.out.println("업로드한 파일이 있어요!");
			b.setFname(fname); //업로드한 파일이 있을경우 그 파일 이름을 실어준다.
			
			
			try {
				byte []data = uploadFile.getBytes(); // 업로드한 파일의 내용을 가지고온다.
				FileOutputStream fos = new FileOutputStream(path + "/" + fname); //출력하기 위한 용도
				fos.write(data); //데이터에 있는 내용을 출력
				fos.close();
				
			
			} catch(Exception e) {
				System.out.println("예외발생: " + e.getMessage());
			}
		} else {
			System.out.println("업로드한 파일이 없어요!");
			b.setFname(""); //파일 이름이 없을 경우 ""
		}
		
		
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		
		//일단은 새글이라고 본다.
		int no = dao.getNextNo();
		int b_ref = no;
		int b_step = 0;
		int b_level = 0;
		
		int pno = b.getNo(); //부모글의 글번호가 온다.
		
		if(pno != 0) { //너가 답글이니?
			//부모글을 가져옴.
			BoardVO p = dao.findByNo(pno);
			b_ref = p.getB_ref();
			b_step = p.getB_step();
			b_level = p.getB_level();
			
			//updateStep 호출
			dao.updateStep(b_ref, b_step);
			
			//이미 달려있는 답글에 1씩 증가
			b_step++;
			b_level++;
		}
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
		

		int re = dao.insertBoard(b);
		if(re != 1) {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
			mav.setViewName("error");
		} 
		
		return mav;
	}
	
	
	
	
	@RequestMapping(value="/updateBoard", method=RequestMethod.GET)
	public void updateForm(Model model, int no) {
		model.addAttribute("b", dao.findByNo(no));
	}
	
	@RequestMapping(value="/updateBoard", method=RequestMethod.POST)
	public ModelAndView updateSubmit(BoardVO b, HttpServletRequest request) {
		
		//업로드한 실경로 알아오기
		String path = request.getRealPath("upload");
				
		String oldFname = b.getFname(); //원래의 파일이름
		String fname = null; //업로드할 파일을 받아오는 변수
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename(); //파일이름을 읽어온다.
		
		if(fname != null && !fname.equals("")) {
			b.setFname(fname); //업로드한 파일이름으로 변경
			
			try {
				byte []data = uploadFile.getBytes(); // 업로드한 파일의 내용을 가지고온다.
				FileOutputStream fos = new FileOutputStream(path + "/" + fname); //파일의 경로를 찾아 출력하기 위한 용도
				fos.write(data); //파일에 있는 내용을 출력
				fos.close();
						
					
			} catch(Exception e) {
				System.out.println("예외발생: " + e.getMessage());
			}
		}
		
		
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		int re = dao.updateBoard(b);
		
		if(re != 1) {
			mav.addObject("msg", "게시물 수정에 실패하였습니다.");
			mav.setViewName("error");
			
			//수정에 실패했다면, 그리고 파일을 업로드했다면
			if(fname != null && !fname.equals("")) { //업로드한 파일을 삭제
				File file = new File(path + "/" + oldFname); //파일의 경로를 찾은 후
				file.delete(); //원래 파일을 삭제
			}
		} else {
			//파일을 수정하고 원래 게시물에 파일이 있었다면
			if(fname != null && !fname.equals("") && oldFname != null && !oldFname.equals("")) { 
				File file = new File(path + "/" + oldFname); //파일의 경로를 찾은 후
				file.delete(); //원래 파일을 삭제
			}
		}
		return mav;
	}
	
	
	
	
	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.GET)
	public void deleteForm(Model model, int no) {
		model.addAttribute("no", no);
	}
	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.POST)
	public ModelAndView deleteSubmit(int no, String pwd, HttpServletRequest request) {
	

		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		//실제경로를 알아오기
		String path = request.getRealPath("upload");
				
		//지울 파일이름을 가져오기
		String oldFname =  dao.findByNo(no).getFname();
		
		//만약 게시물 삭제에 성공했다면 해당 파일을 삭제하도록 추가
		int re = dao.deleteBoard(no, pwd);
		if(re != 1) {
			mav.addObject("msg", "게시물 삭제에 실패하였습니다.");
			mav.setViewName("error");
		} else {
			//만약 게시물 삭제에 성공했다면 해당 파일을 삭제
			if(oldFname != null && !oldFname.equals("")) {
				File file = new File(path + "/" + oldFname);
				file.delete();
			}
			
		}
		return mav;
	}
	
	
	
	
}
