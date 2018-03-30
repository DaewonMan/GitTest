package com.copain.ek.diary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/DiaryWriteC")
public class DiaryWriteC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DiaryWriteC() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().loginCheck(request, response);
	
		request.setAttribute("contentPage", "diary/diaryWrite.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		MemberDAO.getMdao().loginCheck(request, response);
		DiaryDAO.getDdao().write(request, response);
		DiaryDAO.getDdao().getAllLists(request, response);
		DiaryDAO.getDdao().paging(1, request, response);
		
		request.setAttribute("contentPage", "diary/diary.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	
	}

}
