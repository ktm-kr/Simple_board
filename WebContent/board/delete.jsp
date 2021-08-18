<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/board/css/style.css" />
</head>
<body>
<div class="upload shadow-lg p-3 mb-5 bg-body rounded mt-5">
<h1 class="mt-5"><b>업로드 파일 삭제하기</b></h1>
	
	<form method="post" action="${pageContext.request.contextPath}/delete">
		<input type="hidden" class="form-control" value="${num}" name="num">
		<div class="upload2 border border-secondary rounded mt-5 mb-5">
		<input type="text" class="form-control" placeholder="비밀번호 (10자 이내)" name="pass" >
		<div class="btn1 mt-5">
			<button type="submit" class="btn btn-outline-secondary" style="text-align: right;">삭제하기</button>	
			<button type="button" class="btn btn-outline-secondary" style="text-align: right;"onclick="location.href='${pageContext.request.contextPath}/content?num=${num }'">취소</button>	
		</div>
	</div>
	</form>
	
	
</div>
	
</body>
</html>