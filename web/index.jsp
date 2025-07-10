<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
    </head>
    <body>
        <h1>Ajouter un utilisateur</h1>
        <form action="UserServlet" method="Post">
            <label for="name">Nom:</label>
            <input type="text" name="nom" required><br>
            <label for="name">Prenom:</label>
            <input type="text"name="prenom" required><br>
            <label for="name">Age:</label>
            <input type="number"name="age" required><br>
            <input type="submit"value="Ajouter"><br>
            
        </form>
            
    </body>
</html>
