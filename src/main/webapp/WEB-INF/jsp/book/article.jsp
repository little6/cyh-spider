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
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        *{
            padding: 0;
            margin: 0;
        }
        a{
            text-decoration: none;
            color: #000000;
        }
        body{
            width: 100%;
            background: #FDF5E6;
        }
        .back{
            width: 45px;
            height: 45px;
            line-height: 45px;
            text-align: center;
            position: fixed;
            left: 78%;
            border-radius: 22.5px;
            font-size: 14px;
            border: 1px solid #C1CDCD;
        }
        .up{
            top: 8%;
        }
        .down{
            top:16%;
        }
        #main{
            width: 56%;
            margin: 0 auto;
            background: #EEE8CD;
            border: 1px solid #C1CDCD;
        }
        #main-content{
            width: 85%;
            margin: 0 auto;
            /*border: 1px solid #FFDAB9;*/
        }
        h1{
            width: 36%;
            text-align: center;
            /*border: 1px solid #FFDAB9;*/
            margin: 20px auto;
        }
        .nav{
            width: 90%;
            margin: 10px auto;
            text-align: center;
            font-size: 14px;
            color: grey;
            display: block;
            /*border: 1px solid black;*/
        }
        .nav a{
            color: grey;
            margin-right: 6px;
        }
        .nav a:hover{
            color: purple;
        }
        .line{
            width: 1px;
            height: 12px;
            display: inline-block;
            margin: 8px 6px 0;
            border-left: 1px solid grey;
        }
        .content{
            margin-top: 20px;
        }
        .content p{
            text-indent: 2em;
            margin-top: 8px;
            font-size: 14px;
            color: #4F4F4F;
        }
        #btn{
            width: 60%;
            margin: 60px auto;
            /*border: 1px solid black;*/
        }
        .btn-content{
            width: 49.5%;
            display: inline-block;
            text-align: center;
            /*border: 1px solid black;*/
        }
        .btn-content:nth-child(2){
            display: none;
        }
        .font{
            color: grey;
        }
        .btn-content:last-child{
            float: right;
            border-left: 1px solid grey;
        }

        @media screen and (max-width: 800px) {
            .back{
                display: none;
            }
            #main{
                width: 100%;
                margin: 0 auto;
                background: #EEE8CD;
                border: 1px solid #C1CDCD;
            }
            .nav{
                display: none;
            }
            #btn{
                width: 90%;
                margin: 60px auto;
                /*border: 1px solid black;*/
            }
            .btn-content{
                width: 32%;
                display: inline-block;
                text-align: center;
                /*border: 1px solid black;*/
            }
            .btn-content:nth-child(2){
                display: inline-block;
                border-left: 1px solid grey;
            }
            .font{
                display: none;
            }
        }
    </style>
</head>
<body>



<c:if test="${previous==null}">
    <div class="back up">
        <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
    </div>
</c:if>
<c:if test="${previous!=null}">
    <div class="back up">
        <a href="${pageContext.request.contextPath}/${previous}">上一章</a>
    </div>
</c:if>
<div class="back">
    <a href="${pageContext.request.contextPath}/${article.bookUrl}">目  录</a>
</div>
<c:if test="${next==null}">
    <div class="back down">
        <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
    </div>
</c:if>
<c:if test="${next!=null}">
    <div class="back down">
        <a href="${pageContext.request.contextPath}/${next}">下一章</a>
    </div>
</c:if>
<div id="main">
    <div id="main-content">
        <h1>${article.title}</h1>
        <!-- <p class="nav">书名：<a href="">xxx</a><span class="line"></span>作者：<a href="">xxx</a>
            <span class="line"></span>书本类别：<a href="">xxx</a><span class="line"></span>更新时间：<a href="">xxx</a></p> -->
        <div class="content">
            ${article.content}
        </div>
        <div id="btn">
            <c:if test="${previous==null}">
                <div class="btn-content">
                    <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
                </div>
            </c:if>
            <c:if test="${previous!=null}">
                <div class="btn-content">
                    <a href="${pageContext.request.contextPath}/${previous}">上一章</a>
                </div>
            </c:if>
            <div class="back">
                <a href="${pageContext.request.contextPath}/${article.bookUrl}">章节目录</a>
            </div>
            <c:if test="${next==null}">
                <div class="btn-content">
                    <a href="${pageContext.request.contextPath}/${article.bookUrl}">没有了</a>
                </div>
            </c:if>
            <c:if test="${next!=null}">
                <div class="btn-content">
                    <a href="${pageContext.request.contextPath}/${next}">下一章</a>
                </div>
            </c:if>
        </div>
    </div>

</div>
</body>
</html>
