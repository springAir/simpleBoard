<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>??</title>
</head>
<body>
    <h1>수정</h1>
    <form action="/board/${boardKeyName}/post/${post.id}" method="POST">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="id" value="${post.id}"/>
        <div>title : <input type="text" name="title" value="${post.title}"></div>
        <div>writer : <input type="text" name="writer" value="${post.writer}"/></div>
        <div>
            <textarea name="content">${post.content}</textarea>
        </div>
        <div>
            <input type="submit"/>
        </div>
    </form>
</body>
</html>
