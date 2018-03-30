package com.copain.ek.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberLoginC")
public class MemberLoginC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberLoginC() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("jsp/member/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().login(request, response);
		MemberDAO.getMdao().loginCheck(request, response);
		// 자동로그인 체크시 하루동안 로그인 유지; 체크 안하면 15분간 로그인 유지
		request.getRequestDispatcher("index.html").forward(request, response);
		
		
	}

}
