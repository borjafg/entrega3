package uo.sdi.business.impl.classes;

import java.util.Date;

import javax.persistence.EntityExistsException;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;

public class ApplicateForTrip {
    public void applicate(Long idTrip, Long idUser) throws Exception {
	try {
	    Trip viaje = Factories.persistence.newTripDao().findById(idTrip);

	    if (viaje == null) {
		throw new Exception("Viaje inexistente");
	    }

	    if (viaje.getClosingDate().before(new Date())) {
		throw new Exception("Viaje cerrado");
	    }

	    else if (viaje.equals(TripStatus.CANCELLED)) {
		throw new Exception("Viaje cancelado");
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

		throw new Exception("Ya solicitado");
	    }

	    switch (excep.getMessage()) {

	    case "Viaje inexistente":
		throw excep;

	    case "Viaje cerrado":
		throw excep;

	    case "Viaje cancelado":
		throw excep;

	    default:
		throw new Exception(); // Otra causa
	    }
	}
    }
}