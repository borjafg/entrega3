package uo.sdi.actions;

import javax.jms.JMSException;

import uo.sdi.MainMenu;
import uo.sdi.client.MessagingClient;
import alb.util.menu.Action;

/**
 * Clase abtracta que deben implementar aquellas clases que contengan una accion
 * asociada a un menu. <br/>
 * <br/>
 * Se encarga de pasar a las subclases una instancia de un cliente de mensajeria
 * para que puedan ejecutar la accion correspondiente (crear un mensaje, cambiar
 * el viaje del que producir y consumir mensajes...)
 * 
 */
public abstract class AbstratAction implements Action {
    @Override
    public void execute() throws Exception {
	// Se le pasa a las subclases el productor o consumidor correspondiente

	executeAction(MainMenu.getClienteMensajeria());
    }

    public abstract void executeAction(MessagingClient clienteMensajeria)
	    throws JMSException;
}