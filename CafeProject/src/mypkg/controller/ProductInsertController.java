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
		
		//request ������ �ִ� ���ε带 ���� ��ü multi ������ �о� ���δ�.
		MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
		
		Product bean = new Product();
		//��ǰ ��ȣ�� �������̹Ƿ� ���� �� ����.
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
		
		//DAO ��ü�� �����Ѵ�.
		ProductDao pdao = new ProductDao();
		
		int cnt = -99999;
		
		//bean ��ü�� �̿��Ͽ� �ش� �Խù��� �߰��Ѵ�.
		cnt = pdao.InsertData(bean);
		
		//��� ����� �����̷��ǽ�Ų��.
		new ProductListController().doProcess(request, response);
		
		
	}
}