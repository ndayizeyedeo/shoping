import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.User;

// Modèle utilisateur
/*class User {
    private int id;
    private String nom;
    private String prenom;
    private int age;

    public User(int id, String nom, String prenom, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public int getAge() { return age; }
}*/

@WebServlet("/ListeUtilisateursServlet")
public class ListeUtilisateursServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "jdbc:mysql://localhost:3306/ma_base";
        String username = "root";
        String password = "";

        List<User> utilisateurs = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    User user = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("age")
                    );
                    utilisateurs.add(user);
                }

            }
        } catch (Exception e) {
            request.setAttribute("erreur", "Erreur : " + e.getMessage());
        }

        request.setAttribute("utilisateurs", utilisateurs);
        request.getRequestDispatcher("Liste.jsp").forward(request, response);
    }
}
