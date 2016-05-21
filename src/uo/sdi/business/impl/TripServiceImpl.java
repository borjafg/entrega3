package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.TripService;
import uo.sdi.business.impl.classes.CancelTrip;
import uo.sdi.business.impl.classes.GetInfoTrip;
import uo.sdi.business.impl.classes.ListActiveTrips;
import uo.sdi.business.impl.classes.ListPromoterTrips;
import uo.sdi.business.impl.classes.SaveTrip;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class TripServiceImpl implements TripService {
    @Override
    public List<Trip> getListActiveTrips(User user) throws Exception {
	return new ListActiveTrips().getTrips(user);
    }

    @Override
    public List<Trip> getPromoterTrips(User user) throws Exception {
	return new ListPromoterTrips().getTrips(user);
    }

    @Override
    public void save(Trip trip) throws Exception {
	new SaveTrip().save(trip);
    }

    @Override
    public void cancelTrip(Trip trip, User user) throws Exception {
	new CancelTrip().cancel(trip, user);
    }

    @Override
    public Trip getInfoTrip(Long id) throws Exception {
	return new GetInfoTrip().getInfo(id);
    }
}