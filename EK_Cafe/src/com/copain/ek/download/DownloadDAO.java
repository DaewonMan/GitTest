package com.copain.ek.download;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copain.ek.home.DBManager;
import com.copain.ek.member.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javafx.scene.chart.PieChart.Data;

public class DownloadDAO {
	
	private ArrayList<Download> Downloads;

	private static final DownloadDAO DRDAO = new DownloadDAO();

	private DownloadDAO() {
		// TODO Auto-generated constructor stub
	}

	public static DownloadDAO getDrdao() {
		return DRDAO;
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.connect();

			int d_no = Integer.parseInt(request.getParameter("d_no"));
			String sql = "delete from ek_cafe_download" + " where d_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, d_no);

			if (pstmt.executeUpdate() == 1) {
				String path = request.getServletContext().getRealPath("etc");
				String d_file = request.getParameter("d_file");
				d_file = URLDecoder.decode(d_file, "euc-kr");
				File oldFile = new File(path + "/" + d_file);
				oldFile.delete();
				request.setAttribute("r", "파일 삭제 성공");
			} else {
				request.setAttribute("r", "파일 삭제 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "파일 삭제 오류");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void getAllDownload(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String sql = "select * from ek_cafe_download " + "order by d_title";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Downloads = new ArrayList<>();
			Download d = null;
			while (rs.next()) {
				d = new Download();
				d.setD_no(rs.getInt("d_no"));
				d.setD_id(rs.getString("d_id"));
				d.setD_title(rs.getString("d_title"));
				d.setD_file(rs.getString("d_file"));
				d.setD_date(rs.getDate("d_date"));
				Downloads.add(d);
			}

			request.setAttribute("Downloads", Downloads);
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("r", "DB서버오류");
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void reg(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.connect();

			String path = request.getServletContext().getRealPath("etc");
			System.out.println(path);
			MultipartRequest mr = new MultipartRequest(request, path, 104857600, "euc-kr",
					new DefaultFileRenamePolicy());
			Member m = (Member) request.getSession().getAttribute("loginMember");

			String d_title = mr.getParameter("d_title");
			String d_id = m.getM_id();

			String d_file = mr.getFilesystemName("d_file");
			d_file = URLEncoder.encode(d_file, "euc-kr");
			d_file = d_file.replace("+", " ");

			String sql = "insert into ek_cafe_download " + "values(ek_cafe_download_seq.nextval, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, d_title);
			pstmt.setString(1, d_id);
			pstmt.setString(3, d_file);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "파일 업로드 성공");
			} else {
				request.setAttribute("r", "파일 업로드 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "파일 업로드 실패");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
}
