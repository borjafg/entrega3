package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class ListApplicationsPromoter {
    private final String USUARIO_INVALIDO = "El usuario no es valido";
    private final String ERROR = "No se ha podido obtener la lista de solicitudes";

    public List<Application> list(User user) throws Exception {
	List<Application> listaSolicitudes = new ArrayList<Application>();

	try {
	    validar(user);

	    List<Trip> listaViajesPromotor = Factories.persistence.newTripDao()
		    .findByPromoterId(user.getId());

	    List<Application> aux;

	    for (Trip viaje : listaViajesPromotor) {
		aux = Factories.persistence.newApplicationDao()
			.findByTripId(viaje.getId());
		
		for(Application solicitud : aux) {
		    listaSolicitudes.add(solicitud);
		}
	    }
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;

	    default:
		throw new Exception(ERROR);
	    }
	}

	return listaSolicitudes;
    }

    private void validar(User user) throws Exception {
	if (user == null) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	User usuario = Factories.persistence.newUserDao()
		.findById(user.getId());

	if (usuario == null || usuario.getStatus().equals(UserStatus.CANCELLED)) {
	    throw new Exception(USUARIO_INVALIDO);
	}
    }
}