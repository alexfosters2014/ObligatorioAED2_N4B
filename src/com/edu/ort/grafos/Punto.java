
package com.edu.ort.grafos;

import java.util.Objects;

public abstract class Punto {
    private double coordX;
    private double coordY;
    private boolean ocupado;

    public Punto(double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public boolean isOcupado() {
        return ocupado;
    }
    public boolean estaOcupado() {
        return ocupado == true;
    }
    public boolean estaLibre() {
        return ocupado == false;
    }
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
        final Punto other = (Punto) obj;
        
        if (this.coordX == other.coordX && this.coordY == other.coordY) {
            return true;
        }
         }
        return false;
    }

   
    
   public abstract String getColor();
   public abstract String getTipo();
}
