package uy.edu.ort.obli;

import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {

	@Override
	public Retorno inicializarSistema(int maxPuntos) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno destruirSistema() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarUsuario(String email, String nombre, String password) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno buscarUsuario(String email) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listarUsuarios() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
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
