<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/top.jsp"%>
<%
	int myoffset = 3;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3 ;
	int formright = twelve - formleft ; 
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function cancel() {
		location.href="http://localhost:8899/CafeProject/cafemain.jsp";
	}

</script>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-default panel-primary">
			<div class="panel-heading">로그인 하기</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" action="<%=MyCtrlByForm%>" method="post">
				<input type="hidden" name="command" value="meLogin">
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="mid">아이디</label>
						<div class="col-sm-<%=formright%>">
							<input type="text" class="form-control" name="mid" id="mid"
								data-toggle="tooltip" data-placement="top" 
								title="아이디는 3글자 이상 10글자 이하로 입력해 주세요."
								placeholder="아이디를 넣어 주셔용^^" value="admin">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="password">비밀 번호</label>
						<div class="col-sm-<%=formright%>">
							<input type="password" class="form-control" name="password"
								id="password" placeholder="비밀 번호를 넣어 주셔용^^" value="1234">
						</div>
					</div>
					<div class="form-group">
						<div align="center" class="col-sm-offset-3 col-sm-6">
							<button class="btn btn-default" type="submit">로그인</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" type="button" onclick="cancel()" >취소</button>
							&nbsp;&nbsp;&nbsp;
							<a href="<%=contextPath%>/Insert.me">회원 가입</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
    		$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	
</body>
</html>