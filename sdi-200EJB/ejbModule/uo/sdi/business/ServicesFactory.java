package uo.sdi.business;

import uo.sdi.business.LoginService;


public interface ServicesFactory
{
	public LoginService getLoginService();

	public UserService getUserService();

	public TripService getTripService();

	public ApplicationService getApplicationService();

	SeatService getSeatService();
	
	RatingService getRatingService();
}