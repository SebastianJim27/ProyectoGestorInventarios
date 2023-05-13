
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


@WebServlet(name = "elimina", urlPatterns = {"/elimina"})
public class elimina extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variables para añadir y eliminar cantidad
        String producto = request.getParameter("tInventario");
        String delCantidad = request.getParameter("delCantidad");
        String codigo = request.getParameter("cBarras");
        
        String restar=request.getParameter("rest");
        String sumar=request.getParameter("ana");
        
        int nCantidad = delCantidad == null || delCantidad.isEmpty() ? 0 : Integer.parseInt(delCantidad);
        
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs = null;
        String infor = "";
        String dato = "";
        String todDatos = "";
        
        Articulos ar = new Articulos();
        
         try {

                    String query = "UPDATE articulos SET cantidad = cantidad + ? WHERE tipo_producto=? AND codigo=?";
                    String sql3 = "SELECT * FROM articulos";
                    
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, nCantidad);
                    ps.setString(2, producto);
                    ps.setString(3, codigo);
                    ps.executeUpdate();

                    //muestra la base de datos
                    ps = conn.prepareStatement(sql3);
                    rs = ps.executeQuery();
                    
                    while (rs.next()){
                        if(restar!=null){
                            nCantidad=-1*nCantidad;
                        }else if(sumar!=null){
                            nCantidad=1*nCantidad;
                        }
                            
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
         
         //envia un String(todDatos) con la informacion de la base de datos
            request.getSession().setAttribute("datos", todDatos);      
        
            //---------------------------------------------------------------------------------
            //envia un String(infor) con la informacion de algun error y redirige
            request.getSession().setAttribute("infor", infor);
            RequestDispatcher rd = request.getRequestDispatcher("datos.jsp");
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
