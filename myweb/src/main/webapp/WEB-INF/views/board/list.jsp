<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>130개 데이터 게시판</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        .pagination { margin-top: 20px; text-align: center; }
        .pagination a { padding: 8px 16px; text-decoration: none; border: 1px solid #ddd; color: black; }
        .search-area { margin-bottom: 20px; margin-top: 20px; }
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

    <div class="search-area">
        <form id='searchForm' action="/myweb/board/list" method='get'>
            <select name='type' style="height: 30px;">
                <option value="" ${pageMaker.cri.type == null?'selected':''}>--</option>
                <option value="T" ${pageMaker.cri.type eq 'T'?'selected':''}>제목</option>
                <option value="C" ${pageMaker.cri.type eq 'C'?'selected':''}>내용</option>
                <option value="W" ${pageMaker.cri.type eq 'W'?'selected':''}>작성자</option>
                <option value="TC" ${pageMaker.cri.type eq 'TC'?'selected':''}>제목 or 내용</option>
                <option value="TW" ${pageMaker.cri.type eq 'TW'?'selected':''}>제목 or 작성자</option>
                <option value="TWC" ${pageMaker.cri.type eq 'TWC'?'selected':''}>제목 or 내용 or 작성자</option>
            </select>
            <input type='text' name='keyword' value='${pageMaker.cri.keyword}' style="height: 30px;" />
            <input type='hidden' name='pageNum' value='1' /> <input type='hidden' name='amount' value='${pageMaker.cri.amount}' />
            <button class='btn btn-default' style="border: 1px solid #ccc; cursor:pointer;">검색</button>
        </form>
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
                
                <tr>
                    <td>${status.count}</td>
                    <td>
                        <a href="/myweb/board/get?bno=${board.bno}&num=${status.count}">
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
    <c:if test="${pageMaker.prev}">
        <a href="/myweb/board/list?pageNum=${pageMaker.startPage - 1}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}"> [이전] </a>
    </c:if>

    <c:choose>
        <c:when test="${pageMaker.total == 0}">
            <a href="#" style="background-color: #ddd; font-weight: bold;">1</a>
        </c:when>
        <c:otherwise>
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <a href="/myweb/board/list?pageNum=${num}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}" 
                   style="${num == pageMaker.cri.pageNum ? 'background-color: #ddd; font-weight: bold;' : ''}">
                   ${num}
                </a>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <c:if test="${pageMaker.next}">
        <a href="/myweb/board/list?pageNum=${pageMaker.endPage + 1}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}"> [다음] </a>
    </c:if>
</div>

</body>
</html>