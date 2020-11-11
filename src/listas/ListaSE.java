package listas;

import java.util.Iterator;

public class ListaSE<T> implements ILista<T> {

	protected NodoLista<T> inicio;
	protected NodoLista<T> fin;
	protected int largo;
	protected int tope;

	public ListaSE() {
//		this.inicio = null;
//		this.fin = null;
//		this.largo = 0;
		this.tope = -1;
	}

	public ListaSE(int tope) {
//		this.inicio = null;
//		this.fin = null;
//		this.largo = 0;
		this.tope = tope;
	}

	@Override
	public T obtenerPpio() {
		return inicio.getDato();
	}

	@Override
	public T obtenerFin() {
		return fin.getDato();
	}

	@Override
	public void borrarInicio() {
		inicio = inicio.getSig();
		if (inicio == null)
			fin = null;
		largo--;
	}

	@Override
	public void borrarFin() {
		if (inicio.getSig() == null) {
			inicio = fin = null;
		} else {
			NodoLista<T> aux = inicio;
			while (aux.getSig().getSig() != null)
				aux = aux.getSig();
			aux.setSig(null);
			fin = aux;
		}
		largo--;
	}

	@Override
	public void insertarPpio(T dato) {
		NodoLista<T> nuevo = new NodoLista<T>(dato, inicio);
		inicio = nuevo;
		if (fin == null)
			fin = inicio;
		largo++;
	}

	@Override
	public void insertarFin(T dato) {
		if(inicio == null) {
			inicio = fin = new NodoLista<T>(dato);
		} else {
			NodoLista<T> aux = inicio;
			while(aux.getSig()!= null)
				aux = aux.getSig();
			NodoLista<T> nuevo = new NodoLista<T>(dato);
			aux.setSig(nuevo);
			fin = nuevo;
		}
		largo++;
	}

	@Override
	public void insertarOrd(T dato) {
		throw new UnsupportedOperationException("No se puede insertar ordenado en una ListaSE.");
	}

	@Override
	public T recuperar(T dato) {
		NodoLista<T> aux = inicio;
		while(aux != null) {
			if(aux.getDato().equals(dato)) {
				return aux.getDato();
			} else {
				aux = aux.getSig();
			}
		}
		return null;
	}

	@Override
	public void borrar(T dato) {
		if(inicio.getDato().equals(dato)) {
			inicio = inicio.getSig();
			if(inicio == null)
				fin = null;
		} else {
			NodoLista<T> aux = inicio;
			while(!aux.getSig().getDato().equals(dato)) {
				aux = aux.getSig();
			}
			aux.setSig(aux.getSig().getSig());
			if(aux.getSig() == null)
				fin = aux;
		}
		largo--;
	}

	@Override
	public void borrarTodos(T dato) {
		// No es el mejor orden, pero sirve! Deberes: Mejorarla :-)
		while(existe(dato))
			borrar(dato);
	}

	@Override
	public int obtenerPos(T dato) {
		NodoLista<T> aux = inicio;
		int pos = 0;
		while(aux != null) {
			if(aux.getDato().equals(dato)) {
				return pos;
			} else {
				pos++;
				aux = aux.getSig();
			}
		}
		return -1;
	}

	@Override
	public boolean existe(T dato) {
		return obtenerPos(dato)!=-1;
	}

	@Override
	public boolean esVacia() {
		return largo == 0;
	}

	@Override
	public boolean esLlena() {
		return largo == tope;
	}

	@Override
	public int largo() {
		return largo;
	}

	@Override
	public void listar() {
		System.out.print("[");
		if (inicio != null) {
			System.out.print(inicio.getDato());
			if (inicio.getSig() != null) {
				NodoLista<T> aux = inicio;
				while (aux.getSig() != null) {
					System.out.print(", " + aux.getSig().getDato());
					aux = aux.getSig();
				}
			}
		}
		System.out.println("]");
	}

	@Override
	public void vaciar() {
		while (!esVacia())
			borrarInicio();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private NodoLista<T> aux = inicio;
			
			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public T next() {
				T ret = aux.getDato();
				aux = aux.getSig();
				return ret;
			}
		};
	}

}
