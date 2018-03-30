function toLogin() {
	location.href="MemberLoginC";
}

function toJoin(){
	location.href = "MemberJoinC";
}

function updateCheck(){
	var idField = document.joinForm.m_id;
	var pwField = document.joinForm.m_pw;
	var pwChkField = document.joinForm.m_pwChk;
	var nameField = document.joinForm.m_name;
	var imgField = document.joinForm.m_img;
	
	var ok = confirm("������ �����Ͻðڽ��ϱ�?");
	
	if(isEmpty(idField) || containsHangul(idField)) {
		alert("ID �ٽ�");
		idField.value = "";
		idField.focus();
		return false;
	} else if(isEmpty(pwField) || notContains(pwField, "1234567890")
			|| notContains(pwField, "qwertyuiopasdfghjklzxcvbnm")
			|| notContains(pwField, "QWERTYUIOPASDFGHJKLZXCVBNM")) {
		alert("PW �ٽ�");
		pwField.value = "";
		pwField.focus();
		return false;
	} else if(notEquals(pwChkField, pwField)) {
		alert("��й�ȣ �ٸ�");
		pwField.value = "";
		pwcField.value = "";
		pwField.focus();
		return false;
	} else if(isEmpty(nameField)) {
		alert("�̸� �ٽ�");
		nameField.value = "";
		nameField.focus();
		return false;
	} else if(isEmpty(imgField) && ok) {
		return true;
	} else if(isEmpty(imgField) || isNotType(imgField, "jpg")
				&& isNotType(imgField, "png")
				&& isNotType(imgField, "gif")
				&& isNotType(imgField, "jpeg")) {
		alert("�̹��� �ٽ�");
		imgField.value = "";
		imgField.focus();
		return false;
	}
	
	if(ok) {
		return true;
	}
	
	return false;
}

function joinCheck(){
	var idField = document.joinForm.m_id;
	var pwField = document.joinForm.m_pw;
	var pwChkField = document.joinForm.m_pwChk;
	var nameField = document.joinForm.m_name;
	var imgField = document.joinForm.m_img;
	
	if(isEmpty(idField) || containsHangul(idField)) {
		alert("ID �ٽ�");
		idField.value = "";
		idField.focus();
		return false;
	} else if(isEmpty(pwField) || notContains(pwField, "1234567890")
			|| notContains(pwField, "qwertyuiopasdfghjklzxcvbnm")
			|| notContains(pwField, "QWERTYUIOPASDFGHJKLZXCVBNM")) {
		alert("PW �ٽ�");
		pwField.value = "";
		pwField.focus();
		return false;
	} else if(notEquals(pwChkField, pwField)) {
		alert("��й�ȣ �ٸ�");
		pwField.value = "";
		pwcField.value = "";
		pwField.focus();
		return false;
	} else if(isEmpty(nameField)) {
		alert("�̸� �ٽ�");
		nameField.value = "";
		nameField.focus();
		return false;
	} else if(isEmpty(imgField) || isNotType(imgField, "jpg")
				&& isNotType(imgField, "png")
				&& isNotType(imgField, "gif")
				&& isNotType(imgField, "jpeg")) {
		alert("�̹��� �ٽ�");
		imgField.value = "";
		imgField.focus();
		return false;
	}
	
	return true;
}

function loginCheck(){
	var idField = document.loginForm.m_id;
	var pwField = document.loginForm.m_pw;
	
	if(isEmpty(idField) || containsHangul(idField)) {
		alert("ID �ٽ�");
		idField.value = "";
		idField.focus();
		return false;
	} else if(isEmpty(pwField)) {
		alert("PW �ٽ�");
		pwField.value = "";
		pwField.focus();
		return false;
	}
	
	return true;
}

function toUpdate() {
	location.href = "MemberUpdateC";
}

function toDelete() {
	var pw = prompt("��й�ȣ�� �Է��Ͻÿ�");
	
	if(pw != null && pw != "") {
		location.href = "MemberDeleteC?m_pw=" + pw;
	}
}

function toLogout() {
var ok = confirm("�α׾ƿ� �Ͻðڽ��ϱ�?");
	
	if(ok) {
		location.href = "MemberLogoutC";
	}
}