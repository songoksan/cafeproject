<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="./../common/top.jsp" %>
<%	
	String meLoginForm = MyCtrlCommand + "meLoginForm"  ;
	String meInsertForm = MyCtrlCommand + "meInsertForm"  ;

%>	
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<a href="<%=meLoginForm%>">로그인</a><br><br><!-- 버튼누를시  commandList.properties를 통해 이동-->
	<a href="<%=meInsertForm%>">회원 가입</a><br><br>
	여긴 홈이야
</body>
</html>