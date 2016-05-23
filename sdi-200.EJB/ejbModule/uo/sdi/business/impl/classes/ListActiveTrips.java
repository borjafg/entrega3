package uo.sdi.business.impl.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;

public class ListActiveTrips {
    private String ERROR = "Error al buscar la lista de viajes activos";

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
	// Lista de viajes sin filtrar
	List<Trip> listaViajes;

	// Lista de viaje activos
	List<Trip> listaFiltrada = new ArrayList<Trip>();

	// ==============================
	// (1) Obtener la lista de viajes
	// ==============================
	try {
	    if (user != null) {
		listaViajes = Factories.persistence.newTripDao()
			.findAllTrips_NoUser(user.getId());
	    }

	    else {
		listaViajes = Factories.persistence.newTripDao().findAll();
	    }

	    // ==============================
	    // (2) Filtrar la lista de viajes
	    //
	    // - No paso la fecha de cierre
	    // - El viaje esta abierto
	    // ==============================

	    for (Trip viaje : listaViajes) {
		if (viaje.getClosingDate().after(new Date())
			&& viaje.getStatus().equals(TripStatus.OPEN)) {

		    listaFiltrada.add(viaje);
		}
	    }
	}

	catch (Exception excep) {
	    throw new Exception(ERROR);
	}

	return listaFiltrada;
    }
}