
import uy.edu.ort.obli.Sistema;


public class Main {
    
    public static void main(String[] args) {
     
        Sistema sistema = new Sistema();
        
        sistema.inicializarSistema(11);
		sistema.registrarUsuario("CAP1891@gmail.com", "Omar", "HolaSoyOmar1891");
		sistema.registrarDelivery("4", 4.0, 4.0);
		sistema.registrarMovil("7", 7.0, 7.0);
		sistema.registrarDelivery("10", 10.0, 10.0);
		sistema.registrarMovil("11", 11.0, 11.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarDelivery("yo",2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(6.0, 6.0);
		sistema.registrarMovil("movil",8.0, 8.0);
		sistema.registrarEsquina(9.0, 9.0);
                
                sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 5, 5);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 3, 3);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 1, 1);
		sistema.registrarTramo(2.0, 2.0, 5.0, 5.0, 11, 11);
		sistema.registrarTramo(3.0, 3.0, 6.0, 6.0, 2, 2);
		sistema.registrarTramo(3.0, 3.0, 11.0, 11.0, 19, 19);
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 14, 14);
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 6, 6);
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 6, 6); //doble
		sistema.registrarTramo(5.0, 5.0, 8.0, 8.0, 1, 1);
		sistema.registrarTramo(6.0, 6.0, 9.0, 9.0, 3, 3);
		sistema.registrarTramo(7.0, 7.0, 8.0, 8.0, 8, 8);
		sistema.registrarTramo(8.0, 8.0, 7.0, 7.0, 8, 8); //doble
		sistema.registrarTramo(8.0, 8.0, 9.0, 9.0, 5, 5); 
		sistema.registrarTramo(9.0, 9.0, 8.0, 8.0, 5, 5); //doble
		sistema.registrarTramo(9.0, 9.0, 10.0, 10.0, 4, 4);
                
                sistema.movilMasCercano(7.0,7.0);
                
                sistema.dibujarMapa();
    }
    

}
