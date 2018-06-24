<%--
  Created by IntelliJ IDEA.
  User: chaterhower
  Date: 2018/6/23
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${novelsLists}" var="novelsList">
    <p style="color: #32ff21;">${novelsList.type}</p>
    ${novelsList.top.title}
    <c:forEach items="${novelsList.books}" var="book">
        <p style="color:#b64a50;">
            <a href="${book.bookUrl}">${book.title}</a>
        </p>
    </c:forEach>
</c:forEach>
</body>
</html>
