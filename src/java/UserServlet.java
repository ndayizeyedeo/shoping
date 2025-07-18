import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Active la servlet à l'URL /UserServlet
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int age = Integer.parseInt(request.getParameter("age"));

        try {
            Connection con = new DatabaseConfig().getConnection();

            String sql = "INSERT INTO User(nom, prenom, age) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setInt(3, age);

            stmt.executeUpdate();
            stmt.close();
            con.close();

            // Affichage de confirmation avec bouton de retour
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html><head><title>Ajout réussi</title></head><body>");
                out.println("<h2>Utilisateur ajouté avec succès !</h2>");
                out.println("<br>");
                out.println("<form action='ListeUtilisateursServlet' method='get'>");
                out.println("<input type='submit' value='Retour à la liste des utilisateurs'>");
                out.println("</form>");
                out.println("</body></html>");
            }

        } catch (SQLException ex) {
            throw new ServletException("Erreur SQL : " + ex.getMessage(), ex);
        }
    }

    // Pour test simple via GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>UserServlet</title></head><body>");
            out.println("<h1>Bienvenue dans le UserServlet</h1>");
            out.println("</body></html>");
        }
    }
}
