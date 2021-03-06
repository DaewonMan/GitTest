package com.copain.ek.download;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.member.MemberDAO;

@WebServlet("/DownloadDeleteC")
public class DownloadDeleteC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadDeleteC() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MemberDAO.getMdao().loginCheck(request, response)) {
			DownloadDAO.getDrdao().delete(request, response);
		}
		DownloadDAO.getDrdao().getAllDownload(request, response);
		request.setAttribute("contentPage", "download/download.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
