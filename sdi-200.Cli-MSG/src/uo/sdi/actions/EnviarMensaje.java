package uo.sdi.actions;

import javax.jms.JMSException;

import alb.util.console.Console;
import uo.sdi.client.MessagingClient;
import uo.sdi.util.menu.Action;

public class EnviarMensaje implements Action {

    @Override
    public void execute(MessagingClient clienteMensajeria)
	    throws JMSException {
	// TODO Auto-generated method stub

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
}