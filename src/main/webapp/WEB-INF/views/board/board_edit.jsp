<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시글 수정</h2>

<form method="post" action="/controller/board/board_editDone?page=${page}">
  글번호: <input name="bno" id="bno" value="${b.bno}" readonly="readonly" style="background-color: lightgray;"> <br><br>
  제목: <input name="title" id="title" value="${b.title}"> <br><br>
  내용: <textarea name="content" id="content" rows="15" cols="35">${b.content}</textarea> <br><br>
 <button type="submit">수정</button>
</form>
  <input type="button" value="목록으로" onclick="location='/controller/board/board_list?page=${page}'">
  <input type="button" value="취소하기" onclick="history.back();">
</body>
</html>