package uy.edu.ort.obli;

import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

    ABB<Usuario> usuarios = new ABB<Usuario>();

    @Override
    public Retorno inicializarSistema(int maxPuntos) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
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
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarDelivery(String cedula, Double coordX, Double coordY) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarMovil(String matricula, Double coordX, Double coordY) {
        return new Retorno(Resultado.NO_IMPLEMENTADA);
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
