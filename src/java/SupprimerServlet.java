import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/SupprimerServlet")
public class SupprimerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("ListeUtilisateursServlet");
            return;
        }

        int id = Integer.parseInt(idParam);

        String url = "jdbc:mysql://localhost:3306/ma_base";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE id = ?")) {

                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(); // Pour le débogage (optionnel : afficher un message d'erreur dans une JSP)
        }

        // Redirection vers la liste mise à jour
        response.sendRedirect("ListeUtilisateursServlet");
    }
}
