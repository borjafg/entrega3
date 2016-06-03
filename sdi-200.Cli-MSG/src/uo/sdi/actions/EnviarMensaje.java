package uo.sdi.actions;

import javax.jms.JMSException;

import uo.sdi.client.MessagingClient;
import uo.sdi.util.Input;
import uo.sdi.util.menu.Action;

public class EnviarMensaje implements Action {

    @Override
    public void execute(MessagingClient cliente) throws JMSException {
	String mensaje = Input.pedirString("Mensaje a enviar (Escriba "
		+ "\"salir\" para no enviar nada)");
	
	if (mensaje.equals("salir")) {
	    return;
	}
	
	else {
	    cliente.enviarMensaje(mensaje);
	}
    }
    
}