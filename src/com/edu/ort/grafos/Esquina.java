
package com.edu.ort.grafos;


public class Esquina extends Punto  {

    public Esquina(double coordX, double coordY) {
        super(coordX, coordY);
    }

    @Override
    public String QuienSoy() {
        return "ESQUINA";
    }
    
}
