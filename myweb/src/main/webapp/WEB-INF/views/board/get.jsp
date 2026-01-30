<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 상세보기</title>
    <style>
        .container { width: 800px; margin: 0 auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f8f9fa; width: 150px; }
        .content-box { height: 200px; vertical-align: top; }
        .btn-group { margin-top: 20px; text-align: center; }
        button { padding: 10px 20px; cursor: pointer; }
    </style>
</head>
<body>

<div class="container">
    <h2>📄 게시글 상세보기</h2>
    
    <table>
        <tr>
            <th>글 번호</th>
            <td>${vNum}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${board.writer}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>${board.title}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td class="content-box">${board.content}</td>
        </tr>
        <tr>
            <th>최초 작성일</th>
            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
            <th>최종 수정일</th>
            <td><fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
    </table>

    <div class="btn-group">
        <button onclick="location.href='/myweb/board/list'">목록으로</button>
        <button onclick="location.href='/myweb/board/modify?bno=${board.bno}'">수정/삭제하기</button>
    </div>
</div>

</body>
</html>