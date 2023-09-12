package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.daum.service.ReplyService;
import net.daum.vo.ReplyVO;

@RestController
@RequestMapping("/replies")
@Slf4j
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value = "/reply_add", method = RequestMethod.POST)
	public ResponseEntity<String> reply_add(@RequestBody ReplyVO vo){
		/*	@RequestBody 어노테이션은 키, 값쌍의 JSON 형태래로 전송된 데이터를 ReplyVO 객체 타입으로 변경해준다.
		 * */
		ResponseEntity<String> entity=null;
		try {
			replyService.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK); //200 OK
			log.info("Inserted ::: {}", vo.toString());
		}catch(Exception e) {
			e.printStackTrace();
			log.info("Exception ::: BAD_REQUEST Exception");
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	
	//게시판번호에 해당하는 댓글 목록
	@GetMapping(value = "/all/{bno}", produces = "application/json")
	public ResponseEntity<List<ReplyVO>> getReplyList(@PathVariable("bno") int bno){
		/*	@PathVariable("bno")는 매핑주소로부터 게시판번호값을 추출하는 용도로 활용된다.
		 * */
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			
			entity = new ResponseEntity<>(replyService.listReply(bno), HttpStatus.OK);
			log.info("Reply List Returned(size) ::: {}", entity.getBody().size());
		}catch(Exception e) {
			e.printStackTrace();
			log.warn("Reply List Error::: {}", e.getMessage());
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> updateReply(@PathVariable("rno") int rno, 
											  @RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno);
			replyService.editReply(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			log.info("MODIFIED CONTENT RNO::: {}", vo.getRno());
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 삭제
	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delReply(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		try {
			replyService.deleteReply(rno);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			log.info("Delete RNO::: {}", rno);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
