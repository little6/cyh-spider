<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chaterhower
  Date: 2018/6/24
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>小说</title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }

        a{
            text-decoration: none;
        }

        body{
            width: 100%;
        }

        footer{
            width: 100%;
        }

        main{
            width: 80%;
            margin: 0 auto;
        }

        .top{
            border-bottom: 2px solid #008792;
            margin-bottom: 0.5%;
            padding-top: 2%;
            padding-bottom: 2%;
            height: 20%;
        }

        .photo{
            width: 10%;
            margin-left: 5%;
            display: inline-block;
            background-color: #eee;
            /*     border: 1px solid black; */
            opacity: 0.8;
            padding: 0 0;
        }

        img{
            width: 100%;
            height: 100%;
        }

        .right{
            width: 77%;
            float: right;
            margin-left: 2%;
        }

        h4{
            width: 49%;
            display: inline-block;
            margin-top: 2%;
        }

        .right-bottom{
            border-top: 1px dotted #008792;
            padding-top: 2%;
        }

        .right-bottom > p{
            text-indent: 2em;
        }

        .clearfix{
            content: "";
            clear: both;
            overflow: hidden;
        }

        article{
            border-top: 2px solid #008792;
        }

        dl{
            border-bottom: 1px dotted #008792;
        }

        dl > dd {
            width: 33%;
            display: inline-block;
            padding: 1% 0;
            border-bottom: 1px dotted #008792;

        }

        @media (max-width: 1200px){
            .photo{
                display: block;
                width: 100%;
            }

            .right{
                width: 100%;
            }

            h4{
                /* font-size: 12px; */
                width: 100%;
            }

            dl{
                border: none;
            }

            dl > dd{
                width: 100%;
                display: block;
                border-bottom: 1px dotted #008792;
            }
        }
    </style>
</head>
<body>
<jsp:include page="../nav.jsp"/>


<main>
    <div class="top clearfix">
        <div class="photo"><img src="${pageContext.request.contextPath}/${book.titlePageUrl}"></div>
        <div class="right">
            <h1>${book.title}</h1>
            <div>
                <h4>${book.author}</h4>
                <h4>最后更新：${book.updateTime}</h4>
                <h4>最新章节：<a href="${pageContext.request.contextPath}/${book.bookUrl}/${book.latestChapterUrl}.html">${book.latestChapterTitle}</a></h4>
                <c:if test="${record!=null}">
                    <h4>上次阅读：<a
                            href="${pageContext.request.contextPath}/${record.bookUrl}/${record.url}.html">${record.title}</a>
                    </h4>
                </c:if>
            </div>
            <div class="right-bottom">
                <p>
                    ${book.intro}
                </p>
            </div>
        </div>
    </div>

    <article>
        ${book.chapterPage}
    </article>
</main>
</body>
</html>