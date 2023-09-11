<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<%-- jQuery CDN --%>
<meta charset="UTF-8">
<title>비동기식 AJAX 댓글연습</title>
<style>
  #modDiv{ /*댓글수정폼 UI*/
    width: 300px;
    height: 110px;
    background-color: lightgray;
    position: absolute; /* 절대 위치 */
    top: 50%;
    left: 50%;
    margin-top: -50px;
    margin-left: -150px;
    padding: 10px;
    z-index: 1000; /*높을수록 우선권. position 속성이 absolute, fixed(고정)로 설정된곳에서 사용함*/
  }
</style>
</head>
<body>
  <div id="modDiv" style="display: none;">
    <div class="modal-rno"></div> <%-- 댓글번호 출력부분 --%>
    <div>
      <textarea rows="3" cols="35" id="replytext"></textarea>
    </div>
    <div>
      <button id="replyModBtn">댓글수정</button>
      <button id="replyDelBtn">댓글삭제</button>
      <button id="closeBtn" onclick="modDivClose()">닫기</button>
    </div>
  </div>
  
  <h2>Ajax 댓글 연습페이지</h2>
  <div>
    <div>
      댓글 작성자: <input type="text" name="replyer" id="newReplyWriter"> <br>
    </div>
    <div>
      댓글 내용: <textarea rows="5" cols="20" name="replytext" id="newReplyText"></textarea> <br>
    </div>
    
    <input type="button" id="replyAddBtn" value="댓글 등록"> <br>
  </div>
  
  <hr><br>
  
  <%-- 댓글 목록 --%>
  <ul id="replies"></ul>
  
  <script>
  	$bno=24;
  	
  	getAllList(); // 댓글 목록함수 호출
  	
  	function getAllList(){
  		$.getJSON("/controller/replies/all/" + $bno, function(data){
  			$result="";
        $(data).each(function(){ //jQuery each()
          $result += "<li data-rno='" + this.rno + "' class='replyLi'>"
            + this.rno + " : <span class='com' style='color:blue; font-weight:bold;'>'"
              + this.replytext + "</span> <button type='button'>댓글수정</button></li><br>";
        });
        $('#replies').html($result);
  		});
  	}

    //댓글등록
    $('#replyAddBtn').on('click', function(){
      $replyer = $('#newReplyWriter').val();
      $replytext = $('#newReplyText').val();

      //jQuery 비동기 AJAX함수
      $.ajax({
        type: "POST",
        url: "/controller/replies/reply_add",
        contentType: "application/json; charset=utf-8", //보내는 자료형
        dataType : "text", //서버로부터 받을 자료형식 (json, html, text)
        data : JSON.stringify({
          bno : $bno,
          replyer : $replyer,
          replytext : $replytext
        }),
        
        success: function($data){
          if($data == "SUCCESS"){
            alert('댓글이 등록되었습니다!');
            getAllList();
          }
        }
      });
    });

    //댓글수정화면
    $('#replies').on('click', '.replyLi button', function(){
      var reply = $(this).parent(); //부모요소인 li태그를 구함
      var rno = reply.attr("data-rno"); //li태그의 속성인 data-rno 값 즉, 댓글번호를 구함
      var replytext = reply.children('.com').text(); // 댓글내용 -> li태그의 자식요소 com 클래스의 댓글내용문자만 TEXT함수로 가져옴

      $('.modal-rno').html(rno); // modal-rno 클래스 선택자 영역에 댓글 번호를 변경 적용
      $('#replytext').val(replytext); // textArea 입력박스에 val함수로 댓글내용을 변경 적용
      $('#modDiv').show('slow');
    });

    //댓글 수정화면 닫기
    function modDivClose(){
      $('#modDiv').hide('slow');
    }
    
    //댓글삭제
    $('#replyDelBtn').on('click', function(){
      $.ajax({
        type: "DELETE",
        url: "/replies/" + $bno,
        data: $('.modal-rno').val(), //24?

        success: function(){
          alert('삭제 완료');
          getAllList();
        }
      });
    });


    //댓글수정
  </script>
</body>
</html>