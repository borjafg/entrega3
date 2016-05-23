package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class GetInfoTrip {
    public final String ERROR = "Ha habido un error al buscar la informacion"
	    + "del viaje";

    public Trip getInfo(Long id) throws Exception {
	Trip viaje = null;

	try {
	    viaje = Factories.persistence.newTripDao().findById(id);
	}

	catch (Exception excep) {
	    throw new Exception(ERROR);
	}

	return viaje;
    }
}