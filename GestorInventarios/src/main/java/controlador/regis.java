
package controlador;

import jakarta.servlet.RequestDispatcher;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;

@WebServlet(name = "regis", urlPatterns = {"/regis"})
public class regis extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Crea variables para guardar los datos que ingresa el usuario
        String nombre = request.getParameter("nombre");
        String numIde = request.getParameter("identi");
        String correo = request.getParameter("Correo");
        String contrasena = request.getParameter("contrasena");
        String infor = "";

        Persona usuario = new Persona(nombre, numIde, correo, contrasena);

        Connection conn = null;
        PreparedStatement ps;
        //Inserta los datos del formulario en la base de datos
        try {
            conn = conexion.establecerConexion();
            
            String sql1 = "INSERT INTO persona(nombre, identificación, correo, contrasena) VALUES (?, ?, ?, ?)";
            
            ps = conn.prepareStatement(sql1);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getNumIde());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasena());

            ps.executeUpdate();
            
            infor = "Datos insertados";
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            infor = "error en la sybuda de datos" + e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(regis.class.getName()).log(Level.SEVERE, null, ex);
            infor = "error en la sub de datos";

        }

        request.getSession().setAttribute("informacion", infor);
        RequestDispatcher rd = request.getRequestDispatcher("logeo.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
