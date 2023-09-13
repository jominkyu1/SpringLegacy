package net.daum.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController { //파일첨부 즉 이진파일 업로드 스프링 컨트롤러 클래스
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		//return타입이 없는 메소드면 매핑주소가 뷰페이지 파일명이 된다.
		//viewResolver경로 -> /WEB-INF/views/uploadForm.jsp
	}
	
	//동기식 이진파일 업로드
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile,
								 HttpServletRequest request) {
		/*	MultipartFile 스프링API를 사용해서 업로드 되는 파일 데이터를 처리
		 * 	다중 업로드파일은 배열로 받는다. 매개변수명 uploadFile은
		 * 	<input type="file" name="uploadFile">의 name속성명과 같아야한다.
		 * */
		String uploadFolder = request.getRealPath("/") + "resources/upload";
											//첨부파일 업로드 실제경로
		log.info("첨부파일 업로드 실제경로::: {}", uploadFolder);
		
		for(MultipartFile multi : uploadFile) {
			System.out.println("===========");
			System.out.println("getOriginalFilename() ::: " + multi.getOriginalFilename());
			System.out.println("getSize() ::: " + multi.getSize());
			
			File saveFile = new File(uploadFolder, multi.getOriginalFilename());
			try {
				multi.transferTo(saveFile); //업로드 폴더에 첨부파일 실제 업로드
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//for
		
		/*	문제) Tomcat 서버에의해서 실제 upload폴더에 첨부된 파일이 업로드 되게 만들어 보자.
		 * 		  한개 첨부파일뿐아니라 다중 파일 첨부확인도 해보자.
		 * */
	}
	
	//비동기식 aJax 이진파일 업로드
	@GetMapping("/uploadAjax")
	public ModelAndView uploadaJax() {
		return new ModelAndView("uploadAjaxForm");
	}
}
