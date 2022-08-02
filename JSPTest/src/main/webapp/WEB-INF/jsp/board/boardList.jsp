<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
.center {
  text-align: center;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
</head>
<body>
	<h3>product List</h3>
	<table border="1">
		<tr><th>번호</th><th>제목</th><th>작성자</th><th>내용</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="vo" items="${list }">
			<tr><td>${vo.seq }</td><td>${vo.title }
			</td><td>${vo.writer }</td><td>${vo.content }
			<td>${vo.writeDate }</td><td>${vo.visitCNT }</td>
			</tr>
		</c:forEach>
	</table>
<div class="center">
  <div class="pagination">
  <c:if test="${pageInfo.prev }">
  	<a href="boardListPaging.do?pageNum=${pageInfo.startPage - 1 }&amount=${pageInfo.cri.amount }">prev</a>
  </c:if>

  <c:forEach var="num" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
  <a href="boardListPaging.do?pageNum=${num }&amount=${pageInfo.cri.amount }">${num } </a>
  </c:forEach>
  
  <c:if test="${pageInfo.next }">
  	<a href="boardListPaging.do?pageNum=${pageInfo.endPage + 1}&amount=${pageInfo.cri.amount }">next</a>
  </c:if>
 
  </div>
</div>
	
</body>
</html>