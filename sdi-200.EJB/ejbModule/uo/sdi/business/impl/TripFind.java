package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;

public class TripFind {

    public List<Trip> findAll() {
	return Factories.persistence.newTripDao().findAll();
    }

}
