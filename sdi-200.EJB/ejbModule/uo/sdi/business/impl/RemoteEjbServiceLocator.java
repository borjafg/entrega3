package uo.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.LoginService;
import uo.sdi.business.RatingService;
import uo.sdi.business.SeatService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripService;
import uo.sdi.business.UserService;

public class RemoteEjbServiceLocator implements ServicesFactory {
    private static final String APPLICATION_SERVICE_JNDI_KEY = "sdi200/"
	    + "sdi-200EJB/" + "EjbApplicationService!"
	    + "uo.sdi.business.impl.RemoteApplicationService";
    private static final String LOGIN_SERVICE_JNDI_KEY = "sdi200/"
	    + "sdi-200EJB/" + "EjbLoginService!"
	    + "uo.sdi.business.impl.RemoteLoginService";
    private static final String SEAT_SERVICE_JNDI_KEY = "sdi200/"
	    + "sdi-200EJB/" + "EjbSeatService!"
	    + "uo.sdi.business.impl.RemoteSeatService";
    private static final String TRIP_SERVICE_JNDI_KEY = "sdi200/"
	    + "sdi-200EJB/" + "EjbTripService!"
	    + "uo.sdi.business.impl.RemoteTripService";
    private static final String USER_SERVICE_JNDI_KEY = "sdi200/"
	    + "sdi-200EJB/" + "EjbUserService!"
	    + "uo.sdi.business.impl.RemoteUserService";
    private static final String RATING_SERVICE_JNDI_KEY = "sdi200/"
	    + "sdi-200EJB/" + "EjbRatingService!"
	    + "uo.sdi.business.impl.RemoteRatingService";

    @Override
    public LoginService getLoginService() {
	try {
	    Context ctx = new InitialContext();
	    return (LoginService) ctx.lookup(LOGIN_SERVICE_JNDI_KEY);
	} catch (Exception e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public UserService getUserService() {
	try {
	    Context ctx = new InitialContext();
	    return (UserService) ctx.lookup(USER_SERVICE_JNDI_KEY);
	} catch (Exception e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public TripService getTripService() {
	try {
	    Context ctx = new InitialContext();
	    return (TripService) ctx.lookup(TRIP_SERVICE_JNDI_KEY);
	} catch (Exception e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public SeatService getSeatService() {
	try {
	    Context ctx = new InitialContext();
	    return (SeatService) ctx.lookup(SEAT_SERVICE_JNDI_KEY);
	} catch (Exception e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public ApplicationService getApplicationService() {
	try {
	    Context ctx = new InitialContext();
	    return (ApplicationService) ctx
		    .lookup(APPLICATION_SERVICE_JNDI_KEY);
	} catch (Exception e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

    @Override
    public RatingService getRatingService() {
	try {
	    Context ctx = new InitialContext();
	    return (RatingService) ctx
		    .lookup(RATING_SERVICE_JNDI_KEY);
	} catch (Exception e) {
	    throw new RuntimeException("JNDI problem", e);
	}
    }

}
