package mypkg.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.Member;
import mypkg.model.MemberDao;
import mypkg.utility.Validator;
public class MemberInsertController implements SuperController, Validator{
	private HttpServletRequest request ;
	private Member bean = null ;
	
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.request = request ; 
		
		bean  = new Member();
		bean.setAddress1( request.getParameter("address1") );	
		bean.setAddress2( request.getParameter("address2") );
		bean.setMid( request.getParameter("mid") );
//		if( request.getParameter("mpoint") != null && request.getParameter("mpoint") != "" ){
//			bean.setMpoint( Integer.parseInt( request.getParameter("mpoint") ));	
//		}
		bean.setName( request.getParameter("name") );
		bean.setPassword( request.getParameter("password") );
		bean.setEmail(request.getParameter("email"));
		bean.setPhnumber(request.getParameter("phnumber"));
		bean.setZipcode( request.getParameter("zipcode") );
		bean.setMpoint(0);
	
		//선택된 갯수 만큼이 배열로 만들어 지는 데 이것을 멤버 변수에 대입한다.
		
		System.out.println( bean );
		
		String url = "";
		if ( this.validate() == false ) {
			url = "/member/meInsertForm.jsp";  
			this.request.setAttribute("bean", bean);
		}else{
			url = "/member/meLoginForm.jsp";
			//DAO 객체를 생성한다.
			MemberDao mdao = new MemberDao();			
			int cnt = -99999 ; 			
			//Bean 객체를 이용하여 해당 게시물을 추가한다.
			cnt = mdao.InsertData(bean) ;
		}
		System.out.println( request.getAttribute("bean") );
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(this.request, response);		
	}


	@Override
	public boolean validate() {
		final String PREFIX = "err" ;
		boolean isCheck = true ; //기본 값으로 true이고, 유효성 검사에 문제가 생기면 false으로 변경
		
		//Member err = new Member() ; 
		if( bean.getMid().length() < 4 || bean.getMid().length() > 10 ){
			this.request.setAttribute( PREFIX + "id", "아이디는 4자리 이상 10자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		if( bean.getName().length() < 2 || bean.getName().length() > 10 ){
			this.request.setAttribute( PREFIX + "name", "이름은 2자리 이상 10자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		if( bean.getPassword().length() < 4 || bean.getPassword().length() > 10 ){
			this.request.setAttribute( PREFIX + "password", "비밀 번호는 4자리 이상 10자리 이하이어야 합니다.");
			isCheck = false  ;
		}
		if( bean.getZipcode() == null || bean.getZipcode() == ""){
			this.request.setAttribute( PREFIX + "zipcode", "우편 번호는 필수 사항입니다.");
			isCheck = false  ;
		}
		if( bean.getAddress1() == null || bean.getAddress1() == "" ){
			this.request.setAttribute( PREFIX + "address1", "주소는 필수 사항입니다.");
			isCheck = false  ;
		}
		return isCheck ;
	}
}