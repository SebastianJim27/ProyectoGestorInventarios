package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Articulos {
    
    private String producto;
    private String nombreProducto;
    private int cantidad;
    private String vencimiento;
    private String codigo;

    public Articulos(String producto, String nombreProducto, int cantidad, String vencimiento, String codigo) {
        this.producto = producto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.vencimiento = vencimiento;
        this.codigo = codigo;
    }
    
    public Articulos(){
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public String datos(){
        
        return  "<tr><td>" + producto + "</td>" +
                "<td>" + nombreProducto + "</td>" +
                "<td>" + cantidad + "</td>" +
                "<td>" + vencimiento +  "</td>" +
                "<td>" + codigo + "</td></tr>";
    }
    
    
}
