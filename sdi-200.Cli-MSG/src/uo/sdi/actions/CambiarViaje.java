package uo.sdi.actions;

import uo.sdi.client.MessagingClient;
import uo.sdi.model.Trip;
import uo.sdi.util.Input;
import uo.sdi.util.ShowTrip;
import uo.sdi.util.menu.Action;
import alb.util.console.Console;

public class CambiarViaje implements Action {
    @Override
    public void execute(MessagingClient cliente) {
	try {
	    Console.println("==========================");
	    ShowTrip.show(cliente.getViaje());
	    Console.println("==========================");
	    
	    Trip viaje = Input.elegirViaje(cliente.getUser());
	}
	
	catch(Exception excep) {
	    
	}
    }
    
}