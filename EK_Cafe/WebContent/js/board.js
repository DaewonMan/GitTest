function toDeleteRepl(r_no){
	var ok = confirm("해당 댓글을 지우시겠습니까?");
	
	if(ok) {
		location.href = "BoardDeleteReplC?r_no=" + r_no;		
	}
}

function toDeleteBoard(b_no){
	var ok = confirm("삭제합니까?");
	
	if(ok){
		location.href = "BoardDeleteC?b_no=" + b_no;
	}
	
}
function toUpdateBoard(b_no){
	var b_txt = prompt("수정할 내용을 입력하세요");
	
	if(b_txt != null && b_txt != "" && b_txt.length <= 190) {
		location.href = "BoardUpdateC?b_no=" + b_no + "&b_txt=" + b_txt;
	}
	
}