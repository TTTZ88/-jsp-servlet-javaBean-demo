<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>addStudent</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
    }
    form {
      margin-top: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    input[type="text"] {
      padding: 10px;
      margin: 5px 0;
      width: 200px;
      box-sizing: border-box;
    }
    button {
      padding: 10px 20px;
      margin: 10px 0;
      background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
      color: #007bff;
      text-decoration: none;
      transition: color 0.3s ease;
      border: none;
      cursor: pointer;
    }
    button:hover {
      color: #0056b3;
    }
    button:active {
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
  </style>
</head>
<body>

<%request.setCharacterEncoding("UTF-8");%>
<%
  String message = (String)request.getAttribute("message");
  String color = (String)request.getAttribute("color");
  if (message != null) {
%>
<p style="color: <%= color %>;"><%= message %></p>
<% } %>

<form action="StudentServlet" method="post" onsubmit="return validateForm()">
  <input type="hidden" name="action" value="add">
  <input type="text" name="name" placeholder="Name" required>
  <input type="text" name="course" placeholder="Course" required>
  <input type="text" name="score" placeholder="Score" required>
  <button type="submit">Add Student</button>
</form>
<a href="result.jsp" class="myButton">返回</a>
<script>
  function validateForm() {
    var score = document.getElementsByName('score')[0].value;

    if (isNaN(score)) {
      alert('分数必须是数字');
      return false;
    }

    return true;
  }
</script>
</body>
</html>