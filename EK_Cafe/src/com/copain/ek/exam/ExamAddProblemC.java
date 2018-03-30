package com.copain.ek.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/ExamAddProblemC")
public class ExamAddProblemC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExamAddProblemC() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().loginCheck(request, response);
		request.setAttribute("contentPage", "exam/addProblem.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MemberDAO.getMdao().loginCheck(request, response)) {
			ExamDAO.getEdao().addProblem(request, response);
		}
		ExamDAO.getEdao().getAllProblems(request, response);
		request.setAttribute("contentPage", "exam/update.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
