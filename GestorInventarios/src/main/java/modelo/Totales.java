
package modelo;


public abstract class Totales {
    String tipo;
    int cantidad;

    public Totales() {
    }

    public Totales(String tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String  calcularTotal(String tipo, int cantidad){
        return tipo;
                
    }
    
}
