<%--
  Created by IntelliJ IDEA.
  User: chaterhower
  Date: 2018/7/20
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导航</title>
    <style>

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid #e7e7e7;
            background-color: #f3f3f3;
        }

        nav li {
            float: left;
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
</head>
<body>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/" class="active" title="首页">首页</a></li>
    </ul>
</nav>
</body>
</html>
