import entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mybatis_demo";
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
            e.printStackTrace();
        }
        for (Student student : students) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Score: " + student.getScore());
            System.out.println("------------------------");
        }
    }
}