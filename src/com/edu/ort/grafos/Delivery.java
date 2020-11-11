
package com.edu.ort.grafos;

public class Delivery extends Punto{
        private String cedula;

    public Delivery(double coordX, double coordY,String cedula) {
        super(coordX, coordY);
        this.cedula=cedula;
    }
     public Delivery(double coordX, double coordY) {
        super(coordX, coordY);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String getColor() {
     if (isOcupado()){
            return "red";
        }else{
       return "green";
        }
    }

    @Override
    public String getTipo() {
        return "D";
    }

   
    
}
