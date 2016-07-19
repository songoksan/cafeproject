package mypkg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypkg.model.Member;
import mypkg.model.MemberDao;

public class MemberLoginController implements SuperController {
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getClass());

		String mid = request.getParameter("mid");
		String password = request.getParameter("password");

		MemberDao mDao = new MemberDao();

		Member bean = mDao.SelectDataByPk(mid);

		String url = null;
		String message = "";

		if (bean == null) { // �������� �ʴ� ���̵�
			url = "/member/meLoginForm.jsp";
			message = "���̵� ����� Ʋ�Ⱦ��.";
			request.setAttribute("errmsg", message);
		} else {
			if (password.equals(bean.getPassword())) { // ������ ����
				url = "/cafemain.jsp";
				message = bean.getMid() + "�� ��ɼ�~~";
				HttpSession session = request.getSession();
				session.setAttribute("loginfo", bean);
				request.setAttribute("errmsg", message);
			} else { // ��� Ʋ��
				url = "/member/meLoginForm.jsp";
				message = "���̵� ����� Ʋ�Ⱦ��.";
				request.setAttribute("errmsg", message);
			}

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}