package com.edu.ort.grafos;

public class Movil extends Punto {

    public Movil(double coordX, double coordY) {
        super(coordX, coordY);
    }

    @Override
    public String QuienSoy() {
            return "MOVIL";
    }

}
