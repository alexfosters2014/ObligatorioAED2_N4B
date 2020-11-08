
package com.edu.ort.grafos;


public class Esquina extends Punto  {

    public Esquina(double coordX, double coordY) {
        super(coordX, coordY);
    }

    @Override
    public String getColor() {
   if (estaOcupado())
            return "red";
       return "green";
    }

    @Override
    public String getTipo() {
   return "E";
    }

    
}
