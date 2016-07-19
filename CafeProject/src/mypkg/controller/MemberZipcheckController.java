package mypkg.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.MemberDao;
import mypkg.model.Zipcode;
public class MemberZipcheckController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dong = request.getParameter("dong") ;
		List<Zipcode>  lists = null ;
		MemberDao mdao = new MemberDao() ;
		if ( dong != null ) {
			lists = mdao.SelectDataZipcode(dong) ;
			System.out.println( "µ¿³× ¼ö: " + lists.size() );
		}		 
		request.setAttribute( "lists", lists );
		String url = "/member/zipCheck.jsp";  
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}