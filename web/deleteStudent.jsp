<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除学生信息</title>
    <style>
        .h2_bg {
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
            padding: 10px 0; /* 添加上下内边距 */
            text-align: center; /* 文本水平居中 */
            margin-bottom: 20px; /* 底部外边距 */
        }

        .container1 {
            text-align: center;
        }

        a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
            display: block;
            width: fit-content;
            margin: 0 auto;
        }

        a:hover {
            color: #0056b3;
        }

        a:active {
            color: #003366;
        }

        .myButton {
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
            display: block;
            width: fit-content;
            margin: 0 auto;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            text-align: center;
        }

        .myButton:hover {
            color: #0056b3;
        }

        .myButton:active {
            color: #003366;
        }
    </style>
</head>
<body>
<h2 class="h2_bg">删除学生信息</h2>
<%
    boolean isDeleted = (boolean) request.getAttribute("isDeleted");
    boolean idExists = (boolean) request.getAttribute("idExists");
    if (idExists) {
        if (isDeleted) {
%>
<p style="text-align: center;">学生信息删除成功！</p>
<% } else { %>
<p style="text-align: center;">学生信息删除失败，请重试。</p>
<% }
} else { %>
<p class="error">指定的学生信息不存在，请确认后重试。</p>
<% } %>
<a href="result.jsp" class="myButton">返回</a>
</body>
</html>