<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des utilisateurs</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Liste des utilisateurs</h2>

<table>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Âge</th>
        <th>Action</th>
    </tr>

<%
    String url = "jdbc:mysql://localhost:3306/ma_base"; // remplace "ta_base" par ta base réelle
    String username = "root";
    String password = "";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);

        String sql = "SELECT id, nom, prenom, age FROM user";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
%>
    <tr>
        <td><%= rs.getString("nom") %></td>
        <td><%= rs.getString("prenom") %></td>
        <td><%= rs.getInt("age") %></td>
        <td>
            <form action="SupprimerServlet" method="post"
                  onsubmit="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">
                <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                <input type="submit" value="Supprimer">
            </form>
        </td>
    </tr>
<%
        }

    } catch (Exception e) {
%>
    <tr>
        <td colspan="4" style="text-align:center; color:red;">
            Erreur lors de la récupération des données : <%= e.getMessage() %>
        </td>
    </tr>
<%
    } finally {
        if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
        if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
    }
%>

</table>

<br>
<div style="text-align: center;">
    <a href="index.jsp">Ajouter un autre utilisateur</a>
</div>

</body>
</html>
