<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="./css1.css">
</head>
<body>
<%-- 在登录页面中显示错误信息 --%>
<style>
    .error-message {
        color: red;
        font-size: 14px;
        margin-top: 5px;
    }
</style>
<div class="container">
    <div class="login-wrapper">
        <div class="header">Login</div>
        <div class="">
            <form action="/login001" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required class="input-item"/><br/>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required class="input-item"/><br/>
                <input type="submit" class="btn" value="Login"/>
            </form>
        </div>
    </div>
</div>
<div class="input-container">
    <input type="text" class="input-item" placeholder="Username" name="username" value="<%= request.getParameter("username") %>">
    <div class="error-message">
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <%= error %>
        <% } %>
    </div>
</div>

</body>
</html>