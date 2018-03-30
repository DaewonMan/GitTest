package com.copain.ek.board;

import java.util.Date;

public class BoardRepl {
	private int r_no;
	private int r_b_no;
	private String r_id;
	private String r_txt;
	private Date r_date;

	public BoardRepl() {
		// TODO Auto-generated constructor stub
	}

	public BoardRepl(int r_no, int r_b_no, String r_id, String r_txt, Date r_date) {
		super();
		this.r_no = r_no;
		this.r_b_no = r_b_no;
		this.r_id = r_id;
		this.r_txt = r_txt;
		this.r_date = r_date;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public int getR_b_no() {
		return r_b_no;
	}

	public void setR_b_no(int r_b_no) {
		this.r_b_no = r_b_no;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getR_txt() {
		return r_txt;
	}

	public void setR_txt(String r_txt) {
		this.r_txt = r_txt;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	
	

}
