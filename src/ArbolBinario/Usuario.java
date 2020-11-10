package ArbolBinario;

import listas.Direccion;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import listas.Direccion;
import listas.ListaSE;
import listas.ListaSEOrd;

public class Usuario implements Comparable<Usuario> {

    private String nombre;
    private String email;
    private String password;
    private ListaSEOrd<Direccion> direcciones;
    
    public String getNombre() {
        return nombre;
    }

    public ListaSEOrd<Direccion> getDirecciones() {
        return direcciones;
    }

    public Usuario(String mail) {
        this.email = mail;
    }

    public Usuario(String nombre, String mail, String password) {
        this.nombre = nombre;
        this.email = mail;
        this.password = password;
        this.direcciones=new ListaSEOrd<Direccion>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }



    @Override
    public int compareTo(Usuario o) {
        return this.email.compareTo( ((Usuario) o).email);
    }

    public boolean ValidarEmail() {
        String expresion = "\\w{3,}@\\w{3,}\\.\\w{2,}";
        return Pattern.matches(expresion, this.email);
    }

    @Override
    public String toString() {
        return email + ";" + nombre;
    }
    
}
