function toDeleteRepl(r_no){
	var ok = confirm("�ش� ����� ����ðڽ��ϱ�?");
	
	if(ok) {
		location.href = "BoardDeleteReplC?r_no=" + r_no;		
	}
}

function toDeleteBoard(b_no){
	var ok = confirm("�����մϱ�?");
	
	if(ok){
		location.href = "BoardDeleteC?b_no=" + b_no;
	}
	
}
function toUpdateBoard(b_no){
	var b_txt = prompt("������ ������ �Է��ϼ���");
	
	if(b_txt != null && b_txt != "" && b_txt.length <= 190) {
		location.href = "BoardUpdateC?b_no=" + b_no + "&b_txt=" + b_txt;
	}
	
}