function toExamTest(e_level) {
	location.href = "ExamTestC?e_level=" + e_level;
}

function toExaminerEnroll() {
	location.href = "ExaminerEnrollC";
}

function toExaminerEnrollCheck() {
	var idField = document.enrollForm.emr_id;
	
	if(isEmpty(idField)) {
		alert("ID �ٽ�");
		idField.value = "";
		idField.focus();
		return false;
	}
	
	return true;
}

function toExamUpdate(e_level) {
	location.href = "ExamUpdateC?e_level=" + e_level;
}

function toProblemsCheck(pf) {
	var e_problemField = pf.e_problem;
	var e_answer1Field = pf.e_answer1;
	var e_answer2Field = pf.e_answer2;
	var e_answer3Field = pf.e_answer3;
	var e_solutionField = pf.e_solution;
	
	if(isEmpty(e_problemField)) {
		alert("problem �ٽ�");
		e_problemField.value = "";
		e_problemField.focus();
		return false;
	} else if(isEmpty(e_answer1Field)) {
		alert("answer1 �ٽ�");
		e_answer1Field.value = "";
		e_answer1Field.focus();
		return false;
	} else if(isEmpty(e_answer2Field)) {
		alert("answer2 �ٽ�");
		e_answer2Field.value = "";
		e_answer2Field.focus();
		return false;
	} else if(isEmpty(e_answer3Field)) {
		alert("answer3 �ٽ�");
		e_answer3Field.value = "";
		e_answer3Field.focus();
		return false;
	} else if(isEmpty(e_solutionField)) {
		alert("solution �ٽ�");
		e_solutionField.value = "";
		e_solutionField.focus();
		return false;
	}
	
	return true;
}

function toAddProblem(e_level) {
	location.href = "ExamAddProblemC?e_level=" + e_level;
}

function toAddProblemCheck(){
	var problemField = document.addProForm.e_problem;
	var ans1Field = document.addProForm.e_answer1;
	var ans2Field = document.addProForm.e_answer2;
	var ans3Field = document.addProForm.e_answer3;
	var solutionField = document.addProForm.e_solution;
	
	if(isEmpty(problemField)) {
		alert("problem �ٽ�");
		problemField.value = "";
		problemField.focus();
		return false;
	} else if(isEmpty(ans1Field)) {
		alert("answer1 �ٽ�");
		ans1Field.value = "";
		ans1Field.focus();
		return false;
	} else if(isEmpty(ans2Field)) {
		alert("answer2 �ٽ�");
		ans2Field.value = "";
		ans2Field.focus();
		return false;
	} else if(isEmpty(ans3Field)) {
		alert("answer3 �ٽ�");
		ans3Field.value = "";
		ans3Field.focus();
		return false;
	} else if(isEmpty(solutionField)) {
		alert("solution �ٽ�");
		solutionField.value = "";
		solutionField.focus();
		return false;
	}
	
	return true;
	
}

function toDeleteProblem(e_no, e_level){
	var ok = confirm("�ش� ������ �����մϱ�?");
	
	if(ok) {
		location.href = "ExamDeleteProblemC?e_no=" + e_no + "&e_level=" + e_level;
	}
}


function toExaminerDeleteCheck(deleteThis) {
	var ok = confirm("������ ������ �����ڿ��� ��Ż�մϱ�?");
	
	var selectField = deleteThis.emr_id;
	
	if(isEmpty(selectField)) {
		alert("�����Ͻÿ�");
		return false;
	}
	
	if(ok) {
		return true;		
	}
	return false
}
