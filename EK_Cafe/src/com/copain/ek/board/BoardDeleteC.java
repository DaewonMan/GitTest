package com.copain.ek.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/BoardDeleteC")
public class BoardDeleteC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardDeleteC() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	if(MemberDAO.getMdao().loginCheck(request, response)) {
			BoardDAO.getBdao().delete(request, response);
			BoardDAO.getBdao().deleteReplWithb_no(request, response);
	}
			
		BoardDAO.getBdao().getAllLists(request, response);
		BoardDAO.getBdao().paging(1, request, response);
		request.setAttribute("contentPage", "board/board.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
