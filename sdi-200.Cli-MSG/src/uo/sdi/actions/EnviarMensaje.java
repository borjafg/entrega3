package uo.sdi.actions;

import javax.jms.JMSException;

import alb.util.console.Console;
import uo.sdi.client.MessagingClient;

public class EnviarMensaje extends AbstratAction {

    @Override
    public void executeAction(MessagingClient clienteMensajeria)
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