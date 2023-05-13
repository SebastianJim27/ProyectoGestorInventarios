
package modelo;

public class Sumas extends Totales {
        int totalAlimen=0;
        int totalEnsere=0;
        int totalMedica=0;
    public Sumas() {
    }
    public Sumas(String tipo, int cantidad){
        super(tipo, cantidad);
    }
    
 
    @Override
    public String calcularTotal(String tipo, int cantidad){
        if (tipo.equals("Medicamento")){
            totalMedica+=cantidad;
        }else if(tipo.equals("Alimento")){
            totalAlimen+=cantidad;
        }else if(tipo.equals("Enseres")){
            totalEnsere+=cantidad;
        }
        return "<tr><td>" + totalEnsere + "</td>" +
                "<td>" + totalAlimen + "</td>" +
                "<td>" + totalMedica + "</td></tr>";
    }  
  
}

