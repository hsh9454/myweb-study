<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>새 글 쓰기</title></head>
<body>
    <h2>새 게시글 작성</h2>
<form action="/myweb/board/register" method="post">
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
        <tr><th>내용</th><td><textarea name="content" rows="10" cols="40" required></textarea></td></tr>
    </table>
    <br>
    <button type="submit">등록하기</button>
</form>
</body>
</html>