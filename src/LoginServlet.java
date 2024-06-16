import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static void main(String[] args) {
        Statement stmt=null;
        ResultSet rs=null;
        Connection conn =null;
        // JDBC连接数据库，查询用户名和密码是否匹配
        String dbUrl = "jdbc:mysql://localhost:3306/mpdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String dbUsername = "root";
        String dbPassword = "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            //3、通过Connection对象获取Statement对象
            stmt=conn.createStatement();
            //4、使用Statement执行SQL语句
            String sql="select * from users";
            rs=stmt.executeQuery(sql);
            //5、操作ResultSet结果集
            //System.out.println(rs.toString());
            System.out.println("id  |  username  |  password");
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("username");
                String psw=rs.getString("password");
                //rs.toString();
                //System.out.println(rs.toString());
                System.out.println(id + "  |  "+name+"  |  "+psw);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Statement stmt=null;
        ResultSet rs=null;
        Connection conn =null;
        // JDBC连接数据库，查询用户名和密码是否匹配
        String dbUrl = "jdbc:mysql://localhost:3306/mpdemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        String dbUsername = "root";
        String dbPassword = "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 登录成功，将用户名传递到结果页面
                req.setAttribute("username", username);
                RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
                dispatcher.forward(req, resp);
            } else {
//                // 登录失败，重定向回登录页面
//                req.setAttribute("error","密码或账号不正确，请重新输入。");
//                resp.sendRedirect("login.jsp");
//                 登录失败，将错误消息和用户名传递回到登录页面
//                req.setAttribute("error", "密码或账号不正确，请重新输入。");
//                req.setAttribute("username", username);
//                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
//                dispatcher.forward(req, resp);                 登录失败，重定向回登录页面并传递错误消息和用户名
                String errorMessage = "密码或账号不正确，请重新输入。";
                resp.sendRedirect("login.jsp?error=" + URLEncoder.encode(errorMessage, "UTF-8") + "&username=" + URLEncoder.encode(username, "UTF-8"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

//3、通过Connection对象获取Statement对象
//            stmt=conn.createStatement();
//4、使用Statement执行SQL语句
//            String sql="select * from users";
//            rs=stmt.executeQuery(sql);

//            //5、操作ResultSet结果集
//            System.out.println(rs.toString());
//            System.out.println("id  |  name  |  password  | email  | birthday");
//            while(rs.next()){
//                int id=rs.getInt("id");
//                String name=rs.getString("name");
//                String psw=rs.getString("password");
//                //rs.toString();
//                //System.out.println(rs.toString());
//                System.out.println(id + "  |  "+name+"  |  "+psw);
//            }