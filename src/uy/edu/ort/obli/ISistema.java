package uy.edu.ort.obli;

public interface ISistema {

	 Retorno inicializarSistema (int maxPuntos);
	 
	 Retorno destruirSistema();
	 
	 Retorno registrarUsuario(String email, String nombre, String password);

	 Retorno buscarUsuario(String email);
	 
	 Retorno listarUsuarios();
	 
	 Retorno direccionesDeUsuario(String email);
	 
	 Retorno registrarEsquina(double coordX, double coordY);
	 
	 Retorno registrarDelivery(String cedula, double coordX, double coordY);
	 
	 Retorno registrarMovil(String matricula, double coordX, double coordY);

	 Retorno registrarTramo(double coordXi, double coordYi, double
			 coordXf, double coordYf, int metros);
	 
	 Retorno movilMasCercano(double coordXi, double coordYi);

	 Retorno deliveryMasCercano(double coordXi, double coordYi);
	 
	 Retorno caminoMinimoMovil(double coordXi, double coordYi, double coordXf, double coordYf);
	 
	 Retorno caminoMinimoDelivery(double coordXi, double coordYi, double coordXf, double coordYf);
	 
	 Retorno dibujarMapa();
	
}
