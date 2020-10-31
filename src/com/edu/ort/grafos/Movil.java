package com.edu.ort.grafos;

public class Movil extends Punto {
    private String matricula;
            
    public Movil(double coordX, double coordY, String matricula) {
        super(coordX, coordY);
        this.matricula=matricula;
    }

    @Override
    public String QuienSoy() {
            return "MOVIL";
    }

}
