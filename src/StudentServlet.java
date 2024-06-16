import entity.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            try {
                addStudent(request, response);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if ("query".equals(action)) {
            try {
                queryStudents(request, response);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if ("querySingle".equals(action)) {
            querySingleStudent(request, response);
        }
        else if ("delete".equals(action)) {
            try {
                deleteStudent(request, response);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String name = request.getParameter("name");
        String course = request.getParameter("course");
        int score = Integer.parseInt(request.getParameter("score"));

        String url = "jdbc:mysql://localhost:3306/mpdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String user = "root";
        String password = "123456";

        String sql = "INSERT INTO students (name, course, score) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, course);
            stmt.setInt(3, score);
            stmt.executeUpdate();
            request.setAttribute("message", "学生成绩添加成功！");
            request.setAttribute("color", "green");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addstudent.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Failed to add student. Please try again.");
            request.setAttribute("color", "red");
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void queryStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mpdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String user = "root";
        String password = "123456";

        String sql = "SELECT id, name, course, score FROM students";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setCourse(rs.getString("course"));
                student.setScore(rs.getInt("score"));
                students.add(student);
            }
        } catch (SQLException e) {
            // 添加日志输出以便排查问题
            System.out.println("数据库查询出错：" + e.getMessage());
            e.printStackTrace();
        }
        // 确保学生列表获取成功后再设置属性
        if (!students.isEmpty()) {
            for (Student student : students) {
                System.out.println(student.getId() + " " + student.getName() + " " + student.getCourse() + " " + student.getScore());
            }
            request.setAttribute("students", students);
        } else {
            // 如果学生列表为空，可以打印日志进行排查
            System.out.println("学生列表为空");
        }
        //request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
        dispatcher.forward(request, response);
    }
    private void querySingleStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null || studentIdParam.trim().isEmpty()) {
            // 如果学生ID为空，则设置错误信息到请求的属性中
            request.setAttribute("error", "学生ID不能为空");

            // 使用 RequestDispatcher 转发到原页面，让页面显示错误信息
            RequestDispatcher dispatcher = request.getRequestDispatcher("singleStudent.jsp");
            dispatcher.forward(request, response);
        }else {
            int studentId = Integer.parseInt(studentIdParam);
            Student student = null;
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mpdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
                String user = "root";
                String password = "123456";

                String sql = "SELECT id, name, course, score FROM students WHERE id=?";
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, studentId);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setCourse(rs.getString("course"));
                    student.setScore(rs.getInt("score"));

                    request.setAttribute("student", student);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("singleStudent.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // 如果未找到学生，记录日志并将错误信息设置到请求的属性中
                    System.out.println("未找到对应的学生");
                    request.setAttribute("error", "未找到对应的学生");

                    // 继续将学生信息设置到请求属性中，方便页面显示
                    request.setAttribute("student", student);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("singleStudent.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                // 关闭数据库连接和释放资源
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        boolean isDeleted = false;
        boolean idExists = true;

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mpdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String user = "root";
        String password = "123456";

        String selectSql = "SELECT id FROM students WHERE id = ?";
        String deleteSql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            // 检查 id 是否存在
            selectStmt.setInt(1, studentId);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (!rs.next()) {
                    idExists = false;
                }
            }

            if (idExists) {
                // 删除学生信息
                deleteStmt.setInt(1, studentId);
                int rowsAffected = deleteStmt.executeUpdate();
                isDeleted = rowsAffected > 0;
            }
        } catch (SQLException e) {
            // 添加日志输出以便排查问题
            System.out.println("删除学生信息出错：" + e.getMessage());
            e.printStackTrace();
        }

        // 将结果和是否存在 id 的信息传递到 JSP 页面
        request.setAttribute("isDeleted", isDeleted);
        request.setAttribute("idExists", idExists);
        RequestDispatcher dispatcher = request.getRequestDispatcher("deleteStudent.jsp");
        dispatcher.forward(request, response);
    }
}