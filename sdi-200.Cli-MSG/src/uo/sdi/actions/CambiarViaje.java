package uo.sdi.actions;

import javax.jms.JMSException;

import uo.sdi.client.MessagingClient;
import uo.sdi.client.RestClient;
import alb.util.console.Console;

public class CambiarViaje extends AbstratAction {
    @Override
    public void executeAction(MessagingClient clienteMensajeria)
	    throws JMSException {
	
	Long idViaje = pedirIdViaje();

	if (idViaje != -1) {
	    new RestClient().;
	    
	    clienteMensajeria.close();
	}
	
	

    }

    /**
     * Pide al usuario que escriba el id del viaje del que quiere recibir y
     * enviar mensajes.
     * 
     * @return id del viaje
     * 
     */
    private Long pedirIdViaje() {
	Long idViaje = null;

	/*
	 * Mostrar una lista de ids de viajes en los que el usuario participa
	 */

	while (idViaje == null) {
	    Console.println("Para no cambiar de viaje escriba \'-1'");
	    idViaje = Console.readLong("Id del viaje");

	    if (idViaje == -1) {
		return new Long(-1);
	    }

	    if (idViaje == null /* || idViaje no esta en la lista de IDs */) {
		idViaje = null; // El id no es valido
	    }
	}

	return idViaje;
    }
}