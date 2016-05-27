package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.CancelTrip;
import uo.sdi.business.impl.classes.FindTripsUser;
import uo.sdi.business.impl.classes.GetInfoTrip;
import uo.sdi.business.impl.classes.ListActiveTrips;
import uo.sdi.business.impl.classes.ListPromoterTrips;
import uo.sdi.business.impl.classes.SaveTrip;
import uo.sdi.business.impl.classes.FindAll_Trips;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

@Stateless
@WebService(name = "TripService")
public class EjbTripService implements LocalTripService, RemoteTripService {
    @Override
    public List<Trip> getListActiveTrips(User user) throws Exception {
	return new ListActiveTrips().getTrips(user);
    }

    @Override
    public void save(Trip viaje) throws Exception {
	new SaveTrip().save(viaje);
    }

    @Override
    public List<Trip> getPromoterTrips(Long idUser) throws Exception {
	return new ListPromoterTrips().getTrips(idUser);
    }

    @Override
    public void cancelTrip(Trip trip, User user) throws Exception {
	new CancelTrip().cancel(trip, user);
    }

    @Override
    public Trip getInfoTrip(Long id) throws Exception {
	return new GetInfoTrip().getInfo(id);
    }

    /* ====================== */
    /* ======== REST ======== */
    /* ====================== */

    @Override
    public List<Trip> findAll() throws Exception {
	return new FindAll_Trips().findAll();
    }

    @Override
    public List<Trip> findTripsUser(Long id) throws Exception {
	return new FindTripsUser().findTrips(id);
    }
}