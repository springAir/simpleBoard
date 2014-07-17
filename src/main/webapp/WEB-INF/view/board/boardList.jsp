<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
</head>
<body>

<ul>
    <c:forEach var="board" items="${boards}">
        <li>
            <a href="/board/${board.keyName}">${board.name}</a>
        </li>
    </c:forEach>
</ul>

</body>

</html>