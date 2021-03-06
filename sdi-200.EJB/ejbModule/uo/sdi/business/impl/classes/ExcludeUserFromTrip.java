package uo.sdi.business.impl.classes;

import java.util.Date;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class ExcludeUserFromTrip {
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String SOLICITUD_INVALIDA = "La solicitud no es valida";
    private final String VIAJE_CANCELADO = "El viaje fue cancelado";
    private final String NO_PERMITIDO = "Solo el promotor puede cancelar una "
	    + "solicitud";
    private final String FUERA_PLAZO = "Ya paso la fecha de cierre del viaje";
    private final String ERROR = "Ha ocurrido un error al excluir al usuario del"
	    + "del viaje";

    public void exclude(Long idUser, Application application) throws Exception {
	try {
	    validar(idUser, application);

	    Seat asistencia = Factories.persistence.newSeatDao().findById(
		    new Long[] { application.getUserId(),
			    application.getTripId() });

	    // No fue ni aceptado ni rechazado
	    if (asistencia == null) {
		asistencia = new Seat();

		asistencia.setUserId(application.getUserId());
		asistencia.setTripId(application.getTripId());
		asistencia.setStatus(SeatStatus.EXCLUDED);
		asistencia.setComment("Rechazada por el promotor");

		Factories.persistence.newSeatDao().save(asistencia);
	    }
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;

	    case SOLICITUD_INVALIDA:
		throw excep;

	    case NO_PERMITIDO:
		throw excep;

	    case VIAJE_CANCELADO:
		throw excep;

	    case FUERA_PLAZO:
		throw excep;

	    default:
		throw new Exception(ERROR);
	    }
	}
    }

    private void validar(Long idUser, Application application) throws Exception {
	if (idUser == null) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	User promotor = Factories.persistence.newUserDao().findById(idUser);

	if (promotor == null
		|| promotor.getStatus().equals(UserStatus.CANCELLED)) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	if (application == null) {
	    throw new Exception(SOLICITUD_INVALIDA);
	}

	Application solicitud = Factories.persistence.newApplicationDao()
		.findById(
			new Long[] { application.getUserId(),
				application.getTripId() });

	if (solicitud == null) {
	    throw new Exception(SOLICITUD_INVALIDA);
	}

	Trip viaje = Factories.persistence.newTripDao().findById(
		application.getTripId());

	if (viaje == null) {
	    throw new Exception(SOLICITUD_INVALIDA);
	}

	if (viaje.getStatus().equals(TripStatus.CANCELLED)) {
	    throw new Exception(VIAJE_CANCELADO);
	}

	if (!promotor.getId().equals(viaje.getPromoterId())) {
	    throw new Exception(NO_PERMITIDO);
	}

	if (viaje.getClosingDate().before(new Date())) {
	    throw new Exception(FUERA_PLAZO);
	}
    }
}