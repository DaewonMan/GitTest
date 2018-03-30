package com.copain.ek.diary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.copain.ek.home.DBManager;
import com.copain.ek.member.Member;

public class DiaryDAO {
	
	private ArrayList<Diary> diaries;
	
	private static final DiaryDAO DDAO = new DiaryDAO();
	
	public DiaryDAO() {
	}
	
	public static DiaryDAO getDdao() {
		return DDAO;
	}

	public void paging(int page, HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		
		ArrayList<Diary> diaries2 
			= (ArrayList<Diary>) hs.getAttribute("searchDiary");
		
		if(diaries2 == null) {
			diaries2 = diaries;
		}
		
		double cnt = 5;
		int itemSize = diaries2.size();
		int pageCount = (int) Math.ceil(itemSize / cnt);
		request.setAttribute("pageCount", pageCount);

		int start = itemSize - ((int) cnt * (page - 1));
		int end = (page == pageCount) ? -1 : start - ((int) cnt + 1);

		ArrayList<Diary> items2 = new ArrayList<>();

		for (int i = start - 1; i > end; i--) {
			items2.add(diaries2.get(i));
		}

		request.setAttribute("curPageNo", page);
		request.setAttribute("diaries", items2);
	}
	
	public void getDiaryDetail(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 연결
			con = DBManager.connect();

			int d_no = Integer.parseInt(request.getParameter("d_no"));
			
			String sql = "select * from ek_cafe_diary "
					+ "where d_no=? "; 	

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, d_no);
			// 실행
			rs = pstmt.executeQuery();

			// ResultSet -> ArrayList<Student>
			// (jsp에서 디자이너의 작업 처리를 위해 조금더 수고롭게)

			Diary d = null;

			if (rs.next() == true) {
				d = new Diary();
				d.setD_no(rs.getInt("d_no"));
				d.setD_id(rs.getString("d_id"));
				d.setD_title(rs.getString("d_title"));
				d.setD_txt(rs.getString("d_txt"));
				d.setD_date(rs.getDate("d_date"));
				// textarea에 출력할거면
				String d_txt = rs.getString("d_txt");
				d_txt = d_txt.replaceAll("<br>", "\n");
				d.setD_txt(d_txt);

				request.setAttribute("d", d);
			} else {
				
				request.setAttribute("r", "데이터 없음");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "DB서버 오류");

		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	
	public void getAllLists(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String sql = "select * " + 
					"from ek_cafe_diary " + 
					"order by d_date";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			diaries = new ArrayList<>();
			Diary d = null;
			while (rs.next()) {
				d = new Diary();
				d.setD_no(rs.getInt("d_no"));
				d.setD_id(rs.getString("d_id"));
				d.setD_title(rs.getString("d_title"));
				d.setD_txt(rs.getString("d_txt"));
				d.setD_date(rs.getDate("d_date"));
				diaries.add(d);
			}

			if (diaries.size() == 0) {
				request.setAttribute("r", "없음");
				
			}

			HttpSession hs = request.getSession();
			hs.setAttribute("searchDiary", null);
			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("r", "DB서버오류");

			diaries = new ArrayList<>();
			diaries.add(null);
			diaries.add(null);
			diaries.add(null);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("searchDiary", null);
			
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public void write(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.connect();
			
			String d_title = request.getParameter("d_title");
			String d_txt = request.getParameter("d_txt");
			d_txt = d_txt.replace("\n", "<br>");
			
			HttpSession hs = request.getSession();
			Member m = (Member) hs.getAttribute("loginMember");
			String d_id = m.getM_id();
			
			String sql = "insert into ek_cafe_diary values(" 
					+ "ek_cafe_diary_seq.nextval, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, d_id);
			pstmt.setString(2, d_title);
			pstmt.setString(3, d_txt);
			
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "글쓰기 성공");
			} else {
				request.setAttribute("r", "글쓰기 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "글쓰기 실패");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
}
