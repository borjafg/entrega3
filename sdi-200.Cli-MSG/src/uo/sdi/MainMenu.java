package uo.sdi;

import javax.jms.JMSException;

import uo.sdi.actions.CambiarViaje;
import uo.sdi.actions.EnviarMensaje;
import uo.sdi.client.MessagingClient;
import alb.util.console.Console;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
    private static MessagingClient clienteMensajeria;

    public static void main(String[] args) throws JMSException {
	new MainMenu().run();
    }

    private void run() throws JMSException {
	try {
	    incializar();

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

    public void inicializar() {
	
    }
}