/*package uo.sdi.business.integration;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import uo.sdi.business.errorMessages.ErrorMessages;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import alb.util.log.Log;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/MensajeriaCorreo") })
public class MessageListener implements javax.jms.MessageListener {
    private String causaError = "";

    @Override
    public void onMessage(Message msg) {
	try {
	    process(msg);
	}

	catch (JMSException jex) {
	    jex.printStackTrace();
	}
    }

    private void process(Message msg) throws JMSException {
	// ===================================
	// Extraer la informacón de la mensaje
	// ===================================

	MapMessage mensaje = (MapMessage) msg;

	String login = mensaje.getString("from");
	Long idViaje = mensaje.getLong("idViaje");

	// ==================
	// Validar el mensaje
	// ==================

	boolean valido = false;

	try {
	    valido = validar(login, idViaje);
	}

	catch (Exception excep) {
	    // Habra que indicar que ocurrio un error al procesar el
	    // mensaje y dejar que se decida que hacer con el en el
	    // futuro
	    return;
	}

	// ==========================
	// Si no ocurrio ningun error
	// ==========================

	if (valido) { // Topic de mensajes procesados
	    guardarMensaje_Procesados(mensaje);
	}

	else { // Cola de LOG
	    guardarMensaje_Log(mensaje);
	}
    }

    private boolean validar(String login, Long idViaje) throws Exception {
	try {
	    User user = Factories.services.getUserService().getUserByLogin(
		    login);

	    Trip trip = Factories.services.getTripService()
		    .getInfoTrip(idViaje);

	    if (user == null || user.getStatus().equals(UserStatus.CANCELLED)) {
		causaError = "El usuario no es valido";
		return false;
	    }

	    if (trip == null) {
		causaError = "El viaje no es valido";
		return false;
	    }

	    return participaViaje(user.getId(), trip.getId());
	}

	catch (Exception excep) {
	    Log.warn("El mensaje procesado no es valido, usuario = \"%s\" "
		    + "- Id viaje = \"%d\"", login, idViaje);

	    throw excep;
	}
    }

    private boolean participaViaje(Long idUser, Long idTrip) throws Exception {
	try {
	    List<Trip> viajes = Factories.services.getTripService()
		    .findTripsUser(idUser);

	    for (Trip viaje : viajes) {
		if (viaje.getId().equals(idTrip)) {
		    return true;
		}
	    }

	    return false;
	}

	catch (Exception excep) {
	    if (excep.getMessage().equals(
		    ErrorMessages.getMessage("USUARIO_INVALIDO"))) {

		causaError = "El usuario no es valido";
	    }

	    else {

	    }

	    throw excep;
	}
    }

    // ==========================
    // Almacenamiento de mensajes
    // ==========================

    public void guardarMensaje_Procesados(Message mensaje) {
	
    }
    
    public void guardarMensaje_Log(Message mensaje) {
	
    }
}*/