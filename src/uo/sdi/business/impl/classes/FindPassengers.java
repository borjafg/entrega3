package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.User;

public class FindPassengers {
	public List<User> find(Long idTrip) throws Exception {
		try {
			List<Seat> asistencias = Factories.persistence.newSeatDao()
					.findByTrip(idTrip);

			List<User> pasajeros = new ArrayList<User>();

			for (Seat asistencia : asistencias) {
				if (asistencia.getStatus().equals(SeatStatus.ACCEPTED)) {
					pasajeros.add(Factories.persistence.newUserDao().findById(
							asistencia.getUserId()));
				}
			}

			Long idPromotor = Factories.persistence.newTripDao()
					.findById(idTrip).getPromoterId();

			pasajeros.add(Factories.persistence.newUserDao().findById(
					idPromotor));

			return pasajeros;
		}

		catch (Exception excep) {
			throw new Exception();
		}
	}
}