<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>새 글 쓰기</title></head>
<body>
    <h2>✍️ 새 게시글 작성</h2>
    <form action="/myweb/board/register" method="post">
        <table border="1">
            <tr><th>작성자</th><td><input name="writer" placeholder="작성자 입력" required></td></tr>
            <tr><th>제목</th><td><input name="title" placeholder="제목 입력" required style="width:300px;"></td></tr>
            <tr><th>내용</th><td><textarea name="content" rows="10" cols="40" placeholder="내용을 입력하세요"></textarea></td></tr>
        </table>
        <br>
        <button type="submit">등록하기</button>
        <button type="button" onclick="location.href='/myweb/board/list'">취소</button>
    </form>
</body>
</html>