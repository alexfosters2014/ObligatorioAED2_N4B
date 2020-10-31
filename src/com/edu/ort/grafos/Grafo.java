package com.edu.ort.grafos;

import cola.ColaDinamica;
import cola.ICola;

public class Grafo {

    private int tope;
    private int cant;
    private boolean esDirigido;
    private Punto[] vertices;
    private Arista[][] matAdy;

    public Grafo(int tope, boolean esDirigido) {
        this.tope = tope;
        //this.cant = 0;
        this.vertices = new Punto[tope];
        this.matAdy = new Arista[tope][tope];

        int largo = matAdy.length;

        if (esDirigido) {
            for (int i = 0; i < largo; i++) {
                for (int j = 0; j < largo; j++) {
                    matAdy[i][j] = new Arista();
                }
            }
        } else {
            for (int i = 0; i < largo; i++) {
                for (int j = 1; j < largo; j++) {
                    matAdy[i][j] = matAdy[j][i] = new Arista();
                }
            }
        }

    }

    public boolean esLleno() {
        return cant == tope;
    }

    public boolean esVacio() {
        return cant == 0;
    }

    // Pre: !esLleno()
    public void agregarVertice(Punto dato) {
        int pos = posHueco();
        vertices[pos] = dato;
        cant++;
    }

    private int posHueco() {
        int pos = 0;
        while (vertices[pos] != null) {
            pos++;
        }
        return pos;
    }
    
    private int posConVertice() {
        int pos = 0;
        while (vertices[pos] == null) {
            pos++;
        }
        return pos;
    }
    // Pre: existeVertice(origen) && existeVertice(destino) && !existeArista(origen, destino)

    public void agregarArista(Punto origen, Punto destino, int metros,int minutos) {
        int posOrigen = buscarPos(origen);
        int posDestino = buscarPos(destino);

        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setMetros(metros);
        matAdy[posOrigen][posDestino].setMinutos(minutos);
    }

    private int buscarPos(Punto destino) {
        for (int i = 0; i < tope; i++) {
            if (destino.equals(vertices[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean existeArista(Punto origen, Punto destino) {
        int posOrigen = buscarPos(origen);
        int posDestino = buscarPos(destino);
        return matAdy[posOrigen][posDestino].isExiste();
    }

    public boolean existeVertice(Punto origen) {
        return buscarPos(origen) != -1;
    }

    public void borrarArista(Punto origen, Punto destino) {
        int posOrigen = buscarPos(origen);
        int porDestino = buscarPos(destino);
        matAdy[posOrigen][porDestino].setExiste(false);
    }

    public void borrarVertice(Punto origen) {
        int posOrigen = buscarPos(origen);
        cant--;
        vertices[posOrigen] = null;
        for (int i = 0; i < tope; i++) {
            matAdy[i][posOrigen].setExiste(false);
            matAdy[posOrigen][i].setExiste(false);
        }

    }
    //busqueda por prfundidad

    public void dfs(Punto origen) {
        int posOrigen = buscarPos(origen);
        boolean[] visitados = new boolean[tope];
        dfsRec(posOrigen, visitados);

    }

    private void dfsRec(int pos, boolean[] visitados) {
        System.out.println(vertices[pos]);
        visitados[pos] = true;
        for (int i = 0; i < tope; i++) {
            if (!visitados[i] && matAdy[pos][i].isExiste()) {
                dfsRec(i, visitados);
            }
        }
    }  

    //busqueda por amplitud
    public void bfs(Punto punto) {
        int posOrigen = buscarPos(punto);
        boolean[] visitados = new boolean[tope];
        bfsInterno(posOrigen,visitados);
    }

    private void bfsInterno(int posOrigen,boolean[] visitados) {
        ICola<Integer> cola= new ColaDinamica<Integer>();
        cola.enqueue(posOrigen);
        visitados[posOrigen]=true;
         while(!cola.esVacia()){
            int pos=cola.dequeueAndFront();
             System.out.println(vertices[pos]);
             for (int i = 0; i < tope; i++) {
                 if (!visitados[i] && matAdy[pos][i].isExiste()){
                     cola.enqueue(i);
                     visitados[i]=true;
                 }
             }
         }
          
    }
    //algoritmo para encontrar arbol de recubrimiento minimo
    public void prim(boolean metrosTrue_DistanciaFalse){
        int metrosDistancia;
        int pos= posConVertice();
        boolean[] visitados = new boolean[tope];
        visitados[pos]=true;
        //Realizo el proceso reiterativo v-1 veces
        for (int k = 0; k < cant-1; k++) {
            int posO=-1, posD=-1, costo=Integer.MAX_VALUE;
            //busco los visitados
            for (int i = 0; i < tope; i++) {
                if (visitados[i]){
                    // busco los no visitados
                    for (int j = 0; j < tope; j++) {
                        metrosDistancia= metrosTrue_DistanciaFalse ? matAdy[i][j].getMetros() : matAdy[i][j].getMinutos();
                        if (!visitados[j] && matAdy[i][j].isExiste() && metrosDistancia<costo){
                        posO = i;
                        posD = j;
                        costo = metrosDistancia;
                        }
                    }
                }
            }
            visitados[posD]=true;
            System.out.println(String.format("%s -> %s (costo: %s", vertices[posO],vertices[posD], costo));
        }
    }

}
