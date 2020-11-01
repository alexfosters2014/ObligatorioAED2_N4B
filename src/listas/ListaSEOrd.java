package listas;

public class ListaSEOrd<T extends Comparable<T>> extends ListaSE<T> {

	@Override
	public void insertarFin(T dato) {
		throw new UnsupportedOperationException("No se puede insertar al final de una ListaSEOrd.");
	}

	@Override
	public void insertarPpio(T dato) {
		throw new UnsupportedOperationException("No se puede insertar al principio de una ListaSEOrd.");
	}

	@Override
	public void insertarOrd(T dato) {
		if (inicio == null || dato.compareTo(inicio.getDato()) <= 0) {
			super.insertarPpio(dato);
		} else {
			NodoLista<T> aux = inicio;
			while (aux.getSig() != null && dato.compareTo(aux.getSig().getDato()) > 0)
				aux = aux.getSig();
			NodoLista<T> nuevo = new NodoLista<T>(dato, aux.getSig());
			aux.setSig(nuevo);
			if(nuevo.getSig()==null)
				fin = nuevo;
			largo++;
		}
	}
}
