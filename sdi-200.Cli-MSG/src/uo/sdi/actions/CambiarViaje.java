package uo.sdi.actions;

import uo.sdi.client.MessagingClient;
import uo.sdi.model.Trip;
import uo.sdi.util.Input;
import uo.sdi.util.ShowTrip;
import uo.sdi.util.menu.Action;
import alb.util.console.Console;

public class CambiarViaje implements Action {
    @Override
    public void execute(MessagingClient cliente) throws Exception {
	try {
	    Console.println("==========================");
	    ShowTrip.show(cliente.getViaje());
	    Console.println("==========================");

	    Trip viaje = Input.elegirViaje(cliente.getUser());

	    // Si ha elegido un viaje distinto al actual
	    if (!viaje.getId().equals(cliente.getViaje().getId())) {
		cliente.inicializar_Productor_Consumidor(viaje);
		Console.println("Viaje cambiado con exito");
	    }
	    
	    else {
		Console.println("El viaje es el mismo que estaba seleccionado");
	    }
	}

	catch (Exception excep) {
	    if (excep.getMessage().equals(Input.NO_PARTICIPA_VIAJES)) {
		throw excep;
	    }

	    else if (excep.getMessage().equals(Input.SALIR)) {
		Console.println("Ha decidido no cambiar el viaje sobre el que "
			+ "recibir y enviar mensajes");
	    }

	    else { // En cualquier otro caso
		throw excep;
	    }
	}
    }

}