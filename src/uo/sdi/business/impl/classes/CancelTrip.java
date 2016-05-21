package uo.sdi.business.impl.classes;

import java.util.Date;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;

public class CancelTrip {
    private final String VIAJE_INVALIDO = "El viaje no es valido";
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String NO_PROMOTOR = "El usuario no es el promotor del viaje";
    private final String VIAJE_CERRADO = "Ya paso la fecha de cierre del viaje";
    private final String VIAJE_YA_CANCELADO = "El viaje ya fue cancelado";

    /*
     * Contienen el usuario y el viaje (extraidos de la base de datos por si su
     * informacion habia cambiado)
     */
    private Trip viaje;
    private User usuario;

    public void cancel(Trip trip, User user) throws Exception {
	try {
	    validar(trip, user);

	    trip.setStatus(TripStatus.CANCELLED);
	    viaje.setStatus(TripStatus.CANCELLED);

	    Factories.persistence.newTripDao().save(viaje);
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case VIAJE_INVALIDO:
		throw excep;

	    case USUARIO_INVALIDO:
		throw excep;

	    case NO_PROMOTOR:
		throw excep;

	    case VIAJE_CERRADO:
		throw excep;
		
	    case VIAJE_YA_CANCELADO:
		throw excep;

	    default: // En cualquier otro caso
		throw new Exception("Error al cancelar el viaje");
	    }
	}
    }

    /* ========================================================== */
    /* Separada la parte de validacion de parametros de la logica */
    /* ========================================================== */

    private void validar(Trip trip, User user) throws Exception {
	if (trip == null) {
	    throw new Exception(VIAJE_INVALIDO);
	}

	if (user == null) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	// ================================================================
	// (1) Buscar el viaje por si no estuviera en la base de datos o su
	// informacion hubiera cambiado. Hacer lo mismo con el usuario
	// ================================================================

	this.viaje = Factories.persistence.newTripDao().findById(trip.getId());

	this.usuario = Factories.persistence.newUserDao()
		.findById(user.getId());

	// ================================================================
	// (2) Validar el viaje y el usuario
	// ================================================================

	if (viaje == null) {
	    throw new Exception(VIAJE_INVALIDO);
	}

	if (usuario == null) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	if (viaje.getPromoterId().equals(usuario.getId())) {
	    throw new Exception(NO_PROMOTOR);
	}

	if (viaje.getClosingDate().before(new Date())) {
	    throw new Exception(VIAJE_CERRADO);
	}
	
	if(viaje.getStatus().equals(TripStatus.CANCELLED)) {
	    throw new Exception(VIAJE_YA_CANCELADO);
	}
    }
}