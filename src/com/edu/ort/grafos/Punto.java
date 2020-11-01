
package com.edu.ort.grafos;

import java.util.Objects;

public abstract class Punto {
    private double coordX;
    private double coordY;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Punto other = (Punto) obj;
        if (Double.doubleToLongBits(this.coordX) != Double.doubleToLongBits(other.coordX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.coordY) != Double.doubleToLongBits(other.coordY)) {
            return false;
        }
        return true;
    }
    
    public abstract String QuienSoy();
    public boolean estoyEnCoordenadas(double MicoordX, double MicoordY){
        return (coordX == MicoordX && coordY == MicoordY);
    }
}
