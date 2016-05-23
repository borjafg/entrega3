package com.sdi;

import javax.jms.JMSException;

import com.sdi.model.MessagingClient;

import alb.util.console.Console;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
    private MessagingClient clienteMensajeria;

    public static void main(String[] args) throws JMSException {
	new MainMenu().run();
    }

    private void run() throws JMSException {
	try {
	    

	    // TODO Primero elegir el viaje del que recibir y enviar mensajes
	    //
	    // =====================

	    menuOptions = new Object[][] {
	    /*
	     * { "Listar viajes promovidos por el usuario y abiertos",
	     * ListarViajesPromovidos.class }, { "Confirmar viajes",
	     * ConfirmarPasajeros.class }
	     */};
	}

	catch (Exception e) {
	    System.out.println(e.getLocalizedMessage());
	}

	finally {
	    clienteMensajeria.close();
	}
    }

    /**
     * Pide al usuario que escriba el mensaje que quiere enviar a todos los
     * participantes del viaje que selecciono.
     * 
     * @return mensaje a enviar, 'cancelar' si no quiere enviar ningun mensaje
     * 
     */
    private String pedirMensaje() {
	boolean mensajeValido = false;
	String mensaje = "";

	while (!mensajeValido) {
	    Console.println("Para no enviar ningun mensaje escriba \'cancelar\'");
	    mensaje = Console.readString("Mensaje a enviar");

	    if (mensaje != null && !mensaje.equals("")) {
		mensajeValido = true;
	    }
	}

	return mensaje;
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