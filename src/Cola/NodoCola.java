package cola;

public class NodoCola<T> {
	private T dato;
	private NodoCola<T> sig;
	
	// alt shift s + o + enter
	public NodoCola(T dato, NodoCola<T> sig) {
		this.dato = dato;
		this.sig = sig;
	}
	
	public NodoCola(T dato) {
		this.dato = dato;
		this.sig = null;
	}
	
	// alt shift s + r + alt a + o + enter
	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	public NodoCola<T> getSig() {
		return sig;
	}
	public void setSig(NodoCola<T> sig) {
		this.sig = sig;
	}
	
	//alt shift s + s + enter
	@Override
	public String toString() {
		return dato+"";
	}
	
	
}
