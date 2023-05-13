package modelo;

public class Persona {
    
    private String nombre;
    private String numIde;

   
    private String correo;
    private String contrasena;
    
    public Persona(){
        
    }

    public Persona(String nombre, String numIde, String correo, String contrasena) {
        this.nombre = nombre;
        this.numIde = numIde;
        this.correo = correo;
        this.contrasena = contrasena;
    }
  
    
    public String getNombre() {
        return nombre;
    }
    
    public String getNumIde() {
        return numIde;
    }
    
    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
