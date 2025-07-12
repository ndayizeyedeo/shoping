import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class AfficherUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = new ArrayList<>();

        try {
            Connection con = new DatabaseConfig().getConnection();
            Statement stmt = con.createStatement(); // corriger createStatement
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int age = rs.getInt("age");

                User user = new User(nom, prenom, age);
                users.add(user);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            throw new ServletException("Erreur lors de la récupération des utilisateurs : " + ex.getMessage(), ex);
        }

        // Affichage dans le navigateur
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Liste des utilisateurs</title></head><body>");
            out.println("<h1>Liste des utilisateurs</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Nom</th><th>Prénom</th><th>Âge</th></tr>");

            for (User user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getNom() + "</td>");
                out.println("<td>" + user.getPrenom() + "</td>");
                out.println("<td>" + user.getAge() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
        }
    }

    // Optionnel pour les tests GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // ou appel de processRequest(request, response);
    }
}
