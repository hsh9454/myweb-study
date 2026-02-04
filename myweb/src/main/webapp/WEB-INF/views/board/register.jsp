<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>새 글 쓰기</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>

</head>

<body>
    <h2>새 게시글 작성</h2>
<form id="registerForm" action="/myweb/board/register" method="post">
    <input type="hidden" name="bgno" value="${param.bgno}">
    <table border="1">
        <tr>
            <th>작성자</th>
            <td>
                <input type="text" name="writer" value="${user.username}" readonly
                style="background-color: lightgray;">
            </td>
        </tr>
        <tr><th>제목</th><td><input type="text" name="title" required></td></tr>
        <tr><th>내용</th><td><textarea name="content" id="content" style="width:100%; height:412px;"></textarea></td></tr>
    </table>
    <br>
    <button type="button" onclick="save()">등록하기</button>
</form>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "content", 
    sSkinURI: "${pageContext.request.contextPath}/resources/js/smarteditor/SmartEditor2Skin.html",
    fCreator: "createHTML5Editor"
});

function save() {
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
    document.getElementById("registerForm").submit(); 
    if(form) {
    	form.submit();
    }  else {
    	alert("form의 id가 'registerForm'이 맞는지 확인해주세요!")
    }
}

</script>


</body>
</html>