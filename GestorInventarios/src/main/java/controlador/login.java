package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtiene los parámetros de inicio de sesión del formulario de inicio de sesión
        String numIde = request.getParameter("identi");
        String contrasena = request.getParameter("contrasena");
        
        // Obtiene la sesión actual o crea una nueva si no existe
        HttpSession session = request.getSession(true);
        
        // Intenta autenticar al usuario en la base de datos
        boolean authenticated = usuarioAutentico(numIde, contrasena);
        
        // Si el usuario ha sido autenticado con éxito, establece la sesión como autenticada y redirige a la página de inicio
        if(authenticated){
            request.getSession().setAttribute("authenticated", true);
            response.sendRedirect(request.getContextPath() + "/regisInventario.jsp");
        }
        // Si el usuario no ha sido autenticado, muestra un mensaje de error y redirige a la página de inicio de sesión
        else {
            session.setAttribute("authenticated", false);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('El nombre de usuario o la contraseña son incorrectos.');");
            out.println("location='logeo.jsp';");
            out.println("</script>");
        }
    }
    
    // Método privado para autenticar al usuario en la base de datos
    private boolean usuarioAutentico(String numIde, String contrasena) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = conexion.establecerConexion();
            
            String query = "SELECT * FROM persona WHERE identificación=? AND contrasena=?";
            ps = con.prepareStatement(query);
            ps.setString(1, numIde);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            
            return rs.next();
            
        } catch (SQLException e) {
            
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }


   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
