<%@page import="mypkg.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String pattern = "###,###" ;
	DecimalFormat df = new DecimalFormat( pattern ) ;
	int twelve = 12 ; //그리드 시스템의 숫자 값
%>  
<%
	String contextPath = request.getContextPath() ; //현재 진행 중인 프로젝트 이름 
	String CommandName = "/cafeCtrl"  ; //요청을 위한 url 패턴 이름
	String MyCtrlByForm = contextPath +  CommandName ; //폼이 있는 경우에 사용된다. 
	String MyCtrlCommand = contextPath +  CommandName + "?command=" ;
	out.print( MyCtrlCommand + "<br>" ); // Model2/MiniShopCtrl?command=
%>
<%
	//가정 : 현재 어플리케이션 이름이 SpringShop이고, 이미지 업로드 폴더가 upload라고 가정하면
	//웹 서버의 이미지를 올릴 경로는 다음과 같이 구한다.
	//변수 url은 여러군데서 사용되고 있어서 사용하지 못함
	String myurl = request.getRequestURL().toString();
	String uri = request.getRequestURI();
	int idx = myurl.indexOf(uri);
	
	//웹 서버의 이미지를 올릴 경로
	String uploadPath = "/upload/";
	String uploadedFolder = myurl.substring(0, idx) + contextPath + uploadPath;
//	out.print( "url=" + myurl + "<br>" );
//	out.print( "uri=" + uri + "<br>" );
//	out.print( "uploadedFolder=" + uploadedFolder + "<br>" );
	
//	String realPath1 = application.getRealPath( uploadPath );
//	out.print( "realPath1=" + realPath1 + "<br>" );
	//밑의것은 이전 방식
	//String realPath1 = request.getRealPath( uploadPath );
	//out.print( "realPath=" + realPath + "<br>" );
%>


<%
	Member loginfo = (Member)session.getAttribute("loginfo") ;	
	String log = "", mem = "" ; 
	int whologin = 0 ; // 0 : 미로그인,  1 : 회원 로그인, 2 : 관리자 로그인
	
	if( loginfo == null ){
		whologin = 0 ; 
		
	}else{ //관리자는 아이디가 admin이라고 가정한다.
		if( loginfo.getMid().equals("admin") ){ 
			whologin = 2 ; 
		}else{
			whologin = 1 ; //일반 사용자 로그인 
		}
	}
	//out.println("whologin : " + whologin);
	session.setAttribute( "whologin", whologin ) ;
%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CafeProject</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<%=contextPath%>/cafemain.jsp">Home</a></li>
					<li>
						<a href="#" class="dropdown-toggle">
							<font color="white">
								<c:if test="${whologin == 0}">
									미로그인
								</c:if>
								<c:if test="${whologin != 0}">
									 ${sessionScope.loginfo.name}(${sessionScope.loginfo.mid})님 
								</c:if>
							</font>
					
						</a>
					</li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">회원<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<c:if test="${whologin == 0}">
									<a href="<%=MyCtrlCommand%>meInsertForm">회원 가입</a>
								</c:if>
								<c:if test="${whologin != 0}">
									<a href="<%=MyCtrlCommand%>meUpdateForm&mid=${sessionScope.loginfo.mid}">회원 정보 수정</a>
								</c:if>
							</li>
							<li><c:if test="${whologin == 0}">
									<a href="<%=MyCtrlCommand%>meLoginForm">로그인</a>
								</c:if> <c:if test="${whologin != 0}">
									<a href="<%=MyCtrlCommand%>meLogout">로그 아웃</a>
								</c:if></li>
							<li>
								<c:if test="${whologin == 1}">
									<a href="#">회원 탈퇴</a>
								</c:if>		
							</li>							
							<li>
								<c:if test="${whologin == 2}">
									<a href="<%=MyCtrlCommand%>meList">회원 목록 보기</a>
								</c:if>
							</li>
							<li>
								<c:if test="${whologin == 1}">
									<a href="#">회원 상세 보기</a>
								</c:if>
							</li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">게시물<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<c:if test="${whologin != 0}">
									<a href="<%=MyCtrlCommand%>boInsertForm">게시물 등록</a>
								</c:if>
							</li>
							<li>
								<c:if test="${whologin != 0}">
									<a href="<%=MyCtrlCommand%>boList">목록 보기</a>
								</c:if>
							</li>
							<li><a href="#">-- 아래 항목들은 어떻게 해야 하나??</a></li>
							<li><a href="#">게시물 수정</a></li>
							<li><a href="#">게시물 삭제</a></li>
							<li><a href="#">상세 보기</a></li>
							<li><a href="#">답글 작성</a></li>							
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">상품<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<c:if test="${whologin == 2}">
									<a href="<%=MyCtrlCommand%>prInsertForm">상품 등록</a>
								</c:if>
							</li>							
							<li><a href="<%=MyCtrlCommand%>prList">목록 보기</a></li>							
							<li><a href="#">-- 아래 항목들은 어떻게 해야 하나??</a></li>
							<li><a href="#">목록 삭제</a></li>
							<li><a href="#">정보 수정</a></li>
							<li><a href="#">상세 보기</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">쇼핑몰<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<c:if test="${whologin != 0}">
									<a href="#">나의 쇼핑 내역</a>
								</c:if>
							</li>
							<li>
								<c:if test="${whologin != 0}">
									<a href="#">장바구니 보기</a>
								</c:if>
							</li>							
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">예시 모음<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=contextPath%>/example/affixExam.jsp">affixExam.jsp</a></li>
							<li><a href="<%=contextPath%>/example/collapseExam.jsp">collapseEx01.jsp</a></li>
							<li><a href="<%=contextPath%>/example/detailViewEx01.jsp">detailViewEx01.jsp</a></li>
							<li><a href="<%=contextPath%>/example/detailViewEx02.jsp">detailViewEx02.jsp</a></li>
							<li><a href="<%=contextPath%>/example/invoice.jsp">invoice.jsp</a></li>
							<li><a href="<%=contextPath%>/example/tabsExam01.jsp">tabExam01.jsp</a></li>
						</ul>
					</li>					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><c:if test="${empty sessionScope.loginfo}">
							<a href="<%=MyCtrlCommand%>meLoginForm">
							<span class="glyphicon glyphicon-log-in"> 로그인 </span>
							</a>
						</c:if> <c:if test="${not empty sessionScope.loginfo}">
							<a href="<%=MyCtrlCommand%>meLogout">로그 아웃<span
								class="glyphicon glyphicon-log-in"> 로그 아웃 </span>
							</a>
						</c:if></li>
				</ul>
			</div>

		</div>
	</nav>
	<c:if test="${not empty requestScope.errmsg}">
		<script type="text/javascript">
			alert('${requestScope.errmsg}');
		</script>
	</c:if>
</body>
</html>