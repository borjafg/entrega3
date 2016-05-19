package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.User;


public interface TripService {

	public List<Trip> getListActiveTrips(User user) throws Exception;

	public void save(Trip viaje) throws Exception;
}