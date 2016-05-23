package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class SaveTrip {
    private final String ERROR = "Error al guardar el viaje";
    
    public void save(Trip trip) throws Exception {
	try {
	    Factories.persistence.newTripDao().save(trip);
	}

	catch (Exception excep) {
	    throw new Exception(ERROR);
	}
    }
}