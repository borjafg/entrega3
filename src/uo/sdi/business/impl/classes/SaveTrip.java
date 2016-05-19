package uo.sdi.business.impl.classes;

import javax.persistence.EntityExistsException;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class SaveTrip {
	public void save(Trip trip) throws Exception {
		try {
			Factories.persistence.newTripDao().save(trip);
		}
		
		catch(EntityExistsException excep) {
			throw new Exception("Viaje ya existe");
		}
		
		catch(Exception excep) {
			throw new Exception();
		}
	}
}