package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripFindById {

    public List<Trip> findById(Long id) {
	List<Trip> trips = Factories.persistence.newTripDao().findByPromoterId(
		id);
	return trips;
    }

}
