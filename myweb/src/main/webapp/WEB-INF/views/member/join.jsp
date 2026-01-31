<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    
    .join-container { width: 300px; margin: 50px auto; }
    .join-container input { width: 100%; margin-bottom: 10px; padding: 8px; }
    .join-container button { width: 100%; padding: 10px; background: #333; color: #fff; border: none; cursor: pointer; }
</style>
</head>
<body>

<div class="join-container">
    <h2>회원가입</h2>
    <form action="join" method="post">
        <input type="text" name="userid" placeholder="아이디 입력" required>
        <input type="password" name="userpw" placeholder="비밀번호 입력" required>
        <input type="text" name="username" placeholder="닉네임(게시판 표시)" required>
        
        <button type="submit">가입하기</button>
    </form>
    <p style="text-align: center; margin-top: 10px;">
        <a href="/login" style="font-size: 12px; color: #666;">이미 계정이 있으신가요?</a>
    </p>
</div>

</body>
</html>