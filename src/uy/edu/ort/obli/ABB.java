package uy.edu.ort.obli;

public class ABB<T extends Comparable<T>> {

    private NodoABB<T> raiz;
    private String listadoStr;

    public ABB(NodoABB<T> raiz) {
        this.raiz = raiz;
    }

    public ABB() {

    }
    public boolean esVacio(){
     if (raiz==null)
         return true;
     return false;
    }
    public boolean pertenece(T dato) {
        return perteneceRec(dato, raiz);
    }

    private boolean perteneceRec(T dato, NodoABB<T> nodo) {
        if (nodo == null) {
            return false;
        } else if (dato.equals(nodo.getDato())) {
            return true;
        } else if (dato.compareTo(nodo.getDato()) < 0) {
            return perteneceRec(dato, nodo.getIzq());
        } else {
            return perteneceRec(dato, nodo.getDer());
        }
    }

    public NodoABB<T> buscar(T dato) {
        return buscarRec(dato, raiz, 0);
    }

    private NodoABB<T> buscarRec(T dato, NodoABB<T> nodo, int conteo) {
        if (nodo == null) {
            return null;
        } else if (dato.equals(nodo.getDato())) {
            nodo.setcantidadRecorridas(++conteo);
            return nodo;
        } else if (dato.compareTo(nodo.getDato()) < 0) {
            conteo++;
            return buscarRec(dato, nodo.getIzq(), conteo);
        } else {
            conteo++;
            return buscarRec(dato, nodo.getDer(), conteo);
        }
    }
    public String listarAscStr(){
    listadoStr="";
    if (esVacio()){
     return listadoStr;   
    }
    listarAscStrRec(raiz);
    return listadoStr;
   }
   
   private void listarAscStrRec(NodoABB<T> nodo){
       //in-order
    if (nodo != null){
        listarAscStrRec(nodo.getIzq());
        listadoStr += nodo.getDato().toString()+"|";
        listarAscStrRec(nodo.getDer());
    }
   }
    public String listarAsc() {
       return listarAscRec(raiz,"");
    }

    private String listarAscRec(NodoABB<T> nodo,String StrLista) {
        //in-order
        if (nodo != null) {
            listarAscRec(nodo.getIzq(),StrLista);
            StrLista += nodo.getDato().toString()+"|";
            listarAscRec(nodo.getDer(),StrLista);
        }
        return StrLista;
    }

    public void listarDsc() {
        listarDscRec(raiz);
    }

    private void listarDscRec(NodoABB<T> nodo) {
        //in-order
        if (nodo != null) {
            listarDscRec(nodo.getDer());
            System.out.println(nodo.getDato());
            listarDscRec(nodo.getIzq());
        }
    }

    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoABB<T>(dato);
        } else {
            insertarRec(dato, raiz);
        }
    }

    private void insertarRec(T dato, NodoABB<T> nodo) {
        //chequeo si mi dato es menor al nodo
        if (dato.compareTo(nodo.getDato()) < 0) {
            //chequeo si tengo algo por la izq . Si esta vacio debo insertar
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB<T>(dato));
            } else //si tengo una izq llamo a la recursiva
            {
                insertarRec(dato, nodo.getIzq());
            }
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            //chequeo si tengo algo por la izq . Si esta vacio debo insertar
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB<T>(dato));
            } else //si tengo una izq llamo a la recursiva
            {
                insertarRec(dato, nodo.getDer());
            }
        }
    }

    public void insertarCorto(T dato) {
        raiz = insertarCortoRec(dato, raiz);
    }

    private NodoABB<T> insertarCortoRec(T dato, NodoABB<T> nodo) {
        if (nodo == null) {
            return new NodoABB<T>(dato);
        } else {
            if (dato.compareTo(nodo.getDato()) < 0) {
                nodo.setIzq(insertarCortoRec(dato, nodo.getIzq()));
            } else if (dato.compareTo(nodo.getDato()) > 0) {
                nodo.setDer(insertarCortoRec(dato, nodo.getDer()));
            }
            return nodo;
        }
    }

    public T borrarMinimo() {
        //evaluo si no tengo nada por izq, tomo el valor de la derecha ya que el minimo es la raiz (no importa
        //si apunta a null o tiene un nodo a la der
        if (raiz.getIzq() == null) {
            T ret = raiz.getDato();
            raiz = raiz.getDer();
            return ret;
        } else {
            return borrarMinimoRec(raiz);
        }
    }

    private T borrarMinimoRec(NodoABB<T> nodo) {
        if (nodo.getIzq().getIzq() == null) {
            T ret = nodo.getIzq().getDato();
            nodo.setIzq(nodo.getIzq().getDer());
            return ret;
        } else {
            return borrarMinimoRec(nodo.getIzq());
        }

    }

    public void borrar(T dato) {
        if (dato.equals(raiz.getDato())) {
            //caso 1: facil
            if (raiz.getIzq() == null && raiz.getDer() == null) {
                raiz = null;
            } //caso 2: mas o menos
            else if (raiz.getIzq() == null) {
                raiz = raiz.getDer();
            } //caso 2: mas o menos
            else if (raiz.getDer() == null) {
                raiz = raiz.getIzq();
            } else {
                //caso 3: dificil , cuando tiene dos nodos
                if (raiz.getIzq().getIzq() == null) {
                    raiz.setDer(raiz.getDer().getDer());
                } else {
                    T ret = borrarMinimoRec(raiz.getDer());
                    raiz.setDato(ret);
                }
            }
        } else {
            borrarRec(dato, raiz);
        }
    }

    private void borrarRec(T dato, NodoABB<T> nodo) {
        if (dato.compareTo(nodo.getDato()) < 0) {

            if (nodo.getIzq().getIzq() == null && nodo.getIzq().getDer() == null) {
                nodo.setIzq(null);
            } //caso 2: mas o menos
            else if (nodo.getIzq().getIzq() == null) {
                nodo.setIzq(nodo.getIzq().getDer());
            } //caso 2: mas o menos
            else if (nodo.getIzq().getDer() == null) {
                nodo.setIzq(nodo.getIzq().getIzq());
            } else {
                //caso 3: dificil , cuando tiene dos nodos
                if (nodo.getIzq().getIzq().getIzq() == null) {
                    nodo.getIzq().setDer(nodo.getIzq().getDer().getDer());
                } else {
                    T ret = borrarMinimoRec(nodo.getIzq().getDer());
                    nodo.getIzq().setDato(ret);
                }
            }

        } else if (dato.compareTo(nodo.getDato()) > 0) {
            if (nodo.getDer().getIzq() == null && nodo.getDer().getDer() == null) {
                nodo.setDer(null);
            } //caso 2: mas o menos
            else if (nodo.getDer().getIzq() == null) {
                nodo.setDer(nodo.getDer().getDer());
            } //caso 2: mas o menos
            else if (nodo.getDer().getDer() == null) {
                nodo.setDer(nodo.getDer().getIzq());
            } else {
                //caso 3: dificil , cuando tiene dos nodos
                if (nodo.getDer().getIzq().getIzq() == null) {
                    nodo.getDer().setDer(nodo.getDer().getDer().getDer());
                } else {
                    T ret = borrarMinimoRec(nodo.getDer().getDer());
                    nodo.getDer().setDato(ret);
                }
            }

        }
    }

}
