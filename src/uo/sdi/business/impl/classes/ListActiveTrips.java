package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;

public class ListActiveTrips {
	/**
	 * Busca una lista de viajes activos.
	 * 
	 * @param user
	 *            usuario para filtrar la lista de viajes (buscar aquellos a los
	 *            que no haya solicitado asistir y en los que no sea el
	 *            promotor). Si es null entonces no se realizará un filtrado.
	 * 
	 * @return lista de viajes activos
	 * 
	 * @throws Exception
	 *             Ha ocurrido algun error al hacer la busqueda
	 * 
	 */
	public List<Trip> getTrips(User user) throws Exception {
		List<Trip> listaViajes;
		List<Trip> viajesBorrar = new ArrayList<Trip>();

		if (user != null) {
			listaViajes = Factories.persistence.newTripDao()
					.findAllTrips_NoUser(user.getId());
		}

		else {
			listaViajes = Factories.persistence.newTripDao().findAll();
		}

		// (1) Comprobar de que viajes paso la fecha cierre
		for (Trip viaje : listaViajes) {
			if (viaje.getClosingDate().before(new Date())
					|| viaje.getStatus().equals(TripStatus.CANCELLED)) {
				
				viajesBorrar.add(viaje);
			}
		}

		// (2) Borrar los viajes que no están activos
		for (Trip viaje : viajesBorrar) {
			listaViajes.remove(viaje);
		}

		return listaViajes;
	}
}