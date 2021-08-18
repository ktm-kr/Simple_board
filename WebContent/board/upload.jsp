<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/board/css/style.css" />
</head>
<body>
<div class="upload shadow-lg p-3 mb-5 bg-body rounded mt-5">
<h1 class="mt-5"><b>파일 올리기</b></h1>
	<form method="post" action="${pageContext.request.contextPath}/upload">
		<div class="upload2 border border-secondary rounded mt-5 mb-5">
		<input class="form-control" type="file" id="formFile"><br>
		<input type="text" class="form-control" placeholder="작성자 이름" name="writer" ><br>
		<input type="password" class="form-control" placeholder="비밀번호 (10자 이내)" name="pass"><br>
		<textarea class="form-control" placeholder="파일 설명(100자 이내)" name="content"></textarea>
		<div class="btn1 mt-5">
			<button type="submit" class="btn btn-outline-secondary" >작성하기</button>	
			<button type="button" class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/list'" >목록</button>	
		</div>
	</div>
	</form>
	
	
</div>
	
</body>
</html>