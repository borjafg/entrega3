package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class CancelApplication {
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String SOLICITUD_INVALIDA = "La solicitud no es valida";
    private final String NO_PERMITIDO = "No tiene permiso para cancelar la solicitud";
    private final String ERROR = "Ha ocurrido un error al cancelar la solicitud";

    public void cancel(Long idUser, Application application) throws Exception {
	try {
	    validar(idUser, application);

	    Seat asistencia = new Seat();

	    asistencia.setUserId(idUser);
	    asistencia.setTripId(application.getTripId());
	    asistencia.setStatus(SeatStatus.EXCLUDED);
	    asistencia.setComment("Cancelado por el solicitante");

	    Factories.persistence.newSeatDao().save(asistencia);
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;

	    case SOLICITUD_INVALIDA:
		throw excep;

	    case NO_PERMITIDO:
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

	User usuario = Factories.persistence.newUserDao().findById(idUser);

	if (usuario == null || usuario.getStatus().equals(UserStatus.CANCELLED)) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	if (application == null) {
	    throw new Exception(SOLICITUD_INVALIDA);
	}

	Application solicitud = Factories.persistence.newApplicationDao()
		.findById(
			new Long[] { application.getUserId(),
				application.getTripId() });

	if (solicitud != null) {
	    throw new Exception(SOLICITUD_INVALIDA);
	}

	if (!usuario.getId().equals(application.getUserId())) {
	    throw new Exception(NO_PERMITIDO);
	}
    }
}