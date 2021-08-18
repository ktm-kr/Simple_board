<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/board/css/style.css" />
</head>
<body>
<div class="container shadow-lg p-3 mb-5 bg-body rounded mt-5">
<h1 class="mt-5"><b>업로드 파일 정보</b></h1>
	<div class="content border border-secondary rounded mt-5 mb-5">
		<table class="table">
			<tr>
				<th class="table-light">번 호</th>
				<th class="table-light" colspan="3">작성자</th>
				<th class="table-light">조회수</th>
			</tr>
			<tr>
				<td>${num}</td>
				<td colspan="3">${writer }</td>
				<td>${views }</td>
			</tr>
			<tr>
				<th class="table-light descth">설 명</th>
				<td colspan="4" class="desc">${content}</td>
			</tr>
			<tr>
				<th class="table-light">파일</th>
				<td colspan="4"><a href="${pageContext.request.contextPath}/downloadAction">java.war</a></td>
			</tr>
			
		</table>
		<div class="btn1 mt-5">
			<button type="button" class="btn btn-outline-secondary" style="text-align: right;" onclick="location.href='${pageContext.request.contextPath}/updateForm?num=${num}'">수정</button>	
			<button type="button" class="btn btn-outline-secondary" style="text-align: right;" onclick="location.href='${pageContext.request.contextPath}/deleteForm?num=${num}'">삭제</button>	
			<button type="button" class="btn btn-outline-secondary" style="text-align: right;" onclick="location.href='${pageContext.request.contextPath}/list'">목록</button>	
		</div>
	</div>
</div>
	
</body>
</html>