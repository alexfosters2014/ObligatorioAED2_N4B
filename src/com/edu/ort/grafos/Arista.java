
package com.edu.ort.grafos;

public class Arista {
    private boolean existe;
    private int metros;
    private int minutos;

    public Arista(int metros, int minutos) {
        this.existe = true;
        this.metros = metros;
        this.minutos = minutos;
    }
    
     public Arista(){
//          this.existe = false;
//        this.costo = 0;
     }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }


    @Override
    public String toString() {
        return "";
    }
    
}
