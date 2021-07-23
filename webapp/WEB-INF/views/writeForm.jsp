<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 등록</h1>
	<p>
		전화번호를 등록하려면 <br> 아래 항목을 기입하고 "등록"버튼을 클릭하세요.
	</p>

<!-- /http://localhost:8088/phonebook5/write?name=정우성&hp=010-0000-0000&company=02-1234-1234 -->

	<form action="/phonebook5/write" method="get">
		<!-- name="hp"에 name은 문법의 name이기 때문에 hp로 하면안된다. -->
		
		이름(name): <input type="text" name="name" value=""><br> 
		핸드폰(hp): <input type="text" name="hp" value=""><br> 
		회사(company):	<input type="text" name="company" value=""><br>

		<button type="submit">등록</button>

	</form>

	<br>
	<a href="http://localhost:8088/phonebook5/list">리스트 바로가기</a>

</body>
</html>