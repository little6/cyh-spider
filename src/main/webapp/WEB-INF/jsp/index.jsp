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
    <title>秋风引</title>
    <style>
        * {
            padding: 0;
            margin: 0;
            font-family: microsoft yahei;
        }

        body {
            width: 100%;
        }

        li {
            list-style: none;
        }

        .clearfix {
            content: "";
            overflow: hidden;
            clear: both;
            height: 10%;
        }

        /*搜索框*/
        .bar6 input {
            border: 2px solid #c5464a;
            border-radius: 5px;
            background: transparent;
            top: 0;
            right: 0;
        }

        .bar6 button {
            background: #c5464a;
            border-radius: 0 5px 5px 0;
            width: 60px;
            top: 0;
            right: 0;
        }

        .bar6 button:before {
            content: "搜索";
            font-size: 13px;
            color: #F9F0DA;
        }

        div.search {
            padding: 10px 0;
            margin: 2% 10% 0 70%;
        }

        form {
            position: relative;
            width: 300px;
            margin: 0 auto;
        }

        input, button {
            border: none;
            outline: none;
        }

        input {
            width: 100%;
            height: 42px;
            padding-left: 13px;
        }

        button {
            height: 42px;
            width: 42px;
            cursor: pointer;
            position: absolute;
        }

        /*搜索框结束*/

        .main {
            width: 80%;
            border: 2px solid #c5464a;
            margin: 0 auto 0.6%;
        }

        .content {
            width: 30.5%;
            display: inline-block;
            padding-left: 2%;
        }

        .contentLine {
            border-left: 1px dotted #c5464a;
            border-right: 1px dotted #c5464a;
        }

        .title-word {
            width: 92%;
            border-bottom: 1px solid #c5464a;
        }

        .top {
            margin: 3% 0;
            width: 100%;
        }

        span {
            width: 20%;
            float: left;
        }

        img {
            width: 100%;
        }

        .top-right {
            width: 75%;
            float: right;
            margin-left: 5%;
        }

        h4 {
            margin-bottom: 2%;
        }

        p {
            text-indent: 2em;
        }

        .bottom {
            width: 100%;
        }

        .bottom > ul > li {
            display: inline-block;
            width: 49%;
            border-bottom: 1px dotted #c5464a;
            padding-bottom: 1%;
            margin-bottom: 1%;
        }

        @media (max-width: 980px) {

            /*搜索框*/
            .bar6 input {
                border: 2px solid #c5464a;
                border-radius: 5px;
                background: transparent;
                top: 0;
                right: 0;
            }

            .bar6 button {
                background: #c5464a;
                border-radius: 0 5px 5px 0;
                width: 60px;
                top: 0;
                right: 0;
            }

            .bar6 button:before {
                content: "搜索";
                font-size: 13px;
                color: #F9F0DA;
            }

            div.search {
                padding: 10px 0;
                margin: 0 10% 0 60%;
            }

            form {
                position: relative;
                width: 300px;
                margin: 0 auto;
            }

            input, button {
                border: none;
                outline: none;
            }

            input {
                width: 100%;
                height: 42px;
                padding-left: 13px;
            }

            button {
                height: 42px;
                width: 42px;
                cursor: pointer;
                position: absolute;
            }

            /*搜索框结束*/
            .content {
                width: 99%;
                display: block;
                border: 2px solid #c5464a;
                margin-bottom: 2%;
                padding-right: 1%;
            }

            .bottom {
                text-align: center;
            }
        }

        @media (max-width: 700px) {
            .main {
                border-style: none;
            }

            /*搜索框*/
            .bar6 input {
                border: 2px solid #c5464a;
                border-radius: 5px;
                background: transparent;
                top: 0;
                right: 0;
            }

            .bar6 button {
                background: #c5464a;
                border-radius: 0 5px 5px 0;
                width: 60px;
                top: 0;
                right: 0;
            }

            .bar6 button:before {
                content: "搜索";
                font-size: 13px;
                color: #F9F0DA;
            }

            div.search {
                padding: 10px 0;
                margin: 0 10% 0 70%;
            }

            form {
                position: relative;
                width: 300px;
                margin: 0 auto;
            }

            input, button {
                border: none;
                outline: none;
            }

            input {
                width: 100%;
                height: 42px;
                padding-left: 13px;
            }

            button {
                height: 42px;
                width: 42px;
                cursor: pointer;
                position: absolute;
            }

            /*搜索框结束*/
            .content {
                width: 99%;
                display: block;
                border: 2px solid #c5464a;
                margin-bottom: 2%;
                padding-right: 1%;
            }

            .bottom {
                text-align: center;
            }
        }

    </style>
</head>
<body>

<jsp:include page="nav.jsp"/>
<div class="clearfix">
    <div class="search bar6">
        <form method="get" action="${pageContext.request.contextPath}/search">
            <input type="text" placeholder="请输入小说或者作者名字" name="key">
            <button type="submit"></button>
        </form>
    </div>
</div>
<div class="main">

    <c:forEach items="${novelsLists}" var="novelsList">

        <div class="content">
            <div class="title-word">
                    <%--小说分类--%>
                <h3>${novelsList.type}</h3>
            </div>
            <div class="top clearfix">
                <span><img src="${pageContext.request.contextPath}/${novelsList.top.titlePageUrl}"></span>
                <div class="top-right">
                    <h4><a href="${novelsList.top.bookUrl}">${novelsList.top.title}</a></h4>
                    <p>${novelsList.top.intro}</p>
                </div>
            </div>
            <div class="bottom">
                <ul>
                    <c:forEach items="${novelsList.books}" var="book">
                        <li>
                            <a href="${book.bookUrl}">${book.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:forEach>

</div>
<footer style="text-align: center">
    本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。
</footer>
</body>
</html>
