package com.copain.ek.member;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.copain.ek.home.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberDAO {
	private static final MemberDAO MDAO = new MemberDAO();

	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDAO getMdao() {
		return MDAO;
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			// 그림이 저장된 진짜 경로
			String path = request.getServletContext().getRealPath("img");
		
			// 값 가져오기
			// request.setCharacterEncoding("euc-kr"); //POST; 사진들어올땐 필요없음
			MultipartRequest mr = new MultipartRequest(request, path, 31457280, "euc-kr",
					new DefaultFileRenamePolicy());

			String m_id = mr.getParameter("m_id");
			String m_pw = mr.getParameter("m_pw");
			String m_name = mr.getParameter("m_name");

			String m_y = mr.getParameter("m_y");
			String m_m = mr.getParameter("m_m");
			int m = Integer.parseInt(m_m);
			String m_d = mr.getParameter("m_d");
			int d = Integer.parseInt(m_d);

			String m_birth = String.format("%s%02d%02d", m_y, m, d); // 날짜형식 문자열로 변환

			String m_img = mr.getFilesystemName("m_img"); // 사진 파일명 없을 수 있다.
			
			// 사진 파일 선택 안했으면 원래 있던 파일 그대로 쓴다.
			HttpSession hs1 = request.getSession();
			Member m1 = (Member) hs1.getAttribute("loginMember");
			
			if(m_img != null) {
				m_img = URLEncoder.encode(m_img, "euc-kr");
				m_img = m_img.replace("+", " ");
				
				/* 그 전 사진 파일 삭제 */
				String oldImg = m1.getM_img();
				File oldFile = new File(path + "/" + oldImg);
				oldFile.delete();
			} /*else {
				m_img = m1.getM_img();
				
			}*/

			// sql문
			String sql = "update ek_cafe_member set m_pw=?, m_name=?, m_img=?, m_birth=to_date(?,'YYYYMMDD') where m_id=?";

			// pstmt만들고
			pstmt = con.prepareStatement(sql);

			// ? 채우기
			pstmt.setString(1, m_pw);
			pstmt.setString(2, m_name);
			pstmt.setString(3, m_img);
			pstmt.setString(4, m_birth);
			pstmt.setString(5, m_id);

			// 실행
			if (pstmt.executeUpdate() == 1) {
				// 세션에 있는 것도 새정보로 바꿔야함
				Member m2 = new Member();
				m2.setM_id(m_id);
				m2.setM_pw(m_pw);
				m2.setM_name(m_name);
				m2.setM_img(m_img);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				m2.setM_birth(sdf.parse(m_birth));
				
				HttpSession hs2 = request.getSession();
				hs2.setAttribute("loginMember", m2);
				
				request.setAttribute("r", "회원 수정 성공");
				//System.out.println("회원 수정 성공");
			} else {
				request.setAttribute("r", "회원 수정 실패");
				//System.out.println("회원 수정 실패");
			}

		} catch (Exception e) {
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

			HttpSession hs = request.getSession();
			Member m = (Member) hs.getAttribute("loginMember");
			
			String m_id = m.getM_id();
			String m_pw = request.getParameter("m_pw");
			
			
			if(m_pw.equals(m.getM_pw())) {
				String sql = "delete from ek_cafe_member where m_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_id);
				
				if(pstmt.executeUpdate() == 1) {
					
					String path = request.getServletContext().getRealPath("img");
					String oldImg = m.getM_img();
					oldImg = URLDecoder.decode(oldImg, "euc-kr");
					File oldFile = new File(path + "/" + oldImg);
					oldFile.delete();
					
					logout(request, response); // 탈퇴를 했으니 로그아웃처리
					
					// 탈퇴함으로써 로그인시 전에 로그인한 이력도 없앤다
					Cookie[] allCookie = request.getCookies();

					for (Cookie cookie : allCookie) {
						if (cookie.getName().equals("lastLoginId")) {
							cookie.setValue(null);
							response.addCookie(cookie); // 반영
						}
					}
					
					
					request.setAttribute("r", "회원 탈퇴 성공");
					//System.out.println("회원탈퇴성공");
				} else {
					request.setAttribute("r", "회원 탈퇴 실패");
					//System.out.println("회원탈퇴실패");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	public boolean loginCheck(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();

		Member m = (Member) hs.getAttribute("loginMember");

		if (m != null) {
			request.setAttribute("loginPage", "member/loginOk.jsp");
			return true;
		}
		request.setAttribute("loginPage", "member/memberInfo.jsp");
		return false;
	}
	
	public void autoLogin(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] allCookies = request.getCookies();

		if (allCookies != null) {
			for (Cookie cookie : allCookies) {
				// EKAutoLoginId이라는 이름의 쿠키가 있으면 && 쿠키에 값이 있으면
				if (cookie.getName().equals("EKAutoLoginId") && (cookie.getValue() != null)) {

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {
						con = DBManager.connect();

						String m_id = cookie.getValue();

						// id가 xx인 사람의 정보를 가져와서
						String sql = "select * from ek_cafe_member where m_id=?";

						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, m_id);

						rs = pstmt.executeQuery();

						if (rs.next()) {
							String db_pw = rs.getString("m_pw");

							Cookie lastLoginId = new Cookie("lastLoginId", m_id);
							lastLoginId.setMaxAge(86400);
							response.addCookie(lastLoginId);

							Member m = new Member();

							m.setM_id(m_id);
							m.setM_pw(db_pw);
							m.setM_name(rs.getString("m_name"));
							m.setM_birth(rs.getDate("m_birth"));
							m.setM_img(rs.getString("m_img"));

							HttpSession hs = request.getSession();
							hs.setAttribute("loginMember", m);
							hs.setMaxInactiveInterval(86400); // 하루간 세션 유지시간

							Cookie autoLoginId = new Cookie("EKAutoLoginId", m_id);
							autoLoginId.setMaxAge(86400); // 하루간 쿠키 유지
							response.addCookie(autoLoginId);

						} else {
							request.setAttribute("r", "그런 계정 없음");
						}
					}

					catch (Exception e) {
						e.printStackTrace();

						// DB연결 문제
						request.setAttribute("r", "DB 서버 문제");

					} finally {
						DBManager.close(con, pstmt, rs);
					}

				}
			}
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		// hs.invalidate(); // 세션에 있는 거 싹 지워짐

		hs.setAttribute("loginMember", null);
		hs.setAttribute("permitedId", null);
		
		Cookie[] allCookie = request.getCookies();

		for (Cookie cookie : allCookie) {
			// 애초에 로그인할 때 체크해서 로그인 했었으면
			if (cookie.getName().equals("EKAutoLoginId")) {
				cookie.setValue(null); // 지정되어있던 ID지우고
				response.addCookie(cookie); // 반영
			}
		}
	}

	public void join(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = DBManager.connect();

			// 그림이 저장된 진짜 경로
			String path = request.getServletContext().getRealPath("img");
			//System.out.println(path);

			// 값 가져오기
			MultipartRequest mr = new MultipartRequest(request, path, 31457280, "euc-kr",
					new DefaultFileRenamePolicy());

			String m_id = mr.getParameter("m_id");
			String m_pw = mr.getParameter("m_pw");
			String m_name = mr.getParameter("m_name");

			String m_y = mr.getParameter("m_y");
			String m_m = mr.getParameter("m_m");
			int m = Integer.parseInt(m_m);
			String m_d = mr.getParameter("m_d");
			int d = Integer.parseInt(m_d);

			String m_birth = String.format("%s%02d%02d", m_y, m, d); // 날짜형식 문자열로 변환

			String m_img = mr.getFilesystemName("m_img");
			m_img = URLEncoder.encode(m_img, "euc-kr");
			m_img = m_img.replace("+", " ");

			// sql문
			String sql = "insert into ek_cafe_member values(?, ?, ?, to_date(?,'YYYYMMDD'), ?)";

			// pstmt만들고
			pstmt = con.prepareStatement(sql);

			// ? 채우기
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_pw);
			pstmt.setString(3, m_name);
			pstmt.setString(4, m_birth);
			pstmt.setString(5, m_img);

			// 실행
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "회원 등록 성공");
				//System.out.println("회원 등록 성공");
			} else {
				request.setAttribute("r", "회원 등록 실패");
				//System.out.println("회원 등록 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("DB서버 문제");
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.connect();

			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");

			// id가 xx인 사람의 정보를 가져와서
			String sql = "select * from ek_cafe_member where m_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String db_pw = rs.getString("m_pw"); // db에 저장된 비번을 가져온다
				// db에 저장된 비번과 입력한 비번이 일치하는지
				if (m_pw.equals(db_pw)) {

					Cookie lastLoginId = new Cookie("lastLoginId", m_id); // 현재 로그인한 id를 쿠키에 담는다.
					lastLoginId.setMaxAge(24 * 60 * 60); // 하루동안 쿠키 유지하여 로그인시 id입력란에 남아있게
					response.addCookie(lastLoginId);

					Member m = new Member();

					m.setM_id(m_id);
					m.setM_pw(m_pw);
					m.setM_name(rs.getString("m_name"));
					m.setM_birth(rs.getDate("m_birth"));
					m.setM_img(rs.getString("m_img"));

					HttpSession hs = request.getSession();
					hs.setAttribute("loginMember", m); // 현재 로그인한 회원정보를 세션에 담는다.
					hs.setMaxInactiveInterval(15 * 60); // 15분간 회원정보를 세션에 유지한다.

					// 자동 로그인 사용 여부 검사
					if (request.getParameter("autoLogin") != null) {
						Cookie autoLoginId = new Cookie("EKAutoLoginId", m_id); // 자동로그인 체크시 해당id 쿠키에 담는다
						autoLoginId.setMaxAge(86400); // 하루동안 쿠키 유지 (24 * 60 * 60 = 86400)
						response.addCookie(autoLoginId);
					}

				} else {
					request.setAttribute("r", "비번 틀림");
					//System.out.println("비번 틀림");
				}
			} else {
				request.setAttribute("r", "그런 계정 없음");
				//System.out.println("그런 계정 없음");
			}

		} catch (Exception e) {
			e.printStackTrace();

			// DB연결 문제
			request.setAttribute("r", "DB 서버 문제");

		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
}
