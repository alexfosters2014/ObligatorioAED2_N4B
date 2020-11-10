package uy.edu.ort.obli;

import ArbolBinario.Usuario;
import ArbolBinario.*;
import com.edu.ort.grafos.Delivery;
import com.edu.ort.grafos.Esquina;
import com.edu.ort.grafos.Grafo;
import com.edu.ort.grafos.Movil;
import com.edu.ort.grafos.Punto;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import listas.Direccion;

import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

    ABB<Usuario> usuarios;
    Grafo miMapa;

    @Override
    public Retorno inicializarSistema(int maxPuntos) {
        Retorno retorno = new Retorno(Resultado.OK);
        if (maxPuntos <= 0){
            retorno.resultado=Resultado.ERROR_1;
            return retorno;
        }
        miMapa = new Grafo(maxPuntos);
        usuarios = new ABB<Usuario>();
        return retorno;
    }

    @Override
    public Retorno destruirSistema() {
       Retorno retorno = new Retorno(Resultado.OK);
        miMapa = null;
        usuarios = null;
        return retorno;
    }

    @Override
    public Retorno registrarUsuario(String email, String nombre, String password) {
        Retorno retorno = new Retorno(Resultado.OK);

        Usuario usuario = new Usuario(nombre, email.toLowerCase(), password);

        if (!usuario.ValidarEmail()) {
            retorno.resultado = Resultado.ERROR_1;
            return retorno;
        }

        if (usuarios.pertenece(usuario)) {
            retorno.resultado = Resultado.ERROR_2;
            return retorno;
        }
        usuarios.insertar(usuario);
        return retorno;
    }

    @Override
    public Retorno buscarUsuario(String email) {
        Retorno retorno = new Retorno(Resultado.OK);

        Usuario usuario = new Usuario(email.toLowerCase());

        if (!usuario.ValidarEmail()) {
            retorno.resultado = Resultado.ERROR_1;
            return retorno;
        }
        Entero ent = new Entero();
        Usuario usuarioBuscado = usuarios.buscar(usuario, ent);
        if (usuarioBuscado == null) {
            retorno.resultado = Resultado.ERROR_2;
            return retorno;
        }
        retorno.valorString = usuarioBuscado.toString();
        retorno.valorEntero = ent.numero;
        return retorno;
    }

    @Override
    public Retorno listarUsuarios() {
        Retorno retorno = new Retorno(Resultado.OK);
        newString listado = new newString();
        usuarios.listarAsc(listado);
        retorno.valorString = listado.dato;
        return retorno;
    }

    @Override
    public Retorno direccionesDeUsuario(String email) {
        Retorno retorno = new Retorno(Resultado.OK);
        Usuario usuario = new Usuario(email);
        if (!usuario.ValidarEmail()) {
            retorno.resultado = Resultado.ERROR_1;
            return retorno;
        }
        Usuario usuarioBuscado = usuarios.buscar(usuario, new Entero());
        if (usuarioBuscado == null) {
            retorno.resultado = Resultado.ERROR_2;
            return retorno;
        }
        for (Direccion dir : usuarioBuscado.getDirecciones()) {
            retorno.valorString += dir.getPunto().getCoordX() + ";" + dir.getPunto().getCoordY() + "|";
        }
        retorno.valorString = retorno.valorString.substring(0, retorno.valorString.length() - 1);
        return retorno;
    }

    @Override
    public Retorno registrarEsquina(double coordX, double coordY) {
        Punto nuevaEsquina = new Esquina(coordX, coordY);
        return RegistroPunto(nuevaEsquina);
    }

    //metodo auxiliar para registrar puntos
    private Retorno RegistroPunto(Punto punto) {
        Retorno retorno = new Retorno(Resultado.OK);
        if (miMapa.esLleno()) {
            retorno.resultado = Resultado.ERROR_1;
        } else if (miMapa.existeVertice(punto)) {
            retorno.resultado = Resultado.ERROR_2;
        } else {
            miMapa.agregarVertice(punto);
        }
        return retorno;
    }

    @Override
    public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int minutos) {
        Retorno retorno = new Retorno(Resultado.OK);
        Punto puntoOrigen = miMapa.buscarVertice(new Esquina(coordXi, coordYi));
        Punto puntoDestino = miMapa.buscarVertice(new Esquina(coordXf, coordYf));

        if (metros <= 0) {
            retorno.resultado = Resultado.ERROR_1;
            return retorno;
        }
        if (minutos <= 0) {
            retorno.resultado = Resultado.ERROR_2;
            return retorno;
        }
        if (puntoOrigen == null || puntoDestino == null) {
            retorno.resultado = Resultado.ERROR_3;
            return retorno;
        }
        if (miMapa.existeArista(puntoOrigen, puntoDestino)) {
            retorno.resultado = Resultado.ERROR_4;
            return retorno;
        }
        //tengo que ver como agregarArista del delivery que es no dirigo
        miMapa.agregarArista(puntoOrigen, puntoDestino, metros, minutos);
        return retorno;
    }

    @Override
    public Retorno registrarDelivery(String cedula, Double coordX, Double coordY) {
        Punto nuevoDelivery = new Delivery(coordX, coordY, cedula);
        return RegistroPunto(nuevoDelivery);
    }

    @Override
    public Retorno registrarMovil(String matricula, Double coordX, Double coordY) {
        Punto nuevoMovil = new Movil(coordX, coordY, matricula);
        return RegistroPunto(nuevoMovil);
    }

    @Override
    public Retorno movilMasCercano(Double coordXi, Double coordYi) {
        return miMapa.dijkstra_MasCercano(new Esquina(coordXi, coordYi), Grafo.enumPuntos.MOVIL);
    }

    @Override
    public Retorno deliveryMasCercano(Double coordXi, Double coordYi) {//haciendo
        Retorno retorno = new Retorno(Resultado.OK);
        Punto esquina = miMapa.buscarVertice(new Esquina(coordXi, coordYi)); //cualquier punto
        if (esquina == null) {
            retorno.resultado = Resultado.ERROR_1;
            return retorno;
        }
        return miMapa.dijkstra_MasCercano(esquina, Grafo.enumPuntos.DELIVERY);
    }

    @Override
    public Retorno caminoMinimoMovil(Double coordXi, Double coordYi, Double coordXf, Double coordYf, String email) {
        Retorno retorno = new Retorno(Resultado.OK);
        Punto origen = new Esquina(coordXi, coordYi);
        Punto destino = new Esquina(coordXf, coordXf);
        retorno = miMapa.dijkstra_Movil_D(origen, destino);
        
        
        Usuario usuario=usuarios.buscar(new Usuario(email), new Entero());
        if (retorno.resultado == Resultado.OK && usuario != null){
           Direccion direccion = usuario.getDirecciones().recuperar(new Direccion(destino));
           if (direccion == null){
               usuario.getDirecciones().borrar(direccion);
               direccion.aumentarRepeticiones();
               usuario.getDirecciones().insertarOrd(direccion);
               
           }else{
               usuario.getDirecciones().insertarOrd(direccion);
           }
        }
        return retorno;
    }

    @Override
    public Retorno caminoMinimoDelivery(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        Retorno retorno = new Retorno(Resultado.OK);
        Punto origen = new Esquina(coordXi, coordYi);
        Punto destino = new Esquina(coordXf, coordXf);
        return miMapa.dijkstra_Delivery_ND(origen, destino);
    }

    @Override
    public Retorno dibujarMapa() {
       Retorno retorno = new Retorno(Resultado.OK);
        try {
             Desktop.getDesktop().browse(new URL(urlWeb()).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return retorno;
    }
    
    public String urlWeb(){
        String url="";
        url = "http://maps.googleapis.com/maps/api/staticmap?"+
              "&size=1200x600"+
              "&maptype=roadmap"+
               miMapa.urlPuntos()+
               "&key=AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw" ;
        return url;
    }
}
