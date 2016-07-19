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

		if (bean == null) { // 존재하지 않는 아이디
			url = "/member/meLoginForm.jsp";
			message = "아이디나 비번이 틀렸어요.";
			request.setAttribute("errmsg", message);
		} else {
			if (password.equals(bean.getPassword())) { // 오케이 성공
				url = "/cafemain.jsp";
				message = bean.getMid() + "님 어서옵쇼~~";
				HttpSession session = request.getSession();
				session.setAttribute("loginfo", bean);
				request.setAttribute("errmsg", message);
			} else { // 비번 틀림
				url = "/member/meLoginForm.jsp";
				message = "아이디나 비번이 틀렸어요.";
				request.setAttribute("errmsg", message);
			}

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}