
package com.edu.ort.grafos;

public class Delivery extends Punto{

    public Delivery(double coordX, double coordY) {
        super(coordX, coordY);
    }

    @Override
    public String QuienSoy() {
        return "DELIVERY";
    }
    
}
