<%@page import="models.User"%>
<%@ page import="java.util.List" %>
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
    List<User> Liste = (List<User>) session.getAttribute("ListeUtilisateurs");
    if (Liste != null) {
        for (int i = 0; i < Liste.size(); i++) {
            User u = Liste.get(i);
%>
    <tr>
        <td><%= u.getNom() %></td>
        <td><%= u.getPrenom() %></td>
        <td><%= u.getAge() %></td>
        <td>
            <form action="SupprimerServlet" method="post"
                  onsubmit="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">
                <input type="hidden" name="index" value="<%= i %>">
                <input type="submit" value="Supprimer">
            </form>
        </td>
    </tr>
<%
        }
    } else {
%>
    <tr>
        <td colspan="4" style="text-align:center;">Aucun utilisateur enregistré.</td>
    </tr>
<%
    }
%>

</table>

<br>
<div style="text-align: center;">
    <a href="index.jsp">Ajouter un autre utilisateur</a>
</div>

</body>
</html>
