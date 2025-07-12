import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private String url = "jdbc:mysql://localhost:3306/ma_base";
    private String user = "root";
    private String password = "";
    private Connection con;

    public DatabaseConfig() {
        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non trouvé : " + e.getMessage());
        } catch (SQLException ex) {
            System.err.println("Erreur de connexion : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }
}

