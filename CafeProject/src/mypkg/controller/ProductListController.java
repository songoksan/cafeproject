package mypkg.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.Product;
import mypkg.model.ProductDao;
import mypkg.utility.Paging;
public class ProductListController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println( this.getClass() );
	
		ProductDao pdao = new ProductDao();
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");

		int totalCount = pdao.SelectTotalCount();
		System.out.println("ÅäÅ» Ä«¿îÅÍ : " + totalCount);
		
		String contextPath = request.getContextPath();
		String myurl =  contextPath + "/MiniShopCtrl?command=prList";
		String mode = null;
		String keyword = null;
		
		Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, myurl, mode, keyword);
		
		List<Product> lists = pdao.SelectDataList(pageInfo.getBeginRow(), pageInfo.getEndRow());
		
		request.setAttribute("lists", lists);
		request.setAttribute("pagingHtml", pageInfo.getPagingHtml());
		request.setAttribute("pagingStatus", pageInfo.getPagingStatus());
		
		String url = "/product/prList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}