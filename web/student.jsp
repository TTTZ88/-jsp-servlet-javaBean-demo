<%@ page import="entity.Student" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Students List</title>
  <style>
    table {
      margin: 0 auto; /* 将表格水平居中 */
      border-collapse: collapse; /* 合并表格边框 */
      width: 80%; /* 设置表格宽度为页面宽度的 80% */
    }
    th, td {
      padding: 10px; /* 设置单元格内边距 */
      border: 1px solid #ddd; /* 设置单元格边框 */
    }
    th {
      background-color: #f2f2f2; /* 设置表头背景色 */
    }
    tr:nth-child(even) {
      background-color: #f9f9f9; /* 设置偶数行背景色 */
    }
    tr:hover {
      background-color: #f5f5f5; /* 设置鼠标悬停时行背景色 */
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
  </style>
</head>
<body>

<%
  List<Student> students = (List<Student>) request.getAttribute("students");
  if (students != null && !students.isEmpty()) {
%>
<table>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Course</th>
    <th>Score</th>
  </tr>
  <% for (Student student : students) { %>
  <tr>
    <td><%= student.getId() %></td>
    <td><%= student.getName() %></td>
    <td><%= student.getCourse() %></td>
    <td><%= student.getScore() %></td>
  </tr>
  <% } %>
</table>
<%
} else {
%>
<p>学生信息不存在，请稍后再试！</p>
<%
  }
%>
<a href="result.jsp" class="myButton">返回</a>
</body>
</html>