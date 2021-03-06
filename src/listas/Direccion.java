package listas;

import com.edu.ort.grafos.Punto;
import java.util.Objects;

public class Direccion implements Comparable<Direccion> {

    private Punto punto;
    private int repeticiones;

    public Direccion(Punto punto) {
        this.punto = punto;
        this.repeticiones = 1;
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public void aumentarRepeticiones() {
        repeticiones++;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            final Direccion other = (Direccion) obj;
            if (other.getPunto().getCoordX() == this.getPunto().getCoordX()
                    && other.getPunto().getCoordY() == this.getPunto().getCoordY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Direccion o) {
        return (o.getRepeticiones() - this.getRepeticiones());
    }

    @Override
    public String toString() {
        return "Direccion{" + "punto=" + punto + ", repeticiones=" + repeticiones + '}';
    }

}
