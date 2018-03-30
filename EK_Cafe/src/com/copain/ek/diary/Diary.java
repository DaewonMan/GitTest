package com.copain.ek.diary;

import java.util.Date;

public class Diary {

	private int d_no;
	private String d_id;
	private String d_title;
	private String d_txt;
	private Date d_date;
	
	
	public Diary() {
		// TODO Auto-generated constructor stub
	}


	public Diary(int d_no, String d_id, String d_title, String d_txt, Date d_date) {
		super();
		this.d_no = d_no;
		this.d_id = d_id;
		this.d_title = d_title;
		this.d_txt = d_txt;
		this.d_date = d_date;
	}


	public int getD_no() {
		return d_no;
	}


	public void setD_no(int d_no) {
		this.d_no = d_no;
	}


	public String getD_id() {
		return d_id;
	}


	public void setD_id(String d_id) {
		this.d_id = d_id;
	}


	public String getD_title() {
		return d_title;
	}


	public void setD_title(String d_title) {
		this.d_title = d_title;
	}


	public String getD_txt() {
		return d_txt;
	}


	public void setD_txt(String d_txt) {
		this.d_txt = d_txt;
	}


	public Date getD_date() {
		return d_date;
	}


	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}
	
	

	
}
