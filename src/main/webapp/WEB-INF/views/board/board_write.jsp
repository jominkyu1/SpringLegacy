<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function check(){
		if($.trim($('#writer').val()) == ''){
			alert('글쓴이를 입력하세요');
			$('#writer').val('').focus();
			return false;
		}
		
		if($.trim($('#title').val()) == ''){
			alert('글제목을 입력하세요');
			$('#title').val('').focus();
			return false;
		}
		
		if($.trim($('#content').val()) == ''){
			alert('글내용을 입력하세요');
			$('#content').val('').focus();
			return false;
		}
	}
</script>
</head>
<body>
  <form method="post" onsubmit="return check()">
    <h2>스프링 MVC 게시판 입력 폼</h2>
    글쓴이: <input name="writer" id="writer"> <br><br>
    글제목: <input name="title" id="title"> <br><br>
    글내용: <textarea name="content" id="content" rows="10" cols="36"></textarea>
    <hr>
    <input type="submit" value="입력">
    <input type="reset" value="Reset" onclick="$('#writer').focus();">
    <input type="button" value="목록" onclick="location='/controller/board/board_list?page=${page}'">
  </form>
</body>
</html>