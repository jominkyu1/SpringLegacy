package net.daum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.daum.vo.ProductVO;

@Controller
public class SampleController {
	
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA 매핑주소가 실행됨");
	}
	
	@GetMapping("/doB")
	public void doB() {
		System.out.println("doB 매핑주소가 실행됨");
	}
	
	@RequestMapping("doC")
	public String doC(@RequestParam("msg2") String msg, Model model) {
		model.addAttribute("A", "B");
		
		return "result";
	}
	
	@RequestMapping("/name_price")
	public ModelAndView name_price() {
		ProductVO p = new ProductVO("수박", 20000);
		ModelAndView mv = new ModelAndView("/shop/water_melon"); 
						//	WEB-INF/views/shop/water_melon.jsp
		mv.addObject("p", p);
		return mv;
	}
	
	@RequestMapping("/doE")
	public String doE(RedirectAttributes redirect) {
		redirect.addFlashAttribute("cityName", "부산시");
		//addAttribute는 브라우저 주소창에 노출.
		//flashAttribute는 세션에 저장되고 오직 다음 요청에서만 접근. 임시로 저장
		return "redirect:/doF";
	}
	
	@GetMapping("/doF")
	public void doF(@ModelAttribute("cityName") String cityValue) {
		System.out.println("도시이름:: " + cityValue);
	}
	
	@RequestMapping(value = "/doJson", produces = "application/json")
	public @ResponseBody List<ProductVO> doJson() {
		ProductVO pd = new ProductVO("사과", 50000);
		ProductVO pd2 = new ProductVO("망고", 20000);
		
		List<ProductVO> list = new ArrayList<>();
		list.add(pd);
		list.add(pd2);
		return list;
	}
}
