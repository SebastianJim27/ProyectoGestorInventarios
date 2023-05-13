
package controlador;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;


@WebServlet(name = "busca", urlPatterns = {"/busca"})
public class busca extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Crea variables para elegir el tipo de busqueda 
        String busqueda = request.getParameter("buscar");
        String tipoBuscar = request.getParameter("buscarTipo");
        String txtBuscar = request.getParameter("buscarTxt");

        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs = null;
        String infor = "";
        String dato = "";
        String todDatos = "";
        
        Articulos ar = new Articulos();
           //Busca los productos según su tipo
            if(busqueda.equals("tipo_producto")){
                try {

                    String query = "SELECT * FROM articulos WHERE tipo_producto=?";
                    
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(query);
                    ps.setString(1, tipoBuscar);
                    rs = ps.executeQuery();
                    
                    //muestra la base de datos
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                    }

                }catch (SQLException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            //Busca según su nombre
            }else if (busqueda.equals("nombre")){
                try {

                    String query = "SELECT * FROM articulos WHERE nombre=?";
                    
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(query);
                    ps.setString(1, txtBuscar);
                    rs = ps.executeQuery();
                    
                    //muestra la base de datos
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                    }

                }catch (SQLException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                }
            //Busca según su identificación    
            }else if (busqueda.equals("codigo")){
                
                try {

                    String query = "SELECT * FROM articulos WHERE codigo=?";
                    
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(query);
                    ps.setString(1, txtBuscar);
                    rs = ps.executeQuery();
                    
                    //muestra la base de datos
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                    }

                }catch (SQLException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //envia un String(todDatos) con la informacion de la base de datos
            request.getSession().setAttribute("datos", todDatos);      
        
            //---------------------------------------------------------------------------------
            //envia un String(infor) con la informacion de algun error y redirige
            request.getSession().setAttribute("infor", infor);
            RequestDispatcher rd = request.getRequestDispatcher("datos.jsp");
            rd.forward(request, response);
         
         
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
