package com.copain.ek.exam;

public class Exam {
	private int e_no;
	private String e_level;
	private String e_problem;
	private String e_answer1;
	private String e_answer2;
	private String e_answer3;
	private String e_solution;
	
	public Exam() {
		// TODO Auto-generated constructor stub
	}

	public Exam(int e_no, String e_level, String e_problem, String e_answer1, String e_answer2, String e_answer3,
			String e_solution) {
		super();
		this.e_no = e_no;
		this.e_level = e_level;
		this.e_problem = e_problem;
		this.e_answer1 = e_answer1;
		this.e_answer2 = e_answer2;
		this.e_answer3 = e_answer3;
		this.e_solution = e_solution;
	}

	public int getE_no() {
		return e_no;
	}

	public void setE_no(int e_no) {
		this.e_no = e_no;
	}

	public String getE_level() {
		return e_level;
	}

	public void setE_level(String e_level) {
		this.e_level = e_level;
	}

	public String getE_problem() {
		return e_problem;
	}

	public void setE_problem(String e_problem) {
		this.e_problem = e_problem;
	}

	public String getE_answer1() {
		return e_answer1;
	}

	public void setE_answer1(String e_answer1) {
		this.e_answer1 = e_answer1;
	}

	public String getE_answer2() {
		return e_answer2;
	}

	public void setE_answer2(String e_answer2) {
		this.e_answer2 = e_answer2;
	}

	public String getE_answer3() {
		return e_answer3;
	}

	public void setE_answer3(String e_answer3) {
		this.e_answer3 = e_answer3;
	}

	public String getE_solution() {
		return e_solution;
	}

	public void setE_solution(String e_solution) {
		this.e_solution = e_solution;
	}
	
	
	
	
}
