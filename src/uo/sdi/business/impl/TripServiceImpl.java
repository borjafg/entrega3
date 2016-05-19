package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.TripService;
import uo.sdi.business.impl.classes.ListActiveTrips;
import uo.sdi.business.impl.classes.SaveTrip;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class TripServiceImpl implements TripService {
	@Override
	public List<Trip> getListActiveTrips(User user) throws Exception {
		return new ListActiveTrips().getTrips(user);
	}

	@Override
	public void save(Trip viaje) throws Exception {
		new SaveTrip().save(viaje);
	}
}