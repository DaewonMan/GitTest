package com.copain.ek.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/ExaminerEnrollC")
public class ExaminerEnrollC extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExaminerEnrollC() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().loginCheck(request, response);
		ExamDAO.getEdao().getAllExaminers(request, response);
		request.setAttribute("contentPage", "exam/enroll.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MemberDAO.getMdao().loginCheck(request, response)) {
			ExamDAO.getEdao().enroll(request, response);
			ExamDAO.getEdao().getAllExaminers(request, response);
			ExamDAO.getEdao().getExaminerConnect(request, response);				
			
		}
		request.setAttribute("contentPage", "exam/exam.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
