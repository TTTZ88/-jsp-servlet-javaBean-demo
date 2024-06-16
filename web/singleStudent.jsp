<%@ page import="entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        h2 {
            background-color: #f2f2f2;
            padding: 10px;
        }
        p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
<h2>学生信息</h2>
<%
    String error = (String)request.getAttribute("error");
    if (error != null && !error.isEmpty()) {
%>
<p>Error: <%= error %></p>
<%
} else {
    Student student = (Student) request.getAttribute("student");
    if (student != null) {
%>
<!-- 显示学生信息的部分 -->
<p>ID: <%= student.getId() %></p>
<p>Name: <%= student.getName() %></p>
<p>Course: <%= student.getCourse() %></p>
<p>Score: <%= student.getScore() %></p>
<%
} else {
%>
<p>No student information available</p>
<%
        }
    }
%>
</body>
</html>