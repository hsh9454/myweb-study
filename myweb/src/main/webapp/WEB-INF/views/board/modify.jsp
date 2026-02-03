<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>글 수정하기</title></head>
<body>
    <h2>🛠️ 게시글 수정/삭제</h2>
    
    <form method="post">
        <input type="hidden" name="bno" value="${board.bno}">
        <input type="hidden" name="writer" value="${board.writer}">
        
        <input type="hidden" name="bgno" value="${empty param.bgno ? 2 : param.bgno}"> 

        <table border="1">
        
            <tr>
                <th>번호</th>
                <td><input value="${vNum}" readonly></td>
            </tr>
            <tr><th>작성자</th><td>${board.writer}</td></tr>
            <tr><th>제목</th><td><input name="title" value="${board.title}"></td></tr>
            <tr><th>내용</th><td><textarea name="content" rows="10">${board.content}</textarea></td></tr>
        </table>
        <br>

        <button type="button" onclick="submitForm('/myweb/board/modify')">수정완료</button>
        
        <button type="button" onclick="if(confirm('정말 삭제하시겠습니까?')){ submitForm('/myweb/board/remove'); }">삭제하기</button>
        
        <button type="button" onclick="location.href='/myweb/board/list?bgno=${param.bgno}'">취소</button>
    </form>

    <script>
        
        function submitForm(actionPath) {
            const f = document.querySelector('form');
            f.action = actionPath;
            f.submit();
        }
    </script>
</body>
</html>