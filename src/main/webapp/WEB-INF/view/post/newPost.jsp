<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>??</title>
</head>
<body>
    <h1>글쓰기</h1>
    <form action="/board/${boardKeyName}/post" method="POST">
        <div>title : <input type="text" name="title"/></div>
        <div>writer : <input type="text" name="writer"/></div>
        <div>
            <textarea name="content"></textarea>
        </div>
        <div>
            <input type="submit"/>
        </div>
    </form>
</body>
</html>
