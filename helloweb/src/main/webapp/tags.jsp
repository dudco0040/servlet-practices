<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>head1</h1>
	<h2>head2</h2>
	<h3>head3</h3>
	<h4>head4</h4>
	<h5>head5</h5>
	<h6>head6</h6>
	
	<table border = "1", cellspacing = "0" cellpadding = "10">
		<tr>
			<th>글번호</th>
			<td>글제목</td>
			<td>작성자</td>
		</tr>
		<tr> 
			<th>1</th>
			<td>안녕 태그</td>
			<td>둘리</td>		
		</tr>		
		<tr> 
			<th>2</th>
			<td>Hello, Tag</td>
			<td>마이콜</td>
		</tr>
	</table>

	<img src="http://localhost:8080/helloweb/images/poo.jpeg">
	<img src="/helloweb/images/poo.jpeg">
	<img src="images/poo.jpeg">

	<p> 문장을 표시해줌
		개행은 의미가 없음
		띄어쓰기의 역할
	</p>
	
	<a href = "/helloweb/hello?id=leeyc"> hello로 이동 </a>
	
	<a href = "/helloweb/form.jsp"> form으로 이동 </a>

</body>
</html>