package com.edu.ort.grafos;

import cola.ColaDinamica;
import cola.ICola;
import uy.edu.ort.obli.Retorno;

public class Grafo {

    public enum tipoControl {
        DELIVERY,
        MOVIL
    }

    public enum tipoDijkstra {
        MOVIL_MINIMO,
        MOVIL_CERCANO,
        DELIVERY_MINIMO,
        DELIVERY_CERCANO

    }
    private int tope;
    private int cant;
    private Punto[] vertices;
    private Arista[][] matAdyDir;
    private Arista[][] matAdyNODir;

    public Grafo(int tope) {
        this.tope = tope;
        //this.cant = 0;
        this.vertices = new Punto[tope];
        this.matAdyDir = new Arista[tope][tope];
        this.matAdyNODir = new Arista[tope][tope];
        //int largo = matAdyDir.length;

        for (int i = 0; i < tope; i++) {
            for (int j = 0; j < tope; j++) {
                matAdyDir[i][j] = new Arista();
            }
        }

        for (int i = 0; i < tope; i++) {
            for (int j = 0; j < tope; j++) {
                matAdyNODir[i][j] = matAdyNODir[j][i] = new Arista();
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

    public void agregarArista(Punto origen, Punto destino, int metros, int minutos) {
        int posOrigen = buscarPos(origen);
        int posDestino = buscarPos(destino);

        matAdyDir[posOrigen][posDestino].setExiste(true);
        matAdyDir[posOrigen][posDestino].setMetros(metros);
        matAdyDir[posOrigen][posDestino].setMinutos(minutos);

        matAdyNODir[posOrigen][posDestino].setExiste(true);
        matAdyNODir[posOrigen][posDestino].setMetros(metros);
        matAdyNODir[posOrigen][posDestino].setMinutos(minutos);
    }

    private int buscarPos(Punto destino) {///////////
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
        return matAdyDir[posOrigen][posDestino].isExiste();

    }

    public boolean existeVertice(Punto origen) {
        return buscarPos(origen) != -1;
    }

    public Punto buscarVertice(Punto origen) {
        int pos = buscarPos(origen);
        if (pos == -1) {
            return null;
        }
        return vertices[pos];
    }

    public void borrarArista(Punto origen, Punto destino) {
        int posOrigen = buscarPos(origen);
        int porDestino = buscarPos(destino);

        matAdyDir[posOrigen][porDestino].setExiste(false);
        matAdyNODir[posOrigen][porDestino].setExiste(false);
    }

    public void borrarVertice(Punto origen) {
        int posOrigen = buscarPos(origen);
        cant--;
        vertices[posOrigen] = null;
        for (int i = 0; i < tope; i++) {
            matAdyDir[i][posOrigen].setExiste(false);
            matAdyDir[posOrigen][i].setExiste(false);

            matAdyNODir[i][posOrigen].setExiste(false);
            matAdyNODir[posOrigen][i].setExiste(false);
        }

    }
    //mostrar busqueda por prfundidad

    public void dfs(Punto origen) {
        int posOrigen = buscarPos(origen);
        boolean[] visitados = new boolean[tope];
        dfsRec(posOrigen, visitados);

    }

    private void dfsRec(int pos, boolean[] visitados) {
        System.out.println(vertices[pos]);
        visitados[pos] = true;
        for (int i = 0; i < tope; i++) {
            if (!visitados[i] && matAdyNODir[pos][i].isExiste()) {
                dfsRec(i, visitados);
            }
        }
    }

    //busqueda por amplitud
    public void bfs(Punto punto) {
        int posOrigen = buscarPos(punto);
        boolean[] visitados = new boolean[tope];
        bfsInterno(posOrigen, visitados);
    }

    private void bfsInterno(int posOrigen, boolean[] visitados) {
        ICola<Integer> cola = new ColaDinamica<Integer>();
        cola.enqueue(posOrigen);
        visitados[posOrigen] = true;
        while (!cola.esVacia()) {
            int pos = cola.dequeueAndFront();
            System.out.println(vertices[pos]);
            for (int i = 0; i < tope; i++) {
                if (!visitados[i] && matAdyNODir[pos][i].isExiste()) {
                    cola.enqueue(i);
                    visitados[i] = true;
                }
            }
        }

    }

    //algoritmo para encontrar arbol de recubrimiento minimo ,tiene que ser no dirigido
    public void prim(boolean metrosTrue_DistanciaFalse) {
        int metrosDistancia;
        int pos = posConVertice();
        boolean[] visitados = new boolean[tope];
        visitados[pos] = true;
        //Realizo el proceso reiterativo v-1 veces
        for (int k = 0; k < cant - 1; k++) {
            int posO = -1, posD = -1, costo = Integer.MAX_VALUE;
            //busco los visitados
            for (int i = 0; i < tope; i++) {
                if (visitados[i]) {
                    // busco los no visitados
                    for (int j = 0; j < tope; j++) {
                        metrosDistancia = metrosTrue_DistanciaFalse ? matAdyNODir[i][j].getMetros() : matAdyNODir[i][j].getMinutos();
                        if (!visitados[j] && matAdyNODir[i][j].isExiste() && metrosDistancia < costo) {
                            posO = i;
                            posD = j;
                            costo = metrosDistancia;
                        }
                    }
                }
            }
            visitados[posD] = true;
            System.out.println(String.format("%s -> %s (costo: %s", vertices[posO], vertices[posD], costo));
        }
    }

    public ValorDijkstra dijkstra(Punto origen, tipoControl tControl, tipoDijkstra tDijkstra) {
        ValorDijkstra vDijkstra;
        int posO = buscarPos(origen);
        // Armo los tres arreglos necesarios para realizar el algoritmo
        Arista[][] matAdySelector;
        if (tControl.equals(tipoControl.DELIVERY)) {
            matAdySelector = matAdyNODir;
        } else {
            matAdySelector = matAdyDir;
        }
        int[] dist = new int[tope];
        int[] ant = new int[tope];
        boolean[] vis = new boolean[tope];
        // inicializo los vectores
        for (int i = 0; i < tope; dist[i] = Integer.MAX_VALUE, ant[i] = -1, i++)
			;
        // asigno al destino como el primer nodo a ser recorrido
        dist[posO] = 0;
        // comienzo proceso reiterativo (V veces) para ir procesando a los vértices de a
        // uno
        for (int k = 0; k < cant; k++) {
            int posMin = -1, min = Integer.MAX_VALUE;
            // hallo al vértice no visitado de menor distancia al origen
            for (int i = 0; i < tope; i++) {
                if (!vis[i] && dist[i] < min) {
                    posMin = i;
                    min = dist[i];
                }
            }
            // visito al elemento a ser procesado
            vis[posMin] = true;
            // analizo a los adyacentes, actualizando su distancia en caso de ser menor a la
            // hasta ahora descubierta
            for (int j = 0; j < tope; j++) {
                if (!vis[j] && matAdySelector[posMin][j].isExiste()) {
                      int sumaAcumulada=0;
                    switch (tDijkstra) {
                        case DELIVERY_MINIMO:
                            sumaAcumulada = dist[posMin] + matAdySelector[posMin][j].getMinutos();
                            break;
                        case DELIVERY_CERCANO:
                            sumaAcumulada = dist[posMin] + 1;
                            break;
                        case MOVIL_MINIMO: case MOVIL_CERCANO:
                            sumaAcumulada = dist[posMin] + matAdySelector[posMin][j].getMetros();
                            break;
                    }
                    if (sumaAcumulada < dist[j]) {
                        dist[j] = sumaAcumulada;
                        ant[j] = posMin;
                    }
                }
            }
        }
        vDijkstra = new ValorDijkstra(dist, ant);
        return vDijkstra;
    }

    public Retorno dijkstra_Delivery_ND(Punto origen, Punto destino) {
        ValorDijkstra vDijkstra;
        Retorno retorno = new Retorno(Retorno.Resultado.OK);//nuevo comentario
        int posO = buscarPos(origen);
        int posD = buscarPos(destino);
        if (posO == -1 || posD == -1) {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        }
        // Armo los tres arreglos necesarios para realizar el algoritmo
        vDijkstra= dijkstra(origen, tipoControl.DELIVERY, tipoDijkstra.DELIVERY_MINIMO);
        int[] dist = vDijkstra.dist;
        int[] ant = vDijkstra.ant;
       
        int e = 0;
        boolean deliveryDisponible = false;
        while (e < tope && !deliveryDisponible) {
            if (vertices[e] instanceof Delivery && vertices[e].estaLibre()) {
                deliveryDisponible = true;
            }
            e++;
        }

        if (!deliveryDisponible) {
            retorno.resultado = Retorno.Resultado.ERROR_2;
            return retorno;
        }

        // evaluar si el camino fue encontrado=
        retorno.valorEntero = dist[posD];
        retorno.valorString = vertices[posD].getCoordX() + ";" + vertices[posD].getCoordY();
        int a = posD;
        //iterno para guardar las coordenadas de cada punto del camino
        while (ant[a] > -1) {
            retorno.valorString = vertices[ant[a]].getCoordX() + ";" + vertices[ant[a]].getCoordY() + "|" + retorno.valorString;
            a = ant[a];
        }
        return retorno;
    }

    public Retorno dijkstra_Movil_D(Punto origen, Punto destino) {
          ValorDijkstra vDijkstra;
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        int posO = buscarPos(origen);
        int posD = buscarPos(destino);

        if (posO == -1 || posD == -1) {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        }
          vDijkstra = dijkstra(origen, tipoControl.MOVIL, tipoDijkstra.MOVIL_MINIMO);
        // Armo los tres arreglos necesarios para realizar el algoritmo
        int[] dist = vDijkstra.dist;
        int[] ant = vDijkstra.ant;
        
        int e = 0;
        boolean movilDisponible = false;
        while (e < tope && !movilDisponible) {
            if (vertices[e] instanceof Movil && vertices[e].estaLibre()) {
                movilDisponible = true;
            }
            e++;
        }

        if (!movilDisponible) {
            retorno.resultado = Retorno.Resultado.ERROR_2;
            return retorno;
        }

        // evaluar si el camino fue encontrado=
        retorno.valorEntero = dist[posD];
        retorno.valorString = vertices[posD].getCoordX() + ";" + vertices[posD].getCoordY();
        retorno.valorString = vertices[posD].getCoordX() + ";" + vertices[posD].getCoordY();
        int a = posD;
        //iterno para guardar las coordenadas de cada punto del camino
        while (ant[a] > -1) {
            retorno.valorString = vertices[ant[a]].getCoordX() + ";" + vertices[ant[a]].getCoordY() + "|" + retorno.valorString;
            a = ant[a];
        }
        return retorno;
    }

    public Retorno dijkstra_DeliveryMasCercano(Punto origen) {
        ValorDijkstra vDijkstra;
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        int posO = buscarPos(origen);
        if (posO == -1) {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        }
        vDijkstra = dijkstra(origen, tipoControl.DELIVERY, tipoDijkstra.DELIVERY_CERCANO);
        // Armo los tres arreglos necesarios para realizar el algoritmo
        int[] dist = vDijkstra.dist;
        int[] ant = vDijkstra.ant;
        
        int posMinDel = -1, valorMinDel = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (vertices[i] instanceof Delivery) {
                Delivery delivery = (Delivery) vertices[i];
                if (delivery.estaLibre() && dist[i] < valorMinDel) {
                    posMinDel = i;
                    valorMinDel = dist[i];
                }
            }
        }

        if (posMinDel == -1) {
            retorno.resultado = Retorno.Resultado.ERROR_2;
            return retorno;
        }
        //seteo a ocupado el deliver
        Delivery delivery = (Delivery) vertices[posMinDel];
        delivery.setOcupado(true);
        retorno.valorEntero = valorMinDel;
        retorno.valorString = delivery.getCoordX() + ";" + delivery.getCoordY();
        return retorno;

    }

    public Retorno dijkstra_MovilMasCercano(Punto origen) {
        ValorDijkstra vDijkstra;
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        int posO = buscarPos(origen);
        if (posO == -1) {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        }
        vDijkstra = dijkstra(origen, tipoControl.MOVIL, tipoDijkstra.MOVIL_CERCANO);
        // Armo los tres arreglos necesarios para realizar el algoritmo
        int[] dist = vDijkstra.dist;
        int[] ant = vDijkstra.ant;
        
        // ************** operaciones con MOVIL ********************
        int posMinMovil = -1, valorMinMovil = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (vertices[i] instanceof Movil) {
                Movil movil = (Movil) vertices[i];
                if (movil.estaLibre() && dist[i] < valorMinMovil) {
                    posMinMovil = i;
                    valorMinMovil = dist[i];
                }
            }
        }

        if (posMinMovil == -1) {
            retorno.resultado = Retorno.Resultado.ERROR_2;
            return retorno;
        }
        //seteo a ocupado el deliver
        Movil movil = (Movil) vertices[posMinMovil];
        movil.setOcupado(true);
        retorno.valorEntero = valorMinMovil;
        return retorno;
    }

    public String urlPuntos() {
        String url = "";

        for (int i = 0; i < tope; i++) {
            if (vertices[i] instanceof Delivery || vertices[i] instanceof Movil) {
                Punto punto = vertices[i];
                url += "&markers=color:" + punto.getColor() + "%7Clabel:" + punto.getTipo() + "%7C" + punto.getCoordX() + "," + punto.getCoordY();
            }
        }
        return url;
    }

}
