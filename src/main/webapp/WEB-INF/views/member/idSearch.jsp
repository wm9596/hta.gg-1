<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/idSearch.jsp</title>
<script type="text/javascript" src="/lol/resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
});
function backPage(){
	history.go(-1);
}
</script>
</head>
<body>
<h1>아이디 찾기</h1>
이메일<br>
<input type="email" name="email" id="email" style="width: 300px;"><br>
<input type="button" id="emailOk" onclick="emailOk()" value="이메일인증" style="width: 308px;"><br>
인증번호<br>
<input type="text" name="confirm" id="confirm" style="width: 300px;"><br>
<input type="button" id="searchId" onclick="searchId()" value="아이디 찾기" style="width: 308px;"><br>
<span id="confirmId"></span><br>
<input type="button" value="뒤로가기" onclick="backPage()" style="width: 308px;">
</body>
</html>