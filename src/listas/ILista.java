package listas;

public interface ILista<T> extends Iterable<T> {

	/**
	 * Devuelve el primer elemento de la lista <br>
	 * Pre: !esVacia()
	 * @return T dato
	 */
	public T obtenerPpio();

	/**
	 * Devuelve el �ltimo elemento de la lista <br>
	 * Pre: !esVacia()
	 * @return T dato
	 */
	public T obtenerFin();
	
	/**
	 * Elimina al primer elemento de la lista <br>
	 * Pre: !esVacia()
	 */
	public void borrarInicio();

	/**
	 * Elimina al �ltimo elemento de la lista <br>
	 * Pre: !esVacia()
	 */
	public void borrarFin();

	/**
	 * Inserta a un elemento al principio de la lista <br>
	 * Pre: !esLlena()
	 */
	public void insertarPpio(T dato);

	/**
	 * Inserta a un elemento al final de la lista <br>
	 * Pre: !esLlena()
	 */
	public void insertarFin(T dato);


	/**
	 * Inserta a un elemento ordenado dentro de la lista <br>
	 * Pre: !esLlena()
	 */
	public void insertarOrd(T dato);
	
	/**
	 * Devuelve (en caso de existir) a un elemento igual al recibido por par�metro <br>
	 * Pre:
	 */
	public T recuperar(T dato);
	
	/**
	 * Elimina la primer ocurrencia del elemento recibido por par�metro<br>
	 * Pre: existe(dato)
	 */
	public void borrar(T dato);

	/**
	 * Elimina todas las ocurrencias del elemento recibido por par�metro<br>
	 * Pre:
	 */
	public void borrarTodos(T dato);

	/**
	 * Devuelve la posici�n del elemento recibido por par�metro, o -1 en caso de no existir.
	 * Pre:
	 */
	public int obtenerPos(T dato);

	/**
	 * Devuelve <code>true</code> sii el dato existe en la lista, o <code>false</code> en caso contrario.
	 * Pre:
	 */
	public boolean existe(T dato);

	/**
	 * Devuelve <code>true</code> sii la lista est� vac�a, o <code>false</code> en caso contrario.
	 * Pre:
	 */
	public boolean esVacia();

	/**
	 * Devuelve <code>true</code> sii la lista est� llena, o <code>false</code> en caso contrario.
	 * Pre:
	 */
	public boolean esLlena();

	/**
	 * Devuelve la cantidad de elementos de la lista.
	 * Pre:
	 */
	public int largo();
	
	/**
	 * Elimina a todos los elementos de la lista.
	 * Pre:
	 */
	public void vaciar();
        public void listar();
        
}
