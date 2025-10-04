package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    // private static final String URL = "jdbc:mysql://localhost:3306/sampledb?useSSL=false&serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://localhost:13306/test_db";
    private static final String USER = "test_user"; // ←ご自身のユーザー名
    private static final String PASSWORD = "test_password"; // ←ご自身のパスワード

    // データを保持するためのインナークラス
    public static class User {
        public int id;
        public String name;
        public String email;
        public int age;
        public User(int id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        try {
            // JDBCドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
             throw new ServletException("JDBC Driver not found", e);
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, email, age FROM users")) {
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age")
                ));
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("/userlist.jsp").forward(req, resp);
    }

}
