package com.copain.ek.diary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/DiaryDetailC")
public class DiaryDetailC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DiaryDetailC() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().loginCheck(request, response);
		DiaryDAO.getDdao().getDiaryDetail(request, response);
		request.setAttribute("contentPage", "diary/diaryDetail.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
