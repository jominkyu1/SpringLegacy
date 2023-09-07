package net.daum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController { //SpringMVC 게시판 컨트롤러

	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//게시판 글쓰기 폼
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public void board_write(Model model, HttpServletRequest request) {
		//리턴타입이 없는 void형은 매핑주소가 뷰페이지 파일명이 된다.
		//경로: WEB-INF/views/board/board_write.jsp
		
		/* 책갈피 */
		int page=1;
		if(request.getParameter("page") != null) { //GET방식으로 전달된 ?page=번호가 있는경우
			page = Integer.parseInt(request.getParameter("page"));
		}
		model.addAttribute("page", page);
	}

	//게시판 저장
	@RequestMapping(value = "/board_write", method = RequestMethod.POST)
	public ModelAndView board_write(@ModelAttribute BoardVO b, RedirectAttributes rttr) {
		boardService.insertBoard(b);
		rttr.addFlashAttribute("result", "success");
		
		return new ModelAndView("redirect:/board/board_list");
		/*	ModelAndView 생성자 인자값에 올 수 있는것
		 * 	1. 뷰페이지 경로
		 * 	2. redirect:/매핑주소
		 * */
	}
	
	@GetMapping("/board_list") //	/board/board_list
	public String board_list(Model listM, 
							 HttpServletRequest request, 
							 @ModelAttribute BoardVO b) {
		/* 페이징 소스 시작 */
		int page=1; //현재 쪽번호
		int limit=10; //한 페이지에 보여지는 게시글개수
		if(request.getParameter("page") != null) { //GET으로 전달된 쪽번호가 있는경우
			page = Integer.parseInt(request.getParameter("page")); //?page=번호를 page에 저장
		}
		b.setStartrow((page-1)*10+1); //시작 행번호
		b.setEndrow(b.getStartrow()+limit-1); //끝 행번호
		/* 페이징 소스 끝*/
		
		int totalCount = boardService.getTotalCount();
		List<BoardVO> blist = boardService.getBoardList(b);
		
		/* 페이징 연산 시작 */
		int maxpage = (int)((double)totalCount/limit+0.95);//총페이지수
	    int startpage = (((int)((double)page/10+0.9))-1)*10+1;//현재 페이지에 보여질 시작페이지
	    int endpage = maxpage;//현재 페이지에 보여질 마지막 페이지
	        
	    if(endpage>startpage+10-1) endpage=startpage+10-1;
	    //마지막페이지>시작페이지+10-1     마지막페이지=시작페이지+10-1
		/* 페이징 연산 끝 */
		
		listM.addAttribute("totalCount", totalCount);
		listM.addAttribute("blist", blist);
		
		listM.addAttribute("startpage", startpage); //시작페이지
		listM.addAttribute("endpage", endpage); //마지막페이지
		listM.addAttribute("maxpage", maxpage); //총 페이지
		listM.addAttribute("page", page); 
		//현재 페이지 -> 페이징에서 내가 마지막으로 본 페이지번호로 바로 이동하기위한 기능(책갈피)
		
		return "board/board_list";
	}
}