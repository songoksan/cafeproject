package mypkg.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.Member;
import mypkg.model.MemberDao;
public class MemberUpdateFormController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") ;
		MemberDao mdao = new MemberDao() ;
		Member bean = mdao.SelectDataByPk(mid) ;
		
		System.out.println( this.getClass() + bean.toString());
		request.setAttribute( "bean", bean ); 
		
		String url = "/member/meUpdateForm.jsp";  
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
	}
}