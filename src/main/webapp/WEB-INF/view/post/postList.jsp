<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
</head>
<body>
<h1>게시판</h1>

<div>총 ${postContainer.totalPageNumber}페이지 중 ${postContainer.currentPageNumber}페이지</div>

<table>
    <thead>
    <tr>
        <td>number</td>
        <td>title</td>
        <td>writer</td>
        <td>date</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${postContainer.postList}" var="post">
        <tr>
            <td>${post.id}</td>
            <td><a href="/post/${post.id}">${post.title}</a></td>
            <td>${post.writer}</td>
            <td>${post.writeDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <c:choose>
        <c:when test="${currentPageNumber != 1}">
           <span>
               <a href="/board/${board.keyName}/${currentPageNumber - 1}">prev</a>
           </span>
        </c:when>
    </c:choose>

    <span>
        현재 페이지 : ${currentPageNumber}
    </span>

    <c:choose>
        <c:when test="${postContainer.currentPageNumber != postContainer.totalPageNumber}">
            <span>
                <a href="/board/${postContainer.board.keyName}/${postContainer.currentPageNumber + 1}">prev</a>
            </span>
        </c:when>
    </c:choose>
</div>
</body>

</html>