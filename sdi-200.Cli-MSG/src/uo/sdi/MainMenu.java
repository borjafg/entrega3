package uo.sdi;

import uo.sdi.actions.CambiarViaje;
import uo.sdi.actions.EnviarMensaje;
import uo.sdi.client.MessagingClient;
import uo.sdi.util.menu.BaseMenu;
import alb.util.console.Console;

public class MainMenu extends BaseMenu {
    /**
     * Cliente de mensajeria que utiliza la aplicacion para crear, producir y
     * consumir mensajes.
     */
    private MessagingClient clienteMensajeria;

    public static void main(String[] args) throws Exception {
	MainMenu main = new MainMenu();

	try {
	    main.inicializar();

	    main.run();
	}

	catch (Exception excep) {
	    if (!excep.getMessage().equals("salir")) {
		Console.print(excep.getMessage());
	    }
	}
    }

    /**
     * Preparar la ejecución de la aplicación
     * 
     * @throws Exception
     *             ha ocurrido un error o el usuario desea salir
     * 
     */
    private void inicializar() throws Exception {
	new Init().run(this);
    }

    private void run() throws Exception {
	try {
	    menuOptions = new Object[][] {
		    { "Enviar un mensaje", EnviarMensaje.class },
		    { "Cambiar viaje", CambiarViaje.class } };

	    execute(clienteMensajeria);
	}

	catch (Exception e) {
	    System.out.println(e.getLocalizedMessage());
	}

	finally {
	    clienteMensajeria.close();
	}
    }

    // =================
    // Getters y Setters
    // =================

    public void setClienteMensajeria(MessagingClient cliente) {
	clienteMensajeria = cliente;
    }

    public MessagingClient getClienteMensajeria() {
	return clienteMensajeria;
    }
}