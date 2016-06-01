package uo.sdi;

import uo.sdi.actions.CambiarViaje;
import uo.sdi.actions.EnviarMensaje;
import uo.sdi.client.MessagingClient;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import alb.util.console.Console;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
    private static MessagingClient clienteMensajeria;
    
    private static User user;
    private static Trip viaje;

    public static void main(String[] args) throws Exception {
	MainMenu main = new MainMenu();
	
	try{
	    main.inicializar();
	    main.run();
	}
	
	catch (Exception excep) {
	    if(!excep.getMessage().equals("salir")) {
		Console.print(excep.getMessage());
	    }
	}
    }

    private void inicializar() throws Exception {
	new Init().run();
    }

    private void run() throws Exception {
	try {
	    menuOptions = new Object[][] {
		    { "Enviar un mensaje", EnviarMensaje.class },
		    { "Cambiar viaje", CambiarViaje.class } };
	}

	catch (Exception e) {
	    System.out.println(e.getLocalizedMessage());
	}

	finally {
	    clienteMensajeria.close();
	}
    }

    /**
     * Devuelve el cliente de mensajeria que utiliza la aplicacion para crear,
     * consumir y producir mensajes.
     * 
     * @return cliente de mensajeria
     * 
     */
    public static MessagingClient getClienteMensajeria() {
	return clienteMensajeria;
    }
    
    public static void setUser(User user) {
	MainMenu.user = user;
    }
    
    public static User getUser() {
	return user;
    }
}