import entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StuTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String name="张三";
        String course="语文";
        int score=89;
        Scanner sc=new Scanner(System.in);
        System.out.println("分别输入姓名，科目，成绩");
        name=sc.next();
        course=sc.next();
        score=sc.nextInt();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mybatis_demo";
        String user = "root";
        String password = "123456";

        String sql = "INSERT INTO students (name, course, score) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, course);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
