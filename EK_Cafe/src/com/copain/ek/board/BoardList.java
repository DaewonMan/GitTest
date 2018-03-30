package com.copain.ek.board;

import java.util.ArrayList;
import java.util.Date;

public class BoardList {
	private int b_no;
	private String b_id;
	private String b_txt;
	private Date b_date;
	private String m_img;
	
	private ArrayList<BoardRepl> b_repls;
	
	public BoardList() {
		// TODO Auto-generated constructor stub
	}

	public BoardList(int b_no, String b_id, String b_txt, Date b_date, String m_img, ArrayList<BoardRepl> b_repls) {
		super();
		this.b_no = b_no;
		this.b_id = b_id;
		this.b_txt = b_txt;
		this.b_date = b_date;
		this.m_img = m_img;
		this.b_repls = b_repls;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getB_txt() {
		return b_txt;
	}

	public void setB_txt(String b_txt) {
		this.b_txt = b_txt;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public String getM_img() {
		return m_img;
	}

	public void setM_img(String m_img) {
		this.m_img = m_img;
	}

	public ArrayList<BoardRepl> getB_repls() {
		return b_repls;
	}

	public void setB_repls(ArrayList<BoardRepl> b_repls) {
		this.b_repls = b_repls;
	}
	
	
	
	
}
