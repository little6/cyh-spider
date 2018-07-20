<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chaterhower
  Date: 2018/6/24
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.title}</title>
    <style>
        article {
            font-size: 20px;
            line-height: 32px;
            width: 80%;
            overflow: hidden;
            text-align: left;
            padding: 10px 10% 15px 10%;
            border: 1px solid burlywood;
            background-color: burlywood;
        }

        header {
            display: block;
            width: 100%;
        }

        .content {
            display: block;
            margin-left: 20%;
            margin-right: 20%;
        }

        h1 {
            display: block;
            margin: 0 auto;
            text-align: center;
        }

        .chapterNav {
            display: block;
            text-align: center;
        }

        .chapterNav a {
            font-size: 2em;
            display: inline-block;
            text-decoration-line: none;
            margin: 0 10%;
            color: #32291d;
        }

        body {
            background-color: #ecc896;
        }
    </style>
</head>
<body>


<header>
    <h1>${article.title}</h1>
</header>

<div class="content">
    <div class="chapterNav">
        <c:if test="${previous==null}">
            <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
        </c:if>
        <c:if test="${previous!=null}">
            <a href="${pageContext.request.contextPath}/${previous}">上一章</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/${article.bookUrl}">章节目录</a>
        <c:if test="${next==null}">
            <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
        </c:if>
        <c:if test="${next!=null}">
            <a href="${pageContext.request.contextPath}/${next}">下一章</a>
        </c:if>
    </div>

    <article>
        ${article.content}
    </article>
    <div class="chapterNav">
        <c:if test="${previous==null}">
            <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
        </c:if>
        <c:if test="${previous!=null}">
            <a href="${pageContext.request.contextPath}/${previous}">上一章</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/${article.bookUrl}">章节目录</a>
        <c:if test="${next==null}">
            <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
        </c:if>
        <c:if test="${next!=null}">
            <a href="${pageContext.request.contextPath}/${next}">下一章</a>
        </c:if>
    </div>
</div>
</dl>


</body>
</html>
