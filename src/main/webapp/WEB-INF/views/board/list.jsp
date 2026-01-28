<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>130개 데이터 게시판</title>
<style>
    table { width: 100%; border-collapse: collapse; }
    th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
    .pagination { margin-top: 20px; text-align: center; }
    .pagination a { padding: 8px 16px; text-decoration: none; border: 1px solid #ddd; color: black; }
</style>
</head>
<body>

<h2>📋 게시판 목록 (130개 중 10개씩 보기)</h2>

<table>
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    
    <tbody>
        <tr style="background-color: #fff9e6;">
            <td>📢</td>
            <td><strong><a href="/myweb/board/get?bno=1">[공지] 우리 게시판 1호 글입니다!</a></strong></td>
            <td>관리자</td>
            <td>2026-01-28</td>
        </tr>
        
        <c:forEach items="${list}" var="board">
            <c:if test="${board.bno != 1}">
                <tr>
                    <td>${board.bno}</td>
                    <td>
                        <a href="/myweb/board/get?bno=${board.bno}">${board.title}</a>
                    </td>
                    <td>${board.writer}</td>
                    <td>${board.regdate}</td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>


<div class="pagination">
    <c:forEach var="num" begin="1" end="13">
        <a href="/myweb/board/list?pageNum=${num}" 
           style="${num == pageMaker.pageNum ? 'background-color: #ddd; font-weight: bold;' : ''}">
           ${num}
        </a>
    </c:forEach>
</div>
<button onclick="location.href='/myweb/board/register'" style="float: right; margin-bottom: 10px;">글쓰기</button>
</body>
</html>