package com.copain.ek.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.home.DateManager;

@WebServlet("/MemberUpdateC")
public class MemberUpdateC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateC() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MemberDAO.getMdao().loginCheck(request, response)) {
			DateManager.getCurrentYear(request, response);
			request.getRequestDispatcher("jsp/member/update.jsp").forward(request, response);			
		} else {
			request.setAttribute("contentPage", "home.jsp");
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MemberDAO.getMdao().loginCheck(request, response)) {
			MemberDAO.getMdao().update(request, response);
		}
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
