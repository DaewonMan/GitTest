package com.copain.ek.board;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.copain.ek.home.DBManager;
import com.copain.ek.member.Member;


public class BoardDAO {
	private ArrayList<BoardList> lists;

	private static final BoardDAO BDAO = new BoardDAO();
	
	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static BoardDAO getBdao() {
		return BDAO;
	}


	public void deleteReplWithb_no(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			
			int r_b_no = Integer.parseInt(request.getParameter("b_no"));
			
			String sql = "delete from ek_cafe_repl "
						+ "where r_b_no=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, r_b_no);

			if (pstmt.executeUpdate() >= 1) {
				System.out.println("리플 있었음");
			} else {
				System.out.println("리플 없었음");
			}

		} catch (Exception e) {
			System.out.println("리플지우다 문제발생");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void deleteRepl(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			
			int r_no = Integer.parseInt(request.getParameter("r_no"));
			
			String sql = "delete from ek_cafe_repl "
						+ "where r_no=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, r_no);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "리플삭제 성공!");
			} else {
				request.setAttribute("r", "리플삭제 실패");
			}

		} catch (Exception e) {
			request.setAttribute("r", "실패!!");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	private ArrayList<BoardRepl> getRepls(int b_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String sql = " select * " +
					"from ek_cafe_repl " +
					"where r_b_no=? " +
					"order by r_date ";


			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_no);
			// 실행
			rs = pstmt.executeQuery();

			ArrayList<BoardRepl> repls = new ArrayList<>();
			BoardRepl b = null;

			while (rs.next() == true) {
				b = new BoardRepl();
				b.setR_no(rs.getInt("r_no"));
				b.setR_b_no(b_no);
				b.setR_id(rs.getString("r_id"));
				b.setR_txt(rs.getString("r_txt"));
				b.setR_date(rs.getDate("r_date"));
				repls.add(b);
			}
			return repls;
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return null;
	
	}
	
	public void writeReply(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 연결
			con = DBManager.connect();

			int r_no = Integer.parseInt(request.getParameter("r_b_no"));
			
			String r_txt = request.getParameter("r_txt");
			r_txt = r_txt.replace(" ", "&nbsp;");
			
			
			HttpSession hs = request.getSession();
			Member m = (Member)hs.getAttribute("loginMember");
			String r_id = m.getM_id();
			
			
			String sql = "insert into ek_cafe_repl values("
						+ "ek_cafe_repl_seq.nextval,?,?,?, sysdate)";


			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, r_no);
			pstmt.setString(2, r_id);
			pstmt.setString(3, r_txt);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "글쓰기 성공!");
			} else {
				request.setAttribute("r", "글쓰기 실패");
			}

		} catch (Exception e) {
			request.setAttribute("r", "실패!!");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			
			int b_no = Integer.parseInt(request.getParameter("b_no"));
			
			String sql = "delete from ek_cafe_board "
						+ "where b_no=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, b_no);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "삭제 성공!");
			} else {
				request.setAttribute("r", "삭제 실패");
			}

		} catch (Exception e) {
			request.setAttribute("r", "실패!!");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
		
	public void update(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			String b_txt = request.getParameter("b_txt");
			b_txt = b_txt.replaceAll("\n", "<br>");
			b_txt = b_txt.replace(" ", "&nbsp;");
			
			int b_no = Integer.parseInt(request.getParameter("b_no"));
			
			String sql = "update ek_cafe_board "
						+ "set b_txt =?, b_date = sysdate "
						+ "where b_no=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, b_txt);
			pstmt.setInt(2, b_no);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "글쓰기 성공!");
			} else {
				request.setAttribute("r", "글쓰기 실패");
			}

		} catch (Exception e) {
			request.setAttribute("r", "실패!!");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void getAllLists(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String sql = "select * " + 
					"from ek_cafe_member, ek_cafe_board " + 
					"where b_id = m_id " + 
					"order by b_date";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			lists = new ArrayList<>();
			BoardList b = null;
			while (rs.next()) {
				b = new BoardList();
				b.setB_no(rs.getInt("b_no"));
				b.setB_id(rs.getString("b_id"));
				//System.out.println(b.getB_id());
				b.setB_txt(rs.getString("b_txt"));
				b.setB_date(rs.getDate("b_date"));
				b.setM_img(rs.getString("m_img"));
				b.setB_repls(getRepls(b.getB_no()));
				
				lists.add(b);
			}

			if (lists.size() == 0) {
				request.setAttribute("r", "없음");
				lists.add(null);				lists.add(null);
				lists.add(null);				lists.add(null);
				lists.add(null);
			}

			HttpSession hs = request.getSession();
			hs.setAttribute("searchBoard", null);
			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("r", "DB서버오류");

			lists = new ArrayList<>();
			lists.add(null);
			lists.add(null);
			lists.add(null);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("searchBoard", null);
			
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public void paging(int page, HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		
		ArrayList<BoardList> lists2 
			= (ArrayList<BoardList>) hs.getAttribute("searchBoard");
		
		if(lists2 == null) {
			lists2 = lists;
		}
		
		double cnt = 5;
		int itemSize = lists2.size();
		int pageCount = (int) Math.ceil(itemSize / cnt);
		request.setAttribute("pageCount", pageCount);

		int start = itemSize - ((int) cnt * (page - 1));
		int end = (page == pageCount) ? -1 : start - ((int) cnt + 1);

		ArrayList<BoardList> items2 = new ArrayList<>();

		for (int i = start - 1; i > end; i--) {
			items2.add(lists2.get(i));
		}

		request.setAttribute("curPageNo", page);
		request.setAttribute("lists", items2);
	}
	
	public void searchLists(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String b_id = request.getParameter("b_id");
			
			String sql = "select * " + 
					"from ek_cafe_board, ek_cafe_member " + 
					"where m_id = b_id and b_id=?" + 
					"order by b_date";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b_id);
			rs = pstmt.executeQuery();

			ArrayList<BoardList> lists = new ArrayList<>();
			BoardList b = null;
			while (rs.next()) {
			
				b = new BoardList();
				b.setB_no(rs.getInt("b_no"));
				b.setB_id(rs.getString("b_id"));
				b.setB_txt(rs.getString("b_txt"));
				b.setB_date(rs.getDate("b_date"));
				b.setM_img(rs.getString("m_img"));
				lists.add(b);
			}

			if (lists.size() == 0) {
				request.setAttribute("r", "없음");
				lists.add(null);			
				lists.add(null);			
				lists.add(null);
			}

			HttpSession hs = request.getSession();
			hs.setAttribute("searchBoard", lists);
			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("r", "DB서버오류");

			ArrayList<BoardList> lists = new ArrayList<>();
			lists.add(null);
			lists.add(null);
			lists.add(null);
			
			HttpSession hs = request.getSession();
			hs.setAttribute("searchBoard", lists);
			
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	
	public void write(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.connect();
			
			String b_txt = request.getParameter("b_txt");
			b_txt = b_txt.replace("\n", "<br>");
			
			HttpSession hs = request.getSession();
			Member m = (Member) hs.getAttribute("loginMember");
			String b_id = m.getM_id();
			
			String sql = "insert into ek_cafe_board values(" 
					+ "ek_cafe_board_seq.nextval, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b_id);
			pstmt.setString(2, b_txt);
			
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
