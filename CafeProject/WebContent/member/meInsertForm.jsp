<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="./../common/top.jsp"%>
<%
	int myoffset = 2 ;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3 ;
	int formright = twelve - formleft ; 
%>
<html>
<head>
	<script type="text/javascript" src="jquery.js"></script>
	<script type="text/javascript" src="jquery.validate.js"></script>
	<style type="text/css">
		.form-group{ margin-bottom : 3px; }
		.err{
			font-size : 10pt;
			color:red;
			font-weight: bolder;
		}
	</style>
	<script type="text/javascript">
		function function1(  ){
			var mid = document.myform.mid.value ;
			if( mid.length == 0 ){
				alert('아이디를 입력해 주세요') ;
				document.myform.mid.focus() ; 
				return false ;
			}
			var url='<%=MyCtrlCommand%>meIdcheck&mid=' + mid ; 
			window.open(url, 'mywin', 'height=150,width=300') ;
		}
		function function2(  ){
			var url='<%=MyCtrlCommand%>meZipcheck' ; 		
			window.open(url, 'mywin', 'height=600,width=720,status=yes,scrollbars=yes,resizable=no') ;			
		}	
		function function3(  ){
			document.myfrom.isCheck.value = false;
		}
		function checkForm(  ){
			var isCheck = document.myform.isCheck.value ;
			//alert( isCheck ) ;
			if( isCheck == 'false' ){
				alert('아이디 중복 체크를 우선 해주세용.') ;
				return false ;
			}			
		}		
	</script>
</head>
<body>
	<div id="main_container" align="center" 
		class="container col-xs-offset-<%=myoffset%> col-lg-offset-<%=myoffset%> col-xs-<%=mywidth%> col-lg-<%=mywidth%>"> 
		<h2>회원 가입 하기</h2>
		<div class="panel panel-primary sub_container">
			<div class="panel-heading" align="left">
				<font color="red"><b>회원 가입</b></font> 페이지입니다.
			</div>
			<div class="panel-body sub_container">
				<form id="myform" name="myform" class="form-horizontal" role="form" action="<%=MyCtrlByForm%>" method="post">
					<input type="hidden" name="command" value="meInsert">
					<input type="text" name="isCheck" value="false">
					<input type="hidden" name="mpoint" value="5">
					<div class="form-group">
						<label for="mid" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">아이디</label>
	        			<div class="col-xs-<%=formright-2%> col-lg-<%=formright-2%>">
	            			<input type="text" name="mid" id="mid" class="form-control" placeholder="아이디" value="${requestScope.bean.mid}"
	            			onkeyup="function3();">
	            			<span class="err">${errid}</span>  
	        			</div>
	        			<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
	            			<input type="button" class="btn btn-info" value="중복 검사" onclick="function1( );"> 
	        			</div>
	        		</div>  
					<div class="form-group">
						<label for="name" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">이름</label>
	        			<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
	            			<input type="text" name="name" id="name" class="form-control" placeholder="이름" value="${requestScope.bean.name}"> 
	            			<span class="err">${errname}</span>  
	        			</div>
	        		</div>
					<div class="form-group">
						<label for="password" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">비밀 번호</label>
	        			<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
	            			<input type="password" name="password" id="password" class="form-control" placeholder="비밀 번호를 넣어 주셔요" value="1234"> 
	        			</div>
	        		</div>
					<div class="form-group">
						<label for="email" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">E-Mail</label>
	        			<div class="col-xs-<%=formright-2%> col-lg-<%=formright-2%>">
	            			<input type="text" name="email" id="email" class="form-control" placeholder="이메일" value="${requestScope.bean.email}"
	            			onkeyup="function3();">
	            			<span class="err">${errid}</span>  
	        			</div>
	        		</div>        		
					<div class="form-group">
						<label for="zipcode" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">우편 번호</label>
	        			<div class="col-xs-<%=formright-2%> col-lg-<%=formright-2%>">
	            			<input type="text" name="fakezipcode" id="fakezipcode" class="form-control" disabled="disabled" placeholder="우편 번호" value="" >
	            			<input type="text" name="zipcode" id="zipcode" value="" > 
	            			<span class="err">${errzipcode}</span>  
	        			</div>
						<div class="col-xs-<%=2%> col-lg-<%=2%>" align="left">
	            			<input type="button" class="btn btn-info" value="우편 번호 찾기" onclick="function2( );"> 
	        			</div>	       			
	        		</div> 
					<div class="form-group">
						<label for="address1" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">주소</label>
	        			<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
	            			<input type="text" name="fakeaddress1" id="fakeaddress1" class="form-control" disabled="disabled" placeholder="주소" value="" >
	            			<input type="text" name="address1" id="address1"  value="">	            			
	        			</div>
	        		</div>
					<div class="form-group">
						<label for="address2" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">세부 주소</label>
	        			<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
	            			<input type="text" name="address2" id="address2" class="form-control" placeholder="세부 주소" value="삼성 아파트"> 
	        				<span class="err">${erraddress2}</span>
	        			</div>
	        		</div>
	        		<div class="form-group">
						<label for="phnumber" class="col-xs-<%=formleft%> col-lg-<%=formleft%> control-label">핸드폰 번호</label>
	        			<div class="col-xs-<%=formright%> col-lg-<%=formright%>">
	            			<input type="text" name="phnumber" id="phnumber" class="form-control" placeholder="핸드폰 번호" value=""> 
	        				<span class="err">${phnumber}</span>
	        			</div>
	        		</div>
					<div class="form-group">
						<div class="col-xs-<%=twelve%> col-lg-<%=twelve%>" align="center">
							<button type="submit" class="btn btn-default" onclick="return checkForm();"><b>회원 가입</b></button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn btn-default">초기화</button>
						</div>
					</div>
				</form>
			</div>
		</div>		
	</div>
	<script type="text/javascript">
		$(document).ready(function() {		
			//alert('dd') ;
			$('#myform').validate({
				rules : {
					id : {
						required : true
						,rangelength : [ 4, 8 ]
					}				
				}, //end rules
				messages : {
					id : {
						required : '아이디는 필수 입력 사항입니다.'
						,rangelength : '아이디는 4자리 이상 8자리 이하입니다.'
					}			
				}
			});
		});
		
	</script>	
</body>
</html>