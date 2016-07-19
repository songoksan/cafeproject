<%@page import="mypkg.model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/top.jsp"%>
<%
	int myoffset = 2;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3 ;
	int formright = twelve - formleft ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-default panel-warning">
			<div class="panel-heading"><h4>회원 목록</h4></div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>아이디</th>
						<th>이름</th>					
						<th>주소</th>
						<th>세부 주소</th>
						<th>이메일</th>
						<th>핸드폰 번호</th>
						<th>포인트</th>
						<th>삭제</th>
						<th>수정</th>
					</tr>
				</thead>
				<c:forEach var="bean" items="${requestScope.lists}">
					<tr>
						<td>${bean.mid}</td>
						<td>
							<a href="<%=MyCtrlCommand%>meDetailView&mid=${bean.mid}">
								${bean.name}
							</a>
						</td>
						<td>${bean.address1}</td>
						<td>${bean.address2}</td>
						<td>${bean.email}</td>
						<td>${bean.phnumber}</td>
						<td>${bean.mpoint}</td>
						<td>
							<a href="<%=MyCtrlCommand%>meDelete&mid=${bean.mid}">
								삭제
							</a>
						</td>
						<td>
							<a href="<%=MyCtrlCommand%>meUpdateForm&mid=${bean.mid}">
								수정
							</a>
						</td>
					</tr>
				</c:forEach>				
			</table>
		</div>
		<div align="center">
			<footer>${requestScope.pagingHtml}</footer>			
		</div>	
	</div>
</body>
</html>