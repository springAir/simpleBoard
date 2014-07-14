<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>??</title>
</head>
<body>
    <h1>수정</h1>
    <form action="/board/${boardKeyName}/post/${post.id}">
        <input type="hidden" name="_method" value="_PUT"/>
        <div>title : <input type="text" name="title">${post.title}</div>
        <div>writer : <input type="text" name="writer"/>${post.writer}</div>
        <div>
            <textarea name="content">${post.content}</textarea>
        </div>
        <div>
            <input type="submit"/>
        </div>
    </form>
</body>
</html>
