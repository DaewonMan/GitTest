package com.copain.ek.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.home.DateManager;

@WebServlet("/MemberJoinC")
public class MemberJoinC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberJoinC() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateManager.getCurrentYear(request, response);
		request.getRequestDispatcher("jsp/member/join.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().join(request, response);
		MemberDAO.getMdao().loginCheck(request, response);
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
