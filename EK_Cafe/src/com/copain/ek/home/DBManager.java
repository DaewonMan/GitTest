package com.copain.ek.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DBManager {
	
	// �̸� ������ � ����� ����
	// DB������ ���� �ش޶�� �ϸ� ����� ���� ������ �ִ� �ý���
	// ConnectionPool; Tomcat�� �־�� ����� �� �ִ�.
	// ��õŵ� xml�� �����ϱ� ���ϴ�, ���ᵵ ������.
	public static Connection connect() throws NamingException, SQLException {
		
		// context.xml; import�� naming�� �ִ� context����
		Context ctx = new InitialContext();
		
		// Ʃ�� �뿩��(Connection �뿩��)
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/dwmPool");
		
		return ds.getConnection();
	}
	
	
	// DB������ ���� �ش޶�� �ϸ� �� �� ������ �ξ��ִ� �ý���
	// �ڹ� ������ db���� �޼ҵ�; **������ �� �� �޼ҵ�**
	public static Connection oldConnect() throws ClassNotFoundException, SQLException  {
		// OracleDriverŬ���� ����
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// DB���� �ּ�
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		
		return DriverManager.getConnection(url, "eodnjs725", "1187614");
		
	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {rs.close();} catch (Exception e) {}
		try {pstmt.close();} catch (Exception e) {}
		try {con.close();} catch (Exception e) {}
	}
}
