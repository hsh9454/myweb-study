<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>글 수정하기</title></head>
<body>
    <h2>🛠️ 게시글 수정/삭제</h2>
    <form action="/myweb/board/modify" method="post">
        <input type="hidden" name="bno" value="${board.bno}">
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
        <button type="submit">수정완료</button>
        <button type="button" 
        onclick="if(confirm('정말 삭제하시겠습니까?')){ 
                    let f = document.querySelector('form'); 
                    f.action='/myweb/board/remove'; 
                    f.submit(); 
                 }">삭제하기</button>
        <button type="button" onclick="location.href='/myweb/board/list'">취소</button>
    </form>
</body>
</html>