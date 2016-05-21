package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class ListPromoterTrips {
    public List<Trip> getTrips(User user) throws Exception {
	// Lista de viajes
	List<Trip> listaViajes = new ArrayList<Trip>();

	// (1) Comprobar que el usuario es valido
	if (user != null && user.getStatus().equals(UserStatus.ACTIVE)) {

	    // (2) Realizar la busqueda
	    try {
		listaViajes = Factories.persistence.newTripDao()
			.findByPromoterId(user.getId());
		
		return listaViajes;
	    }

	    catch (Exception excep) {
		throw new Exception("Error al buscar los viajes");
	    }
	}

	else {
	    throw new Exception("Usuario invalido");
	}
    }
}