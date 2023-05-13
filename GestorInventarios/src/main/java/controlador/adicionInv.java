package controlador;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;

@WebServlet(name = "adicionInv", urlPatterns = {"/adicionInv"})
public class adicionInv extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Llama a las variables del formulario
        String delet = request.getParameter("eliminar");
        String producto = request.getParameter("tInventario");
        String nombre = request.getParameter("pNombre");
        String cantidad = request.getParameter("cantidad");
        String vencimiento = request.getParameter("Vencimiento");
        String codigo = request.getParameter("cBarras");
        String delCantidad = request.getParameter("delCantidad");
        
        //Convierte los String en enteros 0 si son nulos
        int can = cantidad == null || cantidad.isEmpty() ? 0 : Integer.parseInt(cantidad);
        int nCantidad = delCantidad == null || delCantidad.isEmpty() ? 0 : Integer.parseInt(delCantidad);
        //Creación de los objetos
        Articulos ar = new Articulos(producto, nombre, can, vencimiento, codigo);
        Sumas sum=new Sumas();
        //Variables de la conexión y del envio al jsp
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs = null;
        String infor = "";
        String dato = "";
        String todDatos = "";
        String totInsu= "";
        
        //Define si los valores digitados estan vacios o son nulos
        if (producto != null && !producto.isEmpty() && nombre != null && !nombre.isEmpty() && cantidad != null && !cantidad.isEmpty() && vencimiento != null && !vencimiento.isEmpty() && codigo != null && !codigo.isEmpty()){
  
            //si el valor del delet(Variable identificación de la tabla) es nulo 
            if (delet == null){
                try {
                    //Inserta los datos del formulario en la base de datos                    
                    conn = conexion.establecerConexion();
                    
                    String sql1 = "INSERT INTO articulos(tipo_producto, nombre, cantidad, vencimiento, codigo) VALUES (?, ?, ?, ?, ?)";
                   
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, ar.getProducto());
                    ps.setString(2, ar.getNombreProducto());
                    ps.setInt(3, ar.getCantidad());
                    ps.setString(4, ar.getVencimiento());
                    ps.setString(5, ar.getCodigo());

                    ps.executeUpdate();
            
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    infor  = "Mensaje 1 " + e.getMessage();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(regis.class.getName()).log(Level.SEVERE, null, ex);
                    infor = "Mensaje 2 " + ex.getMessage();
                }
                //------------------------------------------------------------------------------
                
                
                
                try {
                    
                    String sql2 = "SELECT * FROM articulos";
                    
                    //muestra los datos de la base de datos
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(sql2);
                    rs = ps.executeQuery();
                    
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                        totInsu= sum.calcularTotal(ar.getProducto(), ar.getCantidad());
                    }
                    
                } catch (ClassNotFoundException e) {
                    infor = "mensaje 3 " + e.getMessage();
                } catch (SQLException ex) {
                    infor = "mensaje 4 " + ex.getMessage();
                }
            }    
        
            //envia un String(todDatos) con la informacion de la base de datos al jsp
            request.getSession().setAttribute("datos", todDatos);      
            request.getSession().setAttribute("totalIn", totInsu); 
            //---------------------------------------------------------------------------------
            //envia un String(infor) con la informacion de algun error y redirige
            request.getSession().setAttribute("infor", infor);
            RequestDispatcher rd = request.getRequestDispatcher("datos.jsp");
            rd.forward(request, response);
        
        //Si los valores ingresados son nulos
        }else{

            //Si es diferente de nulo o de vacio los valores , para eliminar la fila de la base de datos
            //!producto.isEmpty() significa que es diferente de vacio
            if (producto != null && !producto.isEmpty() && nCantidad != 0 && codigo != null && !codigo.isEmpty()){
                
                try {

                    String query = "UPDATE articulos SET cantidad = cantidad + ? WHERE tipo_producto=? AND codigo=?";
                    String sql3 = "SELECT * FROM articulos";
                    
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, nCantidad);
                    ps.setString(2, producto);
                    ps.setString(3, codigo);
                    //Ejecutar la actualización en la base de datos
                    ps.executeUpdate();
                    

                    //muestra la base de datos
                    ps = conn.prepareStatement(sql3);
                    rs = ps.executeQuery();
                    
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                        totInsu= sum.calcularTotal(ar.getProducto(), ar.getCantidad());
                    }
                    
                

                }catch (SQLException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(adicionInv.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //----------------------------------------------------------------------------------------------------------------
            
            //Si el valor de borrar que se guarda en el delet es direnre de nulo
            if (delet != null){
                try {
                    
                    String sql2 = "DELETE FROM articulos WHERE codigo = ?";
                    String sql3 = "SELECT * FROM articulos";
                    
                    //Elimina el valor de delet y actualiza la base de datos
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(sql2);
                    ps.setString(1, delet);
                    ps.executeUpdate();
                    
                    //muestra la base de datos
                    ps = conn.prepareStatement(sql3);
                    rs = ps.executeQuery();
                    
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                        totInsu= sum.calcularTotal(ar.getProducto(), ar.getCantidad());
                    }
                    
                } catch (ClassNotFoundException e) {
                    infor = "mensaje 5 " + e.getMessage();
                } catch (SQLException ex) {
                    infor = "mensaje 6 " + ex.getMessage();
                }
                
            //si el valor del delet es nulo 
            }else{
                
                try {
                    
                    String sql2 = "SELECT * FROM articulos";
                    
                    //muestra los datos de la base de datos
                    conn = conexion.establecerConexion();
                    ps = conn.prepareStatement(sql2);
                    rs = ps.executeQuery();
                    
                    while (rs.next()){
                        
                        ar.setProducto(rs.getString(1));
                        ar.setNombreProducto(rs.getString(2));
                        ar.setCantidad(rs.getInt(3));
                        ar.setVencimiento(rs.getString(4));
                        ar.setCodigo(rs.getString(5));
                        dato = ar.datos();
                        todDatos += dato;
                        totInsu= sum.calcularTotal(ar.getProducto(), ar.getCantidad());
                    }
                    
                } catch (ClassNotFoundException e) {
                    infor = "mensaje 3 " + e.getMessage();
                } catch (SQLException ex) {
                    infor = "mensaje 4 " + ex.getMessage();
                }
            }
            
            //envia un String(todDatos) con la informacion de la base de datos
            request.getSession().setAttribute("datos", todDatos);      
            request.getSession().setAttribute("totalIn", totInsu);  
            //---------------------------------------------------------------------------------
            //envia un String(infor) con la informacion de algun error y redirige
            request.getSession().setAttribute("infor", infor);
            RequestDispatcher rd = request.getRequestDispatcher("datos.jsp");
            rd.forward(request, response);
        
            
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
