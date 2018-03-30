package com.copain.ek.download;

import java.util.Date;

public class Download {
	private int d_no;
	private String d_id;
	private String d_title;
	private String d_file;
	private Date d_date;

	public Download() {
		// TODO Auto-generated constructor stub
	}

	public Download(int d_no, String d_id, String d_title, String d_file, Date d_date) {
		super();
		this.d_no = d_no;
		this.d_id = d_id;
		this.d_title = d_title;
		this.d_file = d_file;
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

	public String getD_file() {
		return d_file;
	}

	public void setD_file(String d_file) {
		this.d_file = d_file;
	}

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}
	
	
}
