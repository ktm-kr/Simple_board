<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/board/css/style.css" />
</head>
<body>
<div class="container shadow-lg p-3 mb-5 bg-body rounded mt-5">
	<div class="list">
		<h1 class="mt-5"><b>자 료 실</b></h1>
		<h6>( 전체 글 : ${articleCount } )</h6>
		<table class="table mt-5">
			<tr class="table-light">
				<th>번 호</th>
				<th>파일 내용</th>
				<th>파일 제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조 회</th>
			</tr>
			<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.num}</td>
				<td class="listContent"><a href="${pageContext.request.contextPath}/content?num=${list.num}">${list.content }</a></td>
				<td>파일 명</td>
				<td>${list.writer }</td>
				<td>${list.regdate}</td>
				<td>${list.views }</td>
			</tr>
			</c:forEach>
		</table>
		
			<nav>
			  <ul class="pagination" style="text-align:center;">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/list?pageNum=${(endPage - countPage)}" aria-label="Previous">
				    	<span aria-hidden="true">&laquo;</span>
				    </a>
				</li>
				
			   	<c:forEach var="i" begin="${startPage }" end="${endPage }">
			   		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/list?pageNum=${i}">${i}</a></li>
			   	</c:forEach>
			   	
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/list?pageNum=${startPage + countPage}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			  </ul>
			</nav>
	<div class="btn1 mt-5 mb-5">
		<button type="button" class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/uploadForm'">글쓰기</button>	
	</div>
	</div>
</div>
	
</body>
</html>