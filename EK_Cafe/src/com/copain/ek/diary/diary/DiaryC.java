package com.copain.ek.diary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.board.BoardDAO;
import com.copain.ek.member.MemberDAO;

@WebServlet("/DiaryC")
public class DiaryC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DiaryC() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO.getMdao().loginCheck(request, response);
		DiaryDAO.getDdao().getAllLists(request, response);
		DiaryDAO.getDdao().paging(1, request, response);
		
		request.setAttribute("contentPage", "diary/diary.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
