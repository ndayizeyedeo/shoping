<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
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
            text-align: center;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Liste des utilisateurs</h2>

<%
    List<User> utilisateurs = (List<User>) request.getAttribute("utilisateurs");
    String erreur = (String) request.getAttribute("erreur");

    if (erreur != null) {
%>
    <p style="color:red; text-align:center;"><%= erreur %></p>
<%
    }
%>

<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Âge</th>
        <th>Action</th>
    </tr>

<% if (utilisateurs != null) {
       for (User u : utilisateurs) {
%>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getNom() %></td>
        <td><%= u.getPrenom() %></td>
        <td><%= u.getAge() %></td>
        <td>
            <!-- Formulaire Supprimer -->
            <form action="SupprimerServlet" method="post" style="display:inline;"
                  onsubmit="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">
                <input type="hidden" name="id" value="<%= u.getId() %>">
                <input type="submit" value="Supprimer">
            </form>

            <!-- Formulaire Modifier -->
            <form action="modifier.jsp" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= u.getId() %>">
                <input type="submit" value="Modifier">
            </form>
        </td>
    </tr>
<%
        }
   }
%>
</table>

<br>
<div style="text-align: center;">
    <a href="index.jsp">Ajouter un autre utilisateur</a>
</div>

</body>
</html>
