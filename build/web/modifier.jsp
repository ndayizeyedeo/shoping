<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String idParam = request.getParameter("id");
    int id = 0;
    String nom = "", prenom = "";
    int age = 0;

    if (idParam != null && !idParam.isEmpty()) {
        try {
            id = Integer.parseInt(idParam);

            String url = "jdbc:mysql://localhost:3306/ma_base";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
                nom = rs.getString("nom");
                prenom = rs.getString("prenom");
                age = rs.getInt("age");
            } else {
                out.println("<p style='color:red'>Aucun utilisateur trouvé avec l'ID : " + id + "</p>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (NumberFormatException e) {
            out.println("<p style='color:red'>ID invalide (non numérique).</p>");
        } catch (Exception e) {
            out.println("<p style='color:red'>Erreur : " + e.getMessage() + "</p>");
        }
    } else {
        out.println("<p style='color:red'>ID manquant dans l'URL.</p>");
    }
%>

<% if (id != 0) { %>
    <h2>Modifier l'utilisateur</h2>
    <form action="ModifierUtilisateurServlet" method="post">
        <input type="hidden" name="id" value="<%= id %>">
        Nom : <input type="text" name="nom" value="<%= nom %>" required><br>
        Prénom : <input type="text" name="prenom" value="<%= prenom %>" required><br>
        Âge : <input type="number" name="age" value="<%= age %>" required><br>
        <input type="submit" value="Modifier">
    </form>
<% } %>
