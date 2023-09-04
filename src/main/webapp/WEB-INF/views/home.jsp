<%@ page contentType="text/html; charset=UTF-8" %>
  <%-- UTF-8설정 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<%-- serverTime은 HomeController.java에 설정된 키 이름을 참조해서 값을 가져온다. --%>
<%
  String s1 = (String) request.getAttribute("serverTime");
%>
<p><%=s1%></p>
  
</body>
</html>
