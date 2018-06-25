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
            width: 100%;
            overflow: hidden;
            text-align: left;
            padding: 10px 0px 15px 0px;
        }

        header {
            display: block;
            width: 100%;
        }
        .content {
            display: block;
            margin-left: 30%;
            margin-right: 30%;
        }
        h1 {
            display: block;
            margin: 0 auto;
            text-align: center;
        }
    </style>
</head>
<body>
<header>
    <h1>${article.title}</h1>
</header>

<div class="content">
    <article>
        ${article.content}
    </article>
</div>
</dl>


</body>
</html>
