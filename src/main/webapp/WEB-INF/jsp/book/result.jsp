<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chaterhower
  Date: 2018/6/26
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索结果</title>
    <style>
        body {
            background-color: #F1E1BF;
        }

        .container {
            display: block;
            width: 50%;
            margin: 0 auto;
        }

        .table-header {
            text-align: center;
            font-size: 2em;
        }

        table {
            width: 100%;
            border: 1px solid black;
            border-collapse: collapse;
            font-size: 1em;
        }

        td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 0;
            background-color: #F5E6C9;
        }

    </style>
</head>
<body>
<div class="container">
    <c:if test="${books!=null}">
        <table>
            <div class="table-header">
                搜索结果
            </div>
            <tbody>
            <tr>
                <th>小说名称</th>
                <th>最新章节</th>
                <th>作者</th>
                <th>字数</th>
                <th>更新时间</th>
                <th>状态</th>
            </tr>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><a href="${book.bookUrl}">${book.title}</a></td>
                    <td><a href="/${book.bookUrl}/${book.latestChapterUrl}.html">${book.latestChapterTitle}</a></td>
                    <td>${book.author}</td>
                    <td>${book.wordCount}</td>
                    <td>${book.updateTime}</td>
                    <td>${book.status}</td>
                </tr>
            </c:forEach>
            </tbody>



        </table>
    </c:if>
    <c:if test="${books==null}">
        没有找到该小说
    </c:if>
</div>
</body>
</html>
