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
			// ����
			con = DBManager.connect();

			// �׸��� ����� ��¥ ���
			String path = request.getServletContext().getRealPath("img");
		
			// �� ��������
			// request.setCharacterEncoding("euc-kr"); //POST; �������ö� �ʿ����
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

			String m_birth = String.format("%s%02d%02d", m_y, m, d); // ��¥���� ���ڿ��� ��ȯ

			String m_img = mr.getFilesystemName("m_img"); // ���� ���ϸ� ���� �� �ִ�.
			
			// ���� ���� ���� �������� ���� �ִ� ���� �״�� ����.
			HttpSession hs1 = request.getSession();
			Member m1 = (Member) hs1.getAttribute("loginMember");
			
			if(m_img != null) {
				m_img = URLEncoder.encode(m_img, "euc-kr");
				m_img = m_img.replace("+", " ");
				
				/* �� �� ���� ���� ���� */
				String oldImg = m1.getM_img();
				File oldFile = new File(path + "/" + oldImg);
				oldFile.delete();
			} /*else {
				m_img = m1.getM_img();
				
			}*/

			// sql��
			String sql = "update ek_cafe_member set m_pw=?, m_name=?, m_img=?, m_birth=to_date(?,'YYYYMMDD') where m_id=?";

			// pstmt�����
			pstmt = con.prepareStatement(sql);

			// ? ä���
			pstmt.setString(1, m_pw);
			pstmt.setString(2, m_name);
			pstmt.setString(3, m_img);
			pstmt.setString(4, m_birth);
			pstmt.setString(5, m_id);

			// ����
			if (pstmt.executeUpdate() == 1) {
				// ���ǿ� �ִ� �͵� �������� �ٲ����
				Member m2 = new Member();
				m2.setM_id(m_id);
				m2.setM_pw(m_pw);
				m2.setM_name(m_name);
				m2.setM_img(m_img);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				m2.setM_birth(sdf.parse(m_birth));
				
				HttpSession hs2 = request.getSession();
				hs2.setAttribute("loginMember", m2);
				
				request.setAttribute("r", "ȸ�� ���� ����");
				//System.out.println("ȸ�� ���� ����");
			} else {
				request.setAttribute("r", "ȸ�� ���� ����");
				//System.out.println("ȸ�� ���� ����");
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
			// ����
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
					
					logout(request, response); // Ż�� ������ �α׾ƿ�ó��
					
					// Ż�������ν� �α��ν� ���� �α����� �̷µ� ���ش�
					Cookie[] allCookie = request.getCookies();

					for (Cookie cookie : allCookie) {
						if (cookie.getName().equals("lastLoginId")) {
							cookie.setValue(null);
							response.addCookie(cookie); // �ݿ�
						}
					}
					
					
					request.setAttribute("r", "ȸ�� Ż�� ����");
					//System.out.println("ȸ��Ż�𼺰�");
				} else {
					request.setAttribute("r", "ȸ�� Ż�� ����");
					//System.out.println("ȸ��Ż�����");
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
				// EKAutoLoginId�̶�� �̸��� ��Ű�� ������ && ��Ű�� ���� ������
				if (cookie.getName().equals("EKAutoLoginId") && (cookie.getValue() != null)) {

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {
						con = DBManager.connect();

						String m_id = cookie.getValue();

						// id�� xx�� ����� ������ �����ͼ�
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
							hs.setMaxInactiveInterval(86400); // �Ϸ簣 ���� �����ð�

							Cookie autoLoginId = new Cookie("EKAutoLoginId", m_id);
							autoLoginId.setMaxAge(86400); // �Ϸ簣 ��Ű ����
							response.addCookie(autoLoginId);

						} else {
							request.setAttribute("r", "�׷� ���� ����");
						}
					}

					catch (Exception e) {
						e.printStackTrace();

						// DB���� ����
						request.setAttribute("r", "DB ���� ����");

					} finally {
						DBManager.close(con, pstmt, rs);
					}

				}
			}
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		// hs.invalidate(); // ���ǿ� �ִ� �� �� ������

		hs.setAttribute("loginMember", null);
		hs.setAttribute("permitedId", null);
		
		Cookie[] allCookie = request.getCookies();

		for (Cookie cookie : allCookie) {
			// ���ʿ� �α����� �� üũ�ؼ� �α��� �߾�����
			if (cookie.getName().equals("EKAutoLoginId")) {
				cookie.setValue(null); // �����Ǿ��ִ� ID�����
				response.addCookie(cookie); // �ݿ�
			}
		}
	}

	public void join(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// ����
			con = DBManager.connect();

			// �׸��� ����� ��¥ ���
			String path = request.getServletContext().getRealPath("img");
			//System.out.println(path);

			// �� ��������
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

			String m_birth = String.format("%s%02d%02d", m_y, m, d); // ��¥���� ���ڿ��� ��ȯ

			String m_img = mr.getFilesystemName("m_img");
			m_img = URLEncoder.encode(m_img, "euc-kr");
			m_img = m_img.replace("+", " ");

			// sql��
			String sql = "insert into ek_cafe_member values(?, ?, ?, to_date(?,'YYYYMMDD'), ?)";

			// pstmt�����
			pstmt = con.prepareStatement(sql);

			// ? ä���
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_pw);
			pstmt.setString(3, m_name);
			pstmt.setString(4, m_birth);
			pstmt.setString(5, m_img);

			// ����
			if (pstmt.executeUpdate() == 1) {
				request.setAttribute("r", "ȸ�� ��� ����");
				//System.out.println("ȸ�� ��� ����");
			} else {
				request.setAttribute("r", "ȸ�� ��� ����");
				//System.out.println("ȸ�� ��� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("DB���� ����");
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

			// id�� xx�� ����� ������ �����ͼ�
			String sql = "select * from ek_cafe_member where m_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String db_pw = rs.getString("m_pw"); // db�� ����� ����� �����´�
				// db�� ����� ����� �Է��� ����� ��ġ�ϴ���
				if (m_pw.equals(db_pw)) {

					Cookie lastLoginId = new Cookie("lastLoginId", m_id); // ���� �α����� id�� ��Ű�� ��´�.
					lastLoginId.setMaxAge(24 * 60 * 60); // �Ϸ絿�� ��Ű �����Ͽ� �α��ν� id�Է¶��� �����ְ�
					response.addCookie(lastLoginId);

					Member m = new Member();

					m.setM_id(m_id);
					m.setM_pw(m_pw);
					m.setM_name(rs.getString("m_name"));
					m.setM_birth(rs.getDate("m_birth"));
					m.setM_img(rs.getString("m_img"));

					HttpSession hs = request.getSession();
					hs.setAttribute("loginMember", m); // ���� �α����� ȸ�������� ���ǿ� ��´�.
					hs.setMaxInactiveInterval(15 * 60); // 15�а� ȸ�������� ���ǿ� �����Ѵ�.

					// �ڵ� �α��� ��� ���� �˻�
					if (request.getParameter("autoLogin") != null) {
						Cookie autoLoginId = new Cookie("EKAutoLoginId", m_id); // �ڵ��α��� üũ�� �ش�id ��Ű�� ��´�
						autoLoginId.setMaxAge(86400); // �Ϸ絿�� ��Ű ���� (24 * 60 * 60 = 86400)
						response.addCookie(autoLoginId);
					}

				} else {
					request.setAttribute("r", "��� Ʋ��");
					//System.out.println("��� Ʋ��");
				}
			} else {
				request.setAttribute("r", "�׷� ���� ����");
				//System.out.println("�׷� ���� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();

			// DB���� ����
			request.setAttribute("r", "DB ���� ����");

		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
}
