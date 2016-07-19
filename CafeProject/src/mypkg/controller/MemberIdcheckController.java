package mypkg.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.Member;
import mypkg.model.MemberDao;
public class MemberIdcheckController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid") ;
		
		MemberDao mdao = new MemberDao() ;
		Member bean = mdao.SelectDataByPk(mid) ;
		if ( bean == null ) { 
			request.setAttribute("message", mid + "은(는) 사용 가능한 아이디입니다.") ;
			request.setAttribute("isCheck", true) ;
		}else{
			request.setAttribute("message", mid + "은(는) 이미 사용중인 아이디입니다.") ;
			request.setAttribute("isCheck", false) ;
		}
		String url = "/member/idCheck.jsp";  
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}