<%@page import="mypkg.utility.Paging"%>
<%@page import="mypkg.model.ProductDao"%>
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
		<div class="panel panel-default">
			<div class="panel-heading"><h4>상품 목록</h4></div>
			<table class="table table-condensed table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>상품명</th>
						<th>이미지</th>
						<th>재고</th>
						<th>단가</th>
						<th>카테고리</th>
						<th>상품 설명</th>
						<th>포인트</th>
						<th>삭제</th>
						<th>수정</th>
					</tr>
				</thead>
				<tr>
					<td colspan="12" align="center">
						<form class="form-inline" role="form" name="myform" action="<%=MyCtrlByForm%>" method="get">
							<input type="hidden" name="command" value="prList">
							<div class="form-group">
								<select class="form-control" name="mode" id="mode">
									<option value="all" selected="selected">-- 선택하세요---------
									<option value="name">상품명
									<option value="category">카테고리									
								</select>
							</div>
							<div class="form-group">
								<input type="text" class="form-control btn-xs" name="keyword"
									id="keyword" placeholder="검색 키워드">
							</div>
							<button class="btn btn-default btn-warning" type="submit" onclick="search();">검색</button>
							<button class="btn btn-default btn-warning" type="button" onclick="searchAll();">전체 검색</button>
							<button class="btn btn-default btn-info" type="button"
								onclick="writeForm();">상품 등록</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<p class="form-control-static">${requestScope.pagingStatus}</p>
						</form>
					</td>
				</tr>				
				<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td>${bean.pid}</td>
					<td>
						<a href="<%=MyCtrlCommand%>prDetailView&pid=${bean.pid}&${requestScope.parameters}">
							${bean.name}
						</a>
					</td>
					<td>${bean.image}</td>
					<td>${bean.stock}</td>
					<td>${bean.price}</td>
					<td>${bean.category}</td>
					<td>${bean.pcontent}</td>
					<td>${bean.point}</td>
					<td>
						<a>삭제</a>
					</td>
					<td>
						<a>수정</a>
					</td>
				</tr>
				</c:forEach>			
			</table>
		</div>
		<div align="center">
			<footer>${requestScope.pagingHtml}</footer>
		</div>		
	</div>
	<br><br><br><br>
	<script type="text/javascript">
	   /* 방금 전 선택한 콤보 박스를 그대로 보여 주기 */ 
		$('#mode option').each(function (index){
			if( $(this).val() == '${requestScope.mode}' ){
				$(this).attr('selected', 'selected') ;
			}
		});	
		/* 이전에 넣었던 값 그대로 보존 */
		$('#keyword').val( '${requestScope.keyword}' ) ;		
	</script>	
</body>
</html>