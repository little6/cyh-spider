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
            width: 60%;
            padding: 0 5%;
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
            padding: 0 0 0 2px;
            background-color: #F5E6C9;
        }

        .pagination {
            display: block;
            padding: 0;
            width: 100%;
            height: 37px;
            margin: 5px auto;
            text-align: center;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            margin: 0 5px;
        }

        .pagination span {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            margin: 0 5px;
        }

        .pagination span {
            background-color: #4CAF50;
            color: white;
        }

        .pagination a:hover:not(.active) {
            background-color: #ddd;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid #e7e7e7;
        }

        nav li {
            align-content: center;
        }

        nav li a {
            display: block;
            color: #666;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav li a:hover:not(.active) {
            background-color: #ddd;
        }

        nav li a.active {
            color: white;
            background-color: #4CAF50;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>

</head>
<body>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/" class="active" title="首页">首页</a></li>
    </ul>
</nav>
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
        <div class="pagination">
        </div>
    </c:if>
    <c:if test="${books==null}">
        没有找到该小说
    </c:if>
</div>
</body>
<script>
    $('.pagination').pagination({
        //页面总数
        pageCount:${num},
        coping: true,
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        // activeCls:".pagination a.active",
        current:${page},
        callback: function (api) {
            $(window).attr('location', '${pageContext.request.contextPath}/search?key=${key}&page=' + api.getCurrent());
        }
    });
</script>
</html>
