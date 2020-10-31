package cola;

public class ColaDinamica<T> implements ICola<T> {

	private NodoCola<T> inicio;
	private NodoCola<T> fin;
	private int cantidad;
	private int tope;
	
	public ColaDinamica() {
//		this.inicio = null;
//		this.fin = null;
//		this.cantidad = 0;
		this.tope = -1;
	}
	
	public ColaDinamica(int tope) {
//		this.inicio = null;
//		this.fin = null;
//		this.cantidad = 0;
		this.tope = tope;
	}

	@Override
	public void enqueue(T dato) {
		if(fin == null)
			inicio = fin = new NodoCola<T>(dato,inicio);
		else{
			fin.setSig(new NodoCola<T>(dato));
			fin = fin.getSig();
		}
		cantidad++;
	}

	@Override
	public void dequeue() {
		inicio = inicio.getSig();
		if(inicio == null)
			fin = null;
		cantidad--;
	}

	@Override
	public T front() {
		return inicio.getDato();
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
		return cantidad == 0;
	}

	@Override
	public boolean esLlena() {
		return cantidad == tope;
	}

	@Override
	public int largo() {
		return cantidad;
	}

}
