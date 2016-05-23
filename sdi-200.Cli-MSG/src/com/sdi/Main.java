package com.sdi;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import com.sdi.client.Consumer;
import com.sdi.client.Producer;
import com.sdi.client.TripInfo;
import com.sdi.ui.Console;

public class Main {
    private ActionsProcessor acciones;

    public static void main(String[] args) throws JMSException {
	new Main().run();
    }

    private void run() throws JMSException {
	try {
	    acciones = new ActionsProcessor();

	    boolean salir = acciones.login();

	    // TODO Primero elegir el viaje del que recibir y enviar mensajes
	    //
	    // =====================

	    Console.println();
	    Console.println("Ver las acciones disponibles --> ayuda");
	    Console.println();

	    while (!salir) {
		String entrada = Console.readString("--> ");

		evaluar(entrada);
	    }
	}

	catch (Exception e) {
	    System.out.println(e.getLocalizedMessage());
	}

	finally {
	    acciones.close();
	}
    }

    /**
     * Toma un texto que escribio el usuario en la consola y ejecuta la accion
     * asociada a él, o muestra un mensaje en la consola si no se corresponde
     * con ninguna accion.
     * 
     * @param entrada
     *            entrada proporcionada por el usuario
     * 
     * @throws JMSException
     * 
     */
    private void evaluar(String entrada) throws JMSException {
	switch (entrada) {

	case "ayuda":
	    mostrarAyuda();
	    break;

	case "msg":
	    String msg = pedirMensaje();

	    if (msg.equals("cancelar")) {
		break;
	    }
	    
	    acciones.enviarMensaje(msg);
	    break;

	case "change":
	    Long idViaje = pedirIdViaje();

	    if (idViaje.equals(new Long(-1))) {
		break;
	    }

	    // TODO Cambiar el viaje del que recibir y enviar mensajes
	    break;

	default: // entrada desconocida
	    break;
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
     * Pide al usuario que escriba el mensaje que quiere enviar a todos los
     * participantes del viaje que selecciono.
     * 
     * @return mensaje a enviar, 'cancelar' si no quiere enviar ningun mensaje
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
    
    /**
     * Muestra al usuario una lista de acciones que puede realizar.
     * 
     */
    public void mostrarAyuda() {
	Console.println("---------");
	Console.println("Acciones a realizar. Escriba solo el comando");
	Console.println("Despues se le pediran los datos apropiados (por "
		+ "ejemplo, el mensaje a enviar)");
	Console.println();
	Console.println("Enviar un mensaje a los participantes del viaje --> msg");
	Console.println("Enviar y recibir mensajes sobre otro viaje --> change");
	Console.println("Salir del programa --> salir");
	Console.println("---------");
    }
}