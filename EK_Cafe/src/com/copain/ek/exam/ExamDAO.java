package com.copain.ek.exam;

import java.io.File;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.copain.ek.board.BoardList;
import com.copain.ek.home.DBManager;
import com.copain.ek.member.Member;
import com.copain.ek.member.MemberDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ExamDAO {
	private ArrayList<Exam> exams;
	private ArrayList<Member> emrList;
	
	private static final ExamDAO EDAO = new ExamDAO();
	
	private ExamDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ExamDAO getEdao() {
		return EDAO;
	}
	
	public void deleteExaminer(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			
			String emr_id = request.getParameter("emr_id");
			
			String sql = "delete from ek_cafe_examiner where emr_id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, emr_id);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "출제자 삭제 성공!");
			} else {
				request.setAttribute("r", "출제자 삭제 실패");
			}

		} catch (Exception e) {
			request.setAttribute("r", "DB 서버 문제!!");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void deleteProblem(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			
			int e_no = Integer.parseInt(request.getParameter("e_no"));
			
			String sql = "delete from ek_cafe_exam where e_no=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, e_no);

			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "문제 삭제 성공!");
			} else {
				request.setAttribute("r", "문제 삭제 실패");
			}

		} catch (Exception e) {
			request.setAttribute("r", "DB 서버 문제!!");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void addProblem(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();
			
			String e_level = request.getParameter("e_level");
			String e_problem = request.getParameter("e_problem");
			String e_answer1 = request.getParameter("e_answer1");
			String e_answer2 = request.getParameter("e_answer2");
			String e_answer3 = request.getParameter("e_answer3");
			String e_solution = request.getParameter("e_solution");
			
			// sql문
			String sql = "insert into EK_CAFE_EXAM values(ek_cafe_exam_seq.nextval, ?, ?, ?, ?, ?, ?)";

			// pstmt만들고
			pstmt = con.prepareStatement(sql);

			// ? 채우기
			pstmt.setString(1, e_level);
			pstmt.setString(2, e_problem);
			pstmt.setString(3, e_answer1);
			pstmt.setString(4, e_answer2);
			pstmt.setString(5, e_answer3);
			pstmt.setString(6, e_solution);
			

			// 실행
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "문제 등록 성공");
			} else {
				request.setAttribute("r", "문제 등록 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "DB서버 문제");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void problemUpdate(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			String e_problem = request.getParameter("e_problem");
			String e_answer1 = request.getParameter("e_answer1");
			String e_answer2 = request.getParameter("e_answer2");
			String e_answer3 = request.getParameter("e_answer3");
			String e_solution = request.getParameter("e_solution");
			int e_no = Integer.parseInt(request.getParameter("e_no"));
			
			// sql문
			String sql = "update ek_cafe_exam set e_problem=?, e_answer1=?, e_answer2=?, e_answer3=?, e_solution=? where e_no=?";

			// pstmt만들고
			pstmt = con.prepareStatement(sql);

			// ? 채우기
			pstmt.setString(1, e_problem);
			pstmt.setString(2, e_answer1);
			pstmt.setString(3, e_answer2);
			pstmt.setString(4, e_answer3);
			pstmt.setString(5, e_solution);
			pstmt.setInt(6, e_no);

			// 실행
			if (pstmt.executeUpdate() == 1) {
				/*Exam e = new Exam();
				e.setE_problem(e_problem);
				e.setE_answer1(e_answer1);
				e.setE_answer2(e_answer2);
				e.setE_answer3(e_answer3);
				e.setE_solution(e_solution);*/
				
				request.setAttribute("r", "문제 수정 성공");
				
			} else {
				request.setAttribute("r", "문제 수정 실패");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "DB 문제");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void getExaminerConnect(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		ArrayList<Member> emrList2 = (ArrayList<Member>) hs.getAttribute("searchExaminer");
		
		Member m = (Member) hs.getAttribute("loginMember");
		if(m != null) {
			String m_id = m.getM_id();			
			
			boolean permitedId = false;
			for (Member member : emrList2) {
				if(member.getM_id().equals(m_id)) {
					permitedId = true;
					break;
				}
			}
			
			hs.setAttribute("permitedId", permitedId);
		}		
	}
	
	public void getAllExaminers(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String sql = "select * from ek_cafe_examiner";
			
			pstmt = con.prepareStatement(sql);
	
			rs = pstmt.executeQuery();

			emrList = new ArrayList<>();
			Member m = null;
			while (rs.next()) {
				m = new Member(rs.getString("emr_id"), null, null, null, null);
				
				emrList.add(m);
			}
			
			HttpSession hs = request.getSession();
			hs.setAttribute("searchExaminer", emrList);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "DB서버오류");
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	public void enroll(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();
			
			String emr_id = request.getParameter("emr_id");
			
			// sql문
			String sql = "insert into EK_CAFE_EXAMINER values(?)";

			// pstmt만들고
			pstmt = con.prepareStatement(sql);

			// ? 채우기
			pstmt.setString(1, emr_id);
			

			// 실행
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "출제자 등록 성공");
			} else {
				request.setAttribute("r", "출제자 등록 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("r", "DB서버 문제");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public void checkSolution(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		
		ArrayList<Exam> exams2 = (ArrayList<Exam>) hs.getAttribute("searchExam");
		
		if(exams2 == null) {
			exams2 = exams;
		}
		
		
		ArrayList<Boolean> oOrX = new ArrayList<>();
		String selectList = null;
		int cnt = 0;
		for (Exam exam : exams2) {
			selectList = request.getParameter(exam.getE_no()+"");
			System.out.println(selectList);
			// 사용자가 선택한 것과 같은지
			if(selectList.equals(exam.getE_solution())) {
				oOrX.add(true);
				cnt++; // 맞은 갯수 세기
			}
			else {
				oOrX.add(false);
			}
		}
		
		boolean giveLevel = false;
		// 맞은 갯수가 70점 이상이면 통과
		if(cnt >= oOrX.size()*0.7) {
			giveLevel = true;
		}
		
		HttpSession hs2 = request.getSession();
		hs2.setAttribute("examResult", oOrX);
		hs2.setAttribute("giveLevel", giveLevel);
	}
	
	public void getAllProblems(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBManager.connect();

			String sql = "select * from ek_cafe_exam where e_level=?";
			
			String e_level = request.getParameter("e_level");
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, e_level);
			rs = pstmt.executeQuery();

			exams = new ArrayList<>();
			Exam e = null;
			while (rs.next()) {
				e = new Exam();
				e.setE_no(rs.getInt("e_no"));
				e.setE_level(rs.getString("e_level"));
				e.setE_problem(rs.getString("e_problem"));
				e.setE_answer1(rs.getString("e_answer1"));
				e.setE_answer2(rs.getString("e_answer2"));
				e.setE_answer3(rs.getString("e_answer3"));
				e.setE_solution(rs.getString("e_solution"));
				
				exams.add(e);
			}

			if (exams.size() == 0) {
				request.setAttribute("r", "해당 문제가 없음");
				exams.add(null);
			}

			HttpSession hs = request.getSession();
			hs.setAttribute("searchExam", exams);
			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("r", "DB서버오류");
			
			
			HttpSession hs = request.getSession();
			hs.setAttribute("searchExam", null);
			
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
}
