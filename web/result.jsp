<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        /* 设置链接的颜色和下划线 */
        .h2_bg{
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
        }
        .container1 {
            text-align: center;
        }

        /* 设置链接的颜色和下划线 */
        a {
            color: #007bff; /* 设置链接文字颜色为蓝色 */
            text-decoration: none; /* 去除下划线 */
            transition: color 0.3s ease; /* 添加颜色变化的过渡效果 */

            /* 设置链接水平居中 */
            display: block; /* 将链接转换为块级元素 */
            width: fit-content; /* 让链接的宽度适应内容 */
            margin: 0 auto; /* 设置左右外边距为自动，实现水平居中 */
        }

        /* 鼠标悬停时改变链接颜色 */
        a:hover {
            color: #0056b3; /* 设置鼠标悬停时链接文字颜色为深蓝色 */
        }

        /* 鼠标点击时改变链接颜色 */
        a:active {
            color: #003366; /* 设置鼠标点击时链接文字颜色为更深的蓝色 */
        }
         /* 统一样式规则 */
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

        /* 鼠标悬停时改变按钮颜色 */
        .myButton:hover {
            color: #0056b3;
        }

        /* 鼠标点击时改变按钮颜色 */
        .myButton:active {
            color: #003366;
        }
    </style>
</head>
<body>
<h2 class="h2_bg container1">Welcome, <%= request.getAttribute("username") %>!</h2>
<%--<a href="student.jsp" class="">查询学生信息</a>--%>
<!-- 在这里可以进行数据库查询，并将结果显示在页面上 -->
<form action="StudentServlet" method="post">
    <input type="hidden" name="action" value="query">
    <input type="submit" value="查询学生信息" class="myButton">
</form>
<a href="addstudent.jsp" class="myButton">插入学生信息</a>

<form action="StudentServlet" method="post" class="container1" onsubmit="return validateForm('deleteStudentId')">
    <label for="deleteStudentId">学生ID：</label>
    <input type="text" id="deleteStudentId" name="studentId">
    <input type="hidden" name="action" value="delete">
    <input type="submit" value="删除" class="myButton">
</form>

<form action="StudentServlet" method="post" class="container1" onsubmit="return validateForm('studentId')">
    <label for="studentId">学生ID：</label>
    <input type="text" id="studentId" name="studentId">
    <input type="hidden" name="action" value="querySingle">
    <input type="submit" value="查询" class="myButton">
</form>

</body>

<script>
    function validateForm(inputId) {
        var inputValue = document.getElementById(inputId).value;
        if (inputId === 'deleteStudentId' && inputValue.trim() === '') {
            alert('要删除的学生ID不能为空');
            return false;
        }
        return true;
    }
</script>
</html>