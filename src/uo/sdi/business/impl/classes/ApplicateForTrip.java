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
			
			if (viaje.getClosingDate().before(new Date())) {
				throw new Exception("Viaje cerrado");
			}
			
			else if(viaje.equals(TripStatus.CANCELLED)) {
				throw new Exception("Viaje cancelado");
			}
			
			else {
				Application solicitud = new Application();
				
				solicitud.setTripId(idTrip);
				solicitud.setUserId(idUser);
				
				Factories.persistence.newApplicationDao().save(solicitud);
			}
		}
		
		catch(EntityExistsException excep) {
			throw new Exception("Ya solicitado");
		}
		
		catch(Exception excep) {
			throw new Exception();
		}
	}
}