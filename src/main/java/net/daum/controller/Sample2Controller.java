package net.daum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.vo.SampleVO;

@RestController 
//Spring 4.0이후부터 @RestController 지원:: 뷰페이지 없이 Rest방식의 데이터처리가능
//만들어진 데이터는 키:값쌍의 json, XML, 문자열 등
public class Sample2Controller {
	
	@RequestMapping("/rest_start")
	public String rest_start() {
		return "REST API begin";
	}
	
	@RequestMapping(value = "/sendVO", produces="application/json")
	public SampleVO sendVO() {
		//메서드 리턴타입이 SampleVO면 해당클래스의 필드변수명이 json데이터의 키 이름이 된다.
		SampleVO vo = new SampleVO();
		vo.setMno(7);
		vo.setFirstName("홍");
		vo.setLastName("박사");
		
		return vo;
	}
	
	//Collection List타입의 JSON 데이터 생성
	@RequestMapping(value="/sendList", produces = "application/json")
	public List<SampleVO> sendList(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("강");
			vo.setLastName("호동");
			
			list.add(vo);
		}
		
		return list;
	}
	
	//키, 값쌍의 JSON 데이터
	@RequestMapping(value="sendMap", produces = "application/json")
	public Map<Integer, SampleVO> sendMap(){
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i=0; i<5; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("홍");
			vo.setLastName("길동");
			
			map.put(i, vo);
		}

		return map;
	}
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/*	@RestController는 별도의 JSP파일을 만들지 않고 REST API 서비스를 실행하기때문에
		 * 	결과 데이터에 대한 예외 오류가 많이 발생한다.
		 * 	이 경우 ResponseEntity타입을 사용하여 개발자에게 문제가되는 HTTP 에러상태코드인 
		 * 	404(파일없음), 500(오타에러) 등의 코드를 데이터와 함께 리턴할 수 있다.
		 * 	400 -> 나쁜 상태코드(BAD_REQUEST)
		 * */
	}
	
	
	// 404NotFound와 정상적인 값을 동시에 전송
	@RequestMapping(value="/sendErrorList", produces = "application/json")
	public ResponseEntity<List<SampleVO>> sendErrorList(){
		List<SampleVO> list = new ArrayList<>();
		SampleVO vo1 = new SampleVO();
		
		vo1.setMno(1);
		vo1.setFirstName("AA");
		vo1.setLastName("BB");
		
		list.add(vo1);
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
	}
}