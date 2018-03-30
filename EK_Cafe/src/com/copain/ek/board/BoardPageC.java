package com.copain.ek.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/BoardPageC")
public class BoardPageC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardPageC() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	MemberDAO.getMdao().loginCheck(request, response);
	BoardDAO.getBdao().getAllLists(request, response);
	int p = Integer.parseInt(request.getParameter("p"));
	BoardDAO.getBdao().paging(p, request, response);
	request.setAttribute("contentPage", "board/board.jsp");
	request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
