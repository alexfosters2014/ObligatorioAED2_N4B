package uy.edu.ort.obli;

import ArbolBinario.*;
import com.edu.ort.grafos.Delivery;
import com.edu.ort.grafos.Esquina;
import com.edu.ort.grafos.Grafo;
import com.edu.ort.grafos.Movil;
import com.edu.ort.grafos.Punto;

import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

    ABB<Usuario> usuarios = new ABB<Usuario>();
    Grafo miMapa;
    
    @Override
    public Retorno inicializarSistema(int maxPuntos) {
        Retorno retorno = new Retorno(Resultado.OK);
        miMapa=new Grafo(maxPuntos, false);
        
        return retorno;
    }

    @Override
    public Retorno destruirSistema() {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarUsuario(String nombre, String email, String password) {
        Retorno retorno = new Retorno(Resultado.OK);

        Usuario usuario = new Usuario(nombre, email, password);

        if (!usuario.ValidarEmail()) {
            retorno.resultado = Resultado.ERROR_1;
            return retorno;
        }

        if (usuarios.pertenece(usuario)) {
            retorno.resultado = Resultado.ERROR_2;
            return retorno;
        }
        return retorno;
    }

    @Override
    public Retorno buscarUsuario(String email) {
          Retorno retorno = new Retorno(Resultado.OK);

         Usuario usuario=new Usuario(email);
         NodoABB<Usuario> usuarioBuscado;
         
         if (!usuario.ValidarEmail()){
            retorno.resultado=Resultado.ERROR_1;
            return retorno;
        }
         usuarioBuscado=usuarios.buscar(usuario);
       if (usuarioBuscado==null){
            retorno.resultado=Resultado.ERROR_2;
            return retorno;
        }
       retorno.valorString=usuarioBuscado.getDato().toString();
       retorno.valorEntero=usuarioBuscado.getCantidadRecorridas();
       return retorno;
    }

    @Override
    public Retorno listarUsuarios() {
         Retorno retorno = new Retorno(Resultado.OK);
             retorno.valorString=usuarios.listarAscStr();
             return retorno;
    }

    @Override
    public Retorno direccionesDeUsuario(String email) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarEsquina(double coordX, double coordY) {
       Punto nuevaEsquina =new Esquina(coordX, coordY);
       return RegistroPunto(nuevaEsquina);
    }
    //metodo auxiliar para registrar puntos
    private Retorno RegistroPunto(Punto punto){
        Retorno retorno = new Retorno(Resultado.OK);
      if (miMapa.esLleno()){
         retorno.resultado=Resultado.ERROR_1;
       }
      else if (miMapa.existeVertice(punto)){
         retorno.resultado=Resultado.ERROR_2;
       }   
      else{
          miMapa.agregarVertice(punto);
      }
       return retorno;
    }
    
    @Override
    public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros,int minutos) {
      Retorno retorno = new Retorno(Resultado.OK);
        Punto puntoOrigen=miMapa.buscarVertice(coordXi, coordYi);
        Punto puntoDestino=miMapa.buscarVertice(coordXf, coordYf);
        
        if (metros<=0){
            retorno.resultado=Resultado.ERROR_1;
            return retorno;
        }
         if (minutos<=0){
            retorno.resultado=Resultado.ERROR_2;
            return retorno;
        }
          if (puntoOrigen == null && puntoDestino == null){
            retorno.resultado=Resultado.ERROR_3;
            return retorno;
        }
            if (miMapa.existeArista(puntoOrigen, puntoDestino)){
            retorno.resultado=Resultado.ERROR_4;
            return retorno;
        }
            //tengo que ver como agregarArista del delivery que es no dirigo
            miMapa.agregarArista(puntoOrigen, puntoDestino, metros, minutos);
            return retorno;
    }

    @Override
    public Retorno registrarDelivery(String cedula, Double coordX, Double coordY) {
        Punto nuevoDelivery =new Delivery(coordX, coordY, cedula);
       return RegistroPunto(nuevoDelivery);
    }

    @Override
    public Retorno registrarMovil(String matricula, Double coordX, Double coordY) {
        Punto nuevoMovil =new Movil(coordX, coordY, matricula);
       return RegistroPunto(nuevoMovil);
    }

    @Override
    public Retorno movilMasCercano(Double coordXi, Double coordYi) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno deliveryMasCercano(Double coordXi, Double coordYi) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno caminoMinimoMovil(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno caminoMinimoDelivery(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno dibujarMapa() {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

}
