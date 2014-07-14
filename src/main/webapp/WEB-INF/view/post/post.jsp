<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</body>

</html>