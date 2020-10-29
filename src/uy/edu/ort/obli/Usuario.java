package uy.edu.ort.obli;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Comparable<Usuario> {

    private String nombre;
    private String email;
    private String password;

    public String getNombre() {
        return nombre;
    }

    public Usuario(String mail) {
        this.email = mail;
    }

    public Usuario(String nombre, String mail, String password) {
        this.nombre = nombre;
        this.email = mail;
        this.password = password;
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
    public boolean equals(Object obj) {
        if (obj != null) {
            if (getClass() == obj.getClass()) {

                final Usuario usu = (Usuario) obj;
                if (!Objects.equals(this.email, usu.email)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int compareTo(Usuario o) {
        return this.email.compareTo( ((Usuario) o).email);
    }

    public boolean ValidarEmail() {
        String expresion = "^\\d{8}$";
        return Pattern.matches(expresion, this.email);
    }

    @Override
    public String toString() {
        return email + ";" + nombre;
    }
    
}
