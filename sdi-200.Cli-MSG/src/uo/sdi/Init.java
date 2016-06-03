package uo.sdi;

import uo.sdi.client.MessagingClient;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.util.Input;

/**
 * Su unica funcion es inicializar la aplicación. Sólo se utiliza al arrancar la
 * aplicación. <br/>
 * <br/>
 * Está separada de la clase MainMenu para que el codigo quede más legible, y
 * porque de esta forma si se quisiera cambiar lo que ocurre al iniciar la
 * aplicación sólo habría que tocar y recompilar esta clase.
 * 
 */
public class Init {
    public void run(MainMenu main) throws Exception {
	User user = Input.login();
	Trip viaje = Input.elegirViaje(user);

	MessagingClient cliente = new MessagingClient();
	
	cliente.setUser(user);
	cliente.inicializar_Productor_Consumidor(viaje);
	
	main.setClienteMensajeria(cliente);
    }
}