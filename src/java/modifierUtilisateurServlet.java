import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/ModifierUtilisateurServlet")
public class modifierUtilisateurServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int age = Integer.parseInt(request.getParameter("age"));

        String url = "jdbc:mysql://localhost:3306/ma_base";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement stmt = conn.prepareStatement(
                         "UPDATE user SET nom = ?, prenom = ?, age = ? WHERE id = ?")) {

                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setInt(3, age);
                stmt.setInt(4, id);

                stmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Rediriger vers la liste après modification
        response.sendRedirect("ListeUtilisateursServlet");
    }
}
