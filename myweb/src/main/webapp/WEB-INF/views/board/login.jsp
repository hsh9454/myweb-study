<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
    <h2>로그인 하세요</h2>
    <hr>
    
    <form action="login" method="post">
        <div>
            아이디: <input type="text" name="userid" required>
        </div>
        <div>
            비밀번호: <input type="password" name="userpw" required>
        </div>
        <br>
        <button type="submit">로그인</button>
       <a href="list">취소</a>
    </form>
    
</body>
</html>