<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
</head>
<body>
<h1>${post.title}</h1>

<div>
    <span>writer</span>
    <span>${post.writer}</span>
</div>
<div>
    <span>date</span>
    <span>${post.writeDate}</span>
</div>
<div>
    <span>content</span>
    <br/>
    <span>${post.content}</span>
</div>

<div>
    <a href="/board/${boardKeyName}/post/${post.id}/edit?pageNumber=${pageNumber}">
        수정하기
    </a>
    <form action="/board/${boardKeyName}/post/${post.id}" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="submit" value="삭제하기">
    </form>
</div>

<div>
     <a href="/board/${boardKeyName}/page/${pageNumber}">
         목록으로
     </a>
</div>
</body>

</html>