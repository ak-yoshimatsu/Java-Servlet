package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // レスポンスのコンテンツタイプおよびエンコーディング方式を指定
        response.setContentType("text/html; charset=UTF-8");

        // レスポンス書き出し用オブジェクトの取得
        PrintWriter out = response.getWriter();

        // レスポンスの書き出し
        out.println("<!DOCTYPE html>                                 ");
        out.println("<html>                                          ");
        out.println("<head>                                          ");
        out.println("<meta charset=\"UTF-8\">                        ");
        out.println("<title>First Servlet</title>                    ");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">    ");
        out.println("</head>                                         ");
        out.println("<body>                                          ");
        out.println("    <h1>サーブレット</h1>                       ");
        out.println("    <h2>最初の表示</h2>                         ");
        out.println("    <p>Hello Servlet !</p>                      ");
        out.println("</body>                                         ");
        out.println("</html>                                         ");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        // テストメールサーバーの情報を設定
        String host = "your_smtp_server_address";
        int port = 587; // SMTPSのデフォルトポート
        String username = "your_email_address";
        String password = "your_password";
        String fromAddress = "your_email_address";
        String toAddress = "test_recipient@example.com";
        String subject = "テストメール";
        String body = "このメールはテストです。";

        // SMTPサーバーへの接続設定
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // SMTPSの場合
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);

//        // セッションを作成
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(fromAddress));
//        message.setRecipients(Message.RecipientType.TO, toAddress);
//        message.setSubject(subject);
//        message.setText(body);
//
//        // メールを送信
//        Transport.send(message);
//
//        System.out.println("メールを送信しました。");

        doGet(request, response);
    }

}
