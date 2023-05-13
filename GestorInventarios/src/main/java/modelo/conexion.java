package modelo;
import java.sql.*;


public class conexion {
    
        static Connection conn = null;
    public static Connection establecerConexion() throws ClassNotFoundException {
        
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3308/inventario";
                String username = "root";
                String password = "";
                conn = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return conn;
    }

}
