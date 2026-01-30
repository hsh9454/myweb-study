<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

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

<h2>게시판 목록 (130개 중 10개씩 보기)</h2>

<div style="text-align: right; margin-bottom: 10px;">
    <c:choose>
      
        <c:when test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/board/login">로그인</a>
        </c:when>
        
        
        
        
        <c:otherwise>
            <strong>${sessionScope.user.username}님 환영합니다!</strong>
            <a href="${pageContext.request.contextPath}/board/logout" style="margin-right: 10px;">로그아웃</a>
            
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/board/register'">
                새 글 등록
            </button>
        </c:otherwise>
    </c:choose>
</div>




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
            <td>🥶</td>
            <td><strong><a href="/myweb/board/get?bno=1">[공지] 감기 조심하세요 !! </a></strong></td>
            <td>관리자</td>
            <td>2026-01-28</td>
        </tr>
    
    <c:forEach items="${list}" var="board" varStatus="status">
        <c:set var="currentNum" value="${total - ((pageMaker.pageNum - 1) * 10) - status.index}" />
        <tr>
            <td>${currentNum}</td>
        
            <td>
            <a href="/myweb/board/get?bno=${board.bno}&num=${currentNum}">
                ${board.title}
            </a>
            </td>
            <td>${board.writer}</td>
            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </c:forEach>
  
     </tbody>
</table>


<div class="pagination">
    <c:forEach var="num" begin="1" end="${totalPages}">
        <a href="/myweb/board/list?pageNum=${num}" 
           style="${num == pageMaker.pageNum ? 'background-color: #ddd; font-weight: bold;' : ''}">
           ${num}
        </a>
    </c:forEach>
</div>
<c:if test="${not empty sessionScope.user}">
    <button type="button" onclick="location.href='/board/register'" style="float:right;">글쓰기</button>
</c:if>

</body>
</html>