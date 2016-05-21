package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class ListPromoterTrips {
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String ERROR = "Error al buscar los viajes";

    public List<Trip> getTrips(User user) throws Exception {
	List<Trip> listaViajes = new ArrayList<Trip>();

	// (1) Obtener los datos actualizados del usuario
	User usuario = Factories.persistence.newUserDao()
		.findById(user.getId());

	// (2) Comprobar que el usuario es valido
	if (usuario != null && usuario.getStatus().equals(UserStatus.ACTIVE)) {

	    // (3) Realizar la busqueda
	    try {
		listaViajes = Factories.persistence.newTripDao()
			.findByPromoterId(usuario.getId());

		return listaViajes;
	    }

	    catch (Exception excep) {
		throw new Exception(ERROR);
	    }
	}

	else {
	    throw new Exception(USUARIO_INVALIDO);
	}
    }
}