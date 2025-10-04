package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entity.DbRecord;

/**
 * Servlet implementation class DataBaseServlet
 */
@WebServlet("/db")
public class DataBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataBaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // データベース接続設定
	    String url = "jdbc:mysql://localhost:13306/test_db";
	    String user = "test_user";
	    String password = "test_password";
	    
//	    try (Connection con = DriverManager.getConnection(url, user, password)) {
//	        System.out.println("\n接続しました。");
//	    } catch (SQLException e) {
//            // TODO 自動生成された catch ブロック
//            e.printStackTrace();
//            System.out.println("\nエラーが発生しました。");
//        }
//	    

//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // 1. JDBCドライバのロード (Java 6以降では不要になることが多いですが、明示的に記述することもあります)
//            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8.0以降の場合。以前のバージョンでは "com.mysql.jdbc.Driver"
//
//            // 2. データベースへの接続
//            conn = DriverManager.getConnection(url, user, password);
//            out.println("<h1>データベースに接続しました！</h1>");
//
//            // 3. SQLステートメントの作成と実行 (例: テーブルからデータを取得)
//            stmt = conn.createStatement();
//            String sql = "SELECT id, title FROM posts"; // 適切なテーブル名とカラム名に修正
//            rs = stmt.executeQuery(sql);
//
//            out.println("<table border='1'>");
//            out.println("<tr><th>ID</th><th>Name</th></tr>");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                out.println("<tr><td>" + id + "</td><td>" + title + "</td></tr>");
//            }
//            out.println("</table>");
//
//        } catch (ClassNotFoundException e) {
//            out.println("<h1>JDBCドライバのロードに失敗しました。</h1>");
//            e.printStackTrace(out);
//        } catch (SQLException e) {
//            out.println("<h1>データベース接続エラーが発生しました。</h1>");
//            e.printStackTrace(out);
//        } finally {
//            // 4. リソースのクローズ (逆順でクローズするのが一般的)
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                out.println("<h1>リソースのクローズに失敗しました。</h1>");
//                e.printStackTrace(out);
//            }
//        }
	    
	 // 1. JDBCドライバのロード (通常は一度で十分)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8.0以降
            System.out.println("JDBCドライバをロードしました。");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバが見つかりません。JARファイルが正しく配置されているか確認してください。");
            e.printStackTrace();
            return; // ドライバがないと以降の処理はできないので終了
        }

        // 2. try-with-resources を使ったデータベース接続と操作
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement(); // StatementもAutoCloseable
//             ResultSet rs = stmt.executeQuery("SELECT id, name FROM employees")) { // ResultSetもAutoCloseable
                ResultSet rs = stmt.executeQuery("SELECT e.id, e.mail, e.name, d.age, d.birthplace, d.height, d.weight FROM employees e JOIN employee_details d ON d.user_id = e.uid")) { // ResultSetもAutoCloseable

            System.out.println("データベースに接続しました！");

            while (rs.next()) {
                DbRecord record = new DbRecord(
                        rs.getString("mail"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("birthplace"),
                        rs.getInt("height"),
                        rs.getInt("weight")
                );
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                System.out.println("ID: " + id + ", Name: " + name);
                System.out.println("表示");
                System.out.println("取得レコード: " + record);
            }

        } catch (SQLException e) {
            System.err.println("データベース操作中にエラーが発生しました。");
            e.printStackTrace();
        }
	    
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
