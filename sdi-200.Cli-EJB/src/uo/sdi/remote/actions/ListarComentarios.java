package uo.sdi.remote.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.sdi.business.RatingService;
import uo.sdi.business.impl.RemoteEjbServiceLocator;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import alb.util.menu.Action;

public class ListarComentarios implements Action {

    @Override
    public void execute() throws Exception {
	RatingService service = new RemoteEjbServiceLocator()
		.getRatingService();
	List<Rating> ratings = service.listAllRating();

	List<Trip> trips = new RemoteEjbServiceLocator().getTripService()
		.findAll();
	List<Trip> tripsLastMonth = filtradoUltimoMes(trips);
	List<Rating> ratingLastMonth = getComments(tripsLastMonth, ratings);
	print(ratingLastMonth);
    }

    private void print(List<Rating> ratingLastMonth) {
	for (Rating rating : ratingLastMonth) {
	    System.out.println(rating.getId());
	    System.out.println(rating.getComment());
	    System.out.println(rating.getSeatAboutTripId());
	    System.out.println(rating.getSeatAboutUserId());
	    System.out.println(rating.getSeatFromTripId());
	}
	
    }

    private List<Rating> getComments(List<Trip> tripsLastMonth,
	    List<Rating> comentarios) {
	List<Rating> ratingLastMonth = new ArrayList<>();
	for (Trip trip : tripsLastMonth) {
	    for (Rating rating : comentarios) {
		if(rating.getSeatFromTripId().equals(trip.getId()))
		    ratingLastMonth.add(rating);
	    }
	}
	return ratingLastMonth;
    }

    @SuppressWarnings("deprecation")
    private List<Trip> filtradoUltimoMes(List<Trip> trips) {
	List<Trip> filtrado = new ArrayList<>();
	Date hoy = new Date();

	for (Trip trip : trips) {
	    if ((trip.getClosingDate().getMonth() - hoy.getMonth()) == -1
		    && trip.getClosingDate().getYear() == hoy.getYear()) {
		filtrado.add(trip);
		System.out.println(trip.getClosingDate());
	    }
	}
	return filtrado;

    }

}
