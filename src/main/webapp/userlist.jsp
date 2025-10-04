<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="control.UserServlet.User" %>
<html>
<head><title>User List</title></head>
<body>
  <h1>User List</h1>
  <table border="1">
    <tr><th>ID</th><th>Name</th><th>Email</th><th>Age</th></tr>
    <%
      List<User> users = (List<User>)request.getAttribute("users");
      if (users != null) {
        for (User u : users) {
    %>
    <tr>
      <td><%= u.id %></td>
      <td><%= u.name %></td>
      <td><%= u.email %></td>
      <td><%= u.age %></td>
    </tr>
    <%   }
      }
    %>
  </table>
</body>
</html>