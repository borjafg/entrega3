package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class FindTripsUser {
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String ERROR = "Ha ocurrido un error al obtener la lista de "
	    + "viajes";

    public List<Trip> findTrips(Long id) throws Exception {
	try {
	    validar(id);

	    // ==============================
	    // (1) Obtener la lista de viajes
	    // ==============================

	    List<Trip> viajes = Factories.persistence.newTripDao()
		    .findAllTrips_User(id);

	    // ==================================
	    // (2) Eliminar los viajes cancelados
	    // ==================================

	    List<Trip> viajesFiltrados = new ArrayList<Trip>();

	    for (Trip trip : viajes) {
		if (!trip.getStatus().equals(TripStatus.CANCELLED)) {
		    viajesFiltrados.add(trip);
		}
	    }

	    // =========================================
	    // (3) Devolver la lista de viajes filtrados
	    // =========================================

	    return viajesFiltrados;
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;

	    default:
		throw new Exception(ERROR);
	    }
	}
    }

    private void validar(Long idUsuario) throws Exception {
	if (idUsuario == null) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	User user = Factories.persistence.newUserDao().findById(idUsuario);

	if (user == null || user.getStatus().equals(UserStatus.CANCELLED)) {
	    throw new Exception(USUARIO_INVALIDO);
	}
    }
}