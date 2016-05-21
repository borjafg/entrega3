package uo.sdi.business.impl.classes;

import java.util.Date;

import javax.persistence.EntityExistsException;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;

public class ApplicateForTrip {
    private final String VIAJE_INEXISTENTE = "El viaje no existe";
    private final String VIAJE_CERRADO = "El viaje esta cerrado";
    private final String VIAJE_CANCELADO = "El viaje esta cancelado";
    private final String YA_SOLICITADO = "Ya habia solicitado asistir al viaje";
    private final String OTRA_CAUSA = "No se ha podido guardar la solicitud de "
    	+ "asistencia al viaje";
    
    public void applicate(Long idTrip, Long idUser) throws Exception {
	try {
	    Trip viaje = Factories.persistence.newTripDao().findById(idTrip);

	    if (viaje == null) {
		throw new Exception(VIAJE_INEXISTENTE);
	    }

	    if (viaje.getClosingDate().before(new Date())) {
		throw new Exception(VIAJE_CERRADO);
	    }

	    else if (viaje.equals(TripStatus.CANCELLED)) {
		throw new Exception(VIAJE_CANCELADO);
	    }

	    else {
		Application solicitud = new Application();

		solicitud.setTripId(idTrip);
		solicitud.setUserId(idUser);

		Factories.persistence.newApplicationDao().save(solicitud);
	    }
	}

	catch (Exception excep) {
	    if (excep.getCause() != null
		    && excep.getCause() instanceof EntityExistsException) {

		throw new Exception(YA_SOLICITADO);
	    }

	    switch (excep.getMessage()) {

	    case VIAJE_INEXISTENTE:
		throw excep;

	    case VIAJE_CERRADO:
		throw excep;

	    case VIAJE_CANCELADO:
		throw excep;

	    default:
		throw new Exception(OTRA_CAUSA); // Otra causa
	    }
	}
    }
}