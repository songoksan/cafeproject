package mypkg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Controller(��Ʈ�ѷ�) �������̽� : ��� ��Ʈ�ѷ����� �θ� �������̽�
//��û�� ������ �Ǹ� Ư���� ��Ʈ�ѷ�(����)�� �������ֱ� ���� �������̽� 
public interface SuperController {
	public static final String CommandName = "/cafeCtrl" ; 
			
	//��� ����Ͻ� ������ �����ϱ� ���� �߻� �޼ҵ带 �����Ѵ�.
	public void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}