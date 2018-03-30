package com.copain.ek.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/ExamGradeC")
public class ExamGradeC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExamGradeC() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().loginCheck(request, response);
		ExamDAO.getEdao().getAllProblems(request, response);
		ExamDAO.getEdao().checkSolution(request, response);
		
		request.setAttribute("contentPage", "exam/examResult.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
