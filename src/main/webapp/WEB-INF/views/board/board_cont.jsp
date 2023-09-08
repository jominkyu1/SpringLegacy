<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
</body>
</html>