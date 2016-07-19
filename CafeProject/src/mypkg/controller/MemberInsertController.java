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
	
		//���õ� ���� ��ŭ�� �迭�� ����� ���� �� �̰��� ��� ������ �����Ѵ�.
		
		System.out.println( bean );
		
		String url = "";
		if ( this.validate() == false ) {
			url = "/member/meInsertForm.jsp";  
			this.request.setAttribute("bean", bean);
		}else{
			url = "/member/meLoginForm.jsp";
			//DAO ��ü�� �����Ѵ�.
			MemberDao mdao = new MemberDao();			
			int cnt = -99999 ; 			
			//Bean ��ü�� �̿��Ͽ� �ش� �Խù��� �߰��Ѵ�.
			cnt = mdao.InsertData(bean) ;
		}
		System.out.println( request.getAttribute("bean") );
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(this.request, response);		
	}


	@Override
	public boolean validate() {
		final String PREFIX = "err" ;
		boolean isCheck = true ; //�⺻ ������ true�̰�, ��ȿ�� �˻翡 ������ ����� false���� ����
		
		//Member err = new Member() ; 
		if( bean.getMid().length() < 4 || bean.getMid().length() > 10 ){
			this.request.setAttribute( PREFIX + "id", "���̵�� 4�ڸ� �̻� 10�ڸ� �����̾�� �մϴ�.");
			isCheck = false  ;
		}
		if( bean.getName().length() < 2 || bean.getName().length() > 10 ){
			this.request.setAttribute( PREFIX + "name", "�̸��� 2�ڸ� �̻� 10�ڸ� �����̾�� �մϴ�.");
			isCheck = false  ;
		}
		if( bean.getPassword().length() < 4 || bean.getPassword().length() > 10 ){
			this.request.setAttribute( PREFIX + "password", "��� ��ȣ�� 4�ڸ� �̻� 10�ڸ� �����̾�� �մϴ�.");
			isCheck = false  ;
		}
		if( bean.getZipcode() == null || bean.getZipcode() == ""){
			this.request.setAttribute( PREFIX + "zipcode", "���� ��ȣ�� �ʼ� �����Դϴ�.");
			isCheck = false  ;
		}
		if( bean.getAddress1() == null || bean.getAddress1() == "" ){
			this.request.setAttribute( PREFIX + "address1", "�ּҴ� �ʼ� �����Դϴ�.");
			isCheck = false  ;
		}
		return isCheck ;
	}
}