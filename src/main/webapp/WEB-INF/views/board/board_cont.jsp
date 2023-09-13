<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC 게시판 내용보기와 AJAX댓글</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script> <!-- jQuery CDN -->
</head>
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
<body>
<h2 style="color:red;">
  <c:if test="${!empty done}">${done}</c:if>
</h2>
제목: <input id="title" name="title" value="${b.title}" readonly="readonly"> <br><br>
내용: <textArea id="content" name="content" rows="15" cols="25" readonly="readonly">${b.content}</textArea> <br><br>
<button onclick="location='/controller/board/board_list?page=${page}'">목록으로</button>
<button onclick="location='?bno=${b.bno}&page=${page}&state=edit'">수정</button>
<input type="button" onclick="if(confirm('정말로 삭제할까요?') == true){
                    location='/controller/board/board_del?bno=${b.bno}&page=${page}'}else{return;}" value="삭제">
                    
                    
<br>
<hr>
<br>

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
  
  <h2>[Property접근: 댓글 갯수: ${b.replycnt} 개]</h2><br>
  <h2 id="replyCount"></h2>
  <%-- 댓글 목록 --%>
  <ul id="replies"></ul>
  
  <script>
    $bno=${b.bno}; // JavaSript에서 JSTL, EL 사용 가능함
    
    getAllList(); // 댓글 목록함수 호출

    function getAllList(){
      $.getJSON("/controller/replies/all/" + $bno, function(data){
        $('#replyCount').html("AJAX접근: 댓글: " + data.length + "개"); //댓글수 출력
        $result="";
        $(data).each(function(){ //jQuery each()
          $date = new Date(this.regdate);
          $result += "<li data-rno='" + this.rno + "' class='replyLi'>"
            + this.rno + " : <span class='com' style='color:blue; font-weight:bold;'>"
              + this.replytext + "</span> <button type='button'>댓글수정</button></li>작성일자: <b>" + $date.toLocaleString() + "</b><br><hr>";
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
            //location.reload(); //자바스크립트에서 새로고침(단축키 F5)
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
      var rno = $('.modal-rno').text();

      $.ajax({
        type: "DELETE",
        url: "/controller/replies/" + rno,
        
        success: function(){
          alert('삭제 완료');
          modDivClose();
          getAllList();
        }
      });
    });


    //댓글수정
    $('#replyModBtn').on('click', function(){
      var rno = $('.modal-rno').text();
      var replytext = $('#replytext').val();

      $.ajax({
        type: "PUT",
        url: "/controller/replies/" + rno,
        contentType: "application/json; charset=utf-8",
        dataType : "text",
        data : JSON.stringify({
          replytext : replytext
        }),
        
        success: function(){
          alert('수정 완료');
          modDivClose();
          getAllList();
        }
      });
    });

  </script>
</body>
</html>