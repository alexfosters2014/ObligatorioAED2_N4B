package cola;

public class ColaEstatica<T> implements ICola<T> {

	public T[] vec;
	public int tope;
	public int cant;
	public int primero;
	public int ultimo;
	
	public ColaEstatica(int tope) {
		this.vec = (T[])(new Object[tope]);
		cant = primero = ultimo = 0;
		this.tope = tope;
	}

	@Override
	public void enqueue(T dato) {
		vec[ultimo] = dato;
		ultimo = (ultimo + 1) % tope;
		cant++;
	}

	@Override
	public void dequeue() {
		vec[primero] = null;
		primero = (primero + 1) % tope;
		cant--;
	}

	@Override
	public T front() {
		return vec[primero];
	}

	@Override
	public T dequeueAndFront() {
		T primero = front();
		dequeue();
		return primero;
	}


	@Override
	public boolean esVacia() {
		//return inicio == null;
		return cant == 0;
	}

	@Override
	public boolean esLlena() {
		return cant == tope;
	}

	@Override
	public int largo() {
		return cant;
	}

}
