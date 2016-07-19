package mypkg.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.Product;
import mypkg.model.ProductDao;

import com.oreilly.servlet.MultipartRequest;
public class ProductInsertController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println( this.getClass() );
		
		//request 영역에 있는 업로드를 위한 객체 multi 정보를 읽어 들인다.
		MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
		
		Product bean = new Product();
		//상품 번호는 시퀀스이므로 구할 수 없다.
		//int num = Integer.parseInt(multi.getParameter("num"));
		String name = multi.getParameter("name");
		String image = multi.getOriginalFileName("image");
		int stock = Integer.parseInt(multi.getParameter("stock"));
		int price = Integer.parseInt(multi.getParameter("price"));
		String category = multi.getParameter("category");
		String pcontent = multi.getParameter("pcontent");
		int point = Integer.parseInt(multi.getParameter("point"));
		
		bean.setCategory(category);
		bean.setPcontent(pcontent);
		bean.setImage(image);
		bean.setName(name);
		//bean.setNum(num);
		bean.setPoint(point);
		bean.setPrice(price);
		bean.setStock(stock);
		
		//DAO 객체를 생성한다.
		ProductDao pdao = new ProductDao();
		
		int cnt = -99999;
		
		//bean 객체를 이용하여 해당 게시물을 추가한다.
		cnt = pdao.InsertData(bean);
		
		//목록 보기로 리다이렉션시킨다.
		new ProductListController().doProcess(request, response);
		
		
	}
}