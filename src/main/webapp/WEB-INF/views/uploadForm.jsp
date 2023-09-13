<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form method="post" enctype="multipart/form-data" action="uploadFormAction">
  <%-- 파일첨부기능인 자료실 기능 만들때 주의사항 )
    1. form 태그의 method 속성값은 반드시 post로 지정해야 한다.
    2. form 태그의 enctype 속성값은 반드시 multipart/form-data로 지정해야 한다.
    3. input 태그의 type 속성값은 반드시 file로 지정해야 한다.
  --%>
    <input type="file" name="uploadFile" multiple > <hr>
    <%-- 
        최근 브라우저에서는 multiple속성을 지원하는데 이를 사용하면 하나의 첨부파일뿐 아니라
        다중 첨부파일도 처리가 가능하다. IE 10이상 지원
    --%>
    <input type="submit" value="파일 업로드">
  </form>
</body>
</html>