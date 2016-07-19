package mypkg.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.utility.MyFileUpload;

import com.oreilly.servlet.MultipartRequest;

@WebServlet(
		urlPatterns = { "/cafeCtrl" }, 
		initParams = { 
				@WebInitParam(name = "configFile", value = "/WEB-INF/commandList.properties")
		})
public class FrontController extends HttpServlet implements SuperController{
	private static final long serialVersionUID = 1L;
    
	// <Ŀ�ǵ�, �ڵ鷯�ν��Ͻ�> ���� ���� ����
	//�� ������ ������ String ������ Ŀ�ǵ尡 ��� �´�.
	//�� ������ ���� ���� SuperController��� Ŭ���� Ÿ���� ��ü�̴�.
	private Map<String, SuperController> actionMaps =  new HashMap<String, SuperController>();
	
	public void init() throws ServletException {
		//configFile �ʱ�ȭ �Ķ���� �б�
		String configFile = getInitParameter("configFile");
		
		//��Ʈ���� ���Ͽ� Properties ��ü�� �ֱ�
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			String configFilePath = getServletContext().getRealPath(configFile);
			fis = new FileInputStream(configFilePath);
			prop.load(fis); //�ڹ��� ��Ʈ���� �̿��Ͽ� Properties �÷��ǿ� ��ҵ��� �߰��Ѵ�.
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
			}
		}
		
		Iterator keyIter = prop.keySet().iterator();
		
		//prop ��ü�� Iterator�� �̿��Ͽ� map ��ü�� ���
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			
			//handlerClassName�� Ŭ���� ������ ��� �ִ� ���ڿ�
			String handlerClassName = prop.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				
				//�� ��û ������ ������ Controller���� SuperController�� ���� Ŭ�����̴�. 
				SuperController handlerInstance = 
						(SuperController) handlerClass.newInstance();
				
				//��ü�� ���� �ڹ��� �� ������ �߰��Ѵ�.
				actionMaps.put(command, handlerInstance);
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
		System.out.println( "�� ������ : " + actionMaps.size());
	}	
	
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		//���� ���ε带 �̿��ϱ� ���Ͽ� multipart/form-data�� ����ϰ� �Ǹ�
		//request ��ü�� ���� �̿����� ���Ѵ�.
		//�Ķ���� command�� ì���.
		String command = request.getParameter("command");
		
		ServletContext context = getServletContext();		
		
		if( command == null ){ //���� ���ε带 ���� ���̽��̴�.	
			System.out.println("���� ���ε带 �����մϴ�.");
			String uploadedPath = context.getRealPath("/upload") ; //���� ���ε�� ������ ���
			//String uploadedPath = "c:\\temp\\" ;
			System.out.println( "uploadedPath : " + uploadedPath );
			
			//�׷���, ���� ���ε带 ���� MultipartRequest ��ü�� �����ִ� �޼ҵ带 ȣ���Ѵ�.
			MultipartRequest multi = MyFileUpload.getMultiPartRequest(request, uploadedPath) ;
			if( multi != null ){ //MultipartRequest ��ü�� ��������
				command = multi.getParameter("command") ;
				request.setAttribute("multi", multi ); //request�� ���ε�
				request.setAttribute("uploadedPath", uploadedPath ); //request�� ���ε�
			}
		}
		
		//�ش� Ŀ�ǵ忡 �´� ��Ʈ�ѷ��� ã�Ƽ� �ش� ��Ʈ�ѷ��� doProcess() �޼ҵ带 ȣ���Ѵ�.
		//map ��ü���� �ش� command�� �ش��ϴ� Controller ��ü�� ��� ����.
		SuperController controller = actionMaps.get(command);
		if(controller != null){
			System.out.println( controller.toString() + "�� ȣ���" );
			controller.doProcess( request, response ); //�ش� doProcess() �޼ҵ带 ȣ���Ѵ�. 
		}
		
	}	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//doGet(), doPost() �޼ҵ� �� �� doProcess() �޼ҵ带 ȣ���Ѵ�.
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//doGet(), doPost() �޼ҵ� �� �� doProcess() �޼ҵ带 ȣ���Ѵ�.
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
}