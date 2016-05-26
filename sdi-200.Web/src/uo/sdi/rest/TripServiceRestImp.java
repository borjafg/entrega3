package uo.sdi.rest;

import java.util.List;

import uo.sdi.business.TripService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripServiceRestImp implements TripServicesRest {
    TripService service = Factories.services.getTripService();

    @Override
    public List<Trip> getViajes() {
	return service.findAll();
    }

    @Override
    public List<Trip> getViajesUsuario(Long id) throws Exception {
	return service.getPromoterTrips(id);
    }

    @Override
    public List<Trip> getViajesParticipaUsuario(Long id) {
	return service.findTripsUser(id);
    }
}