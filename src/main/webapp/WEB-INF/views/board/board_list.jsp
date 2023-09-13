<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- JSTL Core Taglib 추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>Spring MVC 게시판 목록</title>
</head>
<body>
  <table border="1" style="border-collapse: collapse;">
    <tr>
      <th colspan="7">Spring MVC 게시판 목록</th>
    </tr>
    <tr>
      <td colspan="7" align="right">총 게시글: ${totalCount} 개</td>
    </tr>
    <tr>
      <th>글번호</th>
      <th>글쓴이</th>
      <th>제목</th>
      <th>내용</th>
      <th>조회수</th>
      <th>댓글</th>
      <th>날짜</th>
    </tr>
    <c:if test="${empty blist}">
      <tr>
        <th colspan="6">게시글이 없습니다.</th>
      </tr>
    </c:if>

    <c:forEach items="${blist}" var="list">
      <tr>
        <td>${list.bno}</td>
        <td>${list.writer}</td>
        <td><a href="/controller/board/board_cont?bno=${list.bno}&page=${page}&state=cont">${list.title}</a></td>
        <!-- /controller/board/board_cont?bno=글번호&page=현재페이지?&state=수정or상세보기(cont) 
            GET방식으로 쿼리파라미터(쿼리스트링) 전달. 현재페이지는 책갈피 기능을위해 전달한다.
        -->
        <td><a href="/controller/board/board_cont?bno=${list.bno}&page=${page}&state=cont">${list.content}</a></td>
        <td>${list.viewcnt}</td>
        <td>${list.replycnt}</td>
        <td>${list.regdate}</td>
      </tr>
    </c:forEach>

    <%--페이징 시작 --%>
    
    
    
    <tr>
      <th colspan="7"><c:if test="${page <= 1}">
    [이전]&nbsp; <%-- &nbsp;은 한칸의 빈공백 처리 --%>
        </c:if> <c:if test="${page > 1}">
          <a href="/controller/board/board_list?page=${page-1}">[이전]</a>&nbsp;
   </c:if> <%--쪽번호 출력 부분 --%> <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
          <c:if test="${a == page}">
            <%--현재 쪽번호가 선택된 경우 --%>
      <${a}>
     </c:if>
          <c:if test="${a != page}">
            <%-- 현재 쪽번호가 선택 안된 경우 --%>
            <a href="/controller/board/board_list?page=${a}">[${a}]</a>&nbsp;
          <%-- board_list?page=쪽번호가 웹주소창에 노출되는 get방식으로 전달된다. --%>
          </c:if>
        </c:forEach> <c:if test="${page >= maxpage}">
    [다음]
   </c:if> <c:if test="${page < maxpage}">
          <a href="/controller/board/board_list?page=${page+1}">[다음]</a>
        </c:if></th>
    </tr>




    <%--페이징 끝 --%>
    <tr>
      <td colspan="6" align="right"><input type="button" value="글쓰기"
        onclick="location='/controller/board/board_write?page=${page}'"
      ></td>
    </tr>
  </table>
  <script>
			$msg = "${result}"; //자바스크립트에서 JSP EL문법이나 JSTL 사용 가능

			if ($msg == 'success') {
				alert('Spring MVC 게시물 처리 성공!');
			}
		</script>
</body>
</html>