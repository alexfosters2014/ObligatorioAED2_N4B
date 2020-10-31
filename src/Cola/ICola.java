package cola;

public interface ICola<T> {
	
	/**
	 * Pre: !esLlena()
	 * @param dato
	 */
	public void enqueue(T dato);

	/**
	 * Pre: !esVacia()
	 * @param dato
	 */
	public void dequeue();
	
	/**
	 * Pre: !esVacia()
	 * @param dato
	 */
	public T front();
	
	/**
	 * Pre: !esVacia()
	 * @param dato
	 */
	public T dequeueAndFront();
	
	public boolean esVacia();
	
	public boolean esLlena();
	
	public int largo();
} 
