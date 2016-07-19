package mypkg.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.Product;
import mypkg.model.ProductDao;
public class ProductDetailViewController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println( this.getClass() );
		int pid = Integer.parseInt(request.getParameter("pid"));
		ProductDao pdao = new ProductDao();
		Product bean = pdao.SelectDataByPk(pid);
		
		request.setAttribute("bean", bean);
		String url = "/product/prDetailView.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}
}