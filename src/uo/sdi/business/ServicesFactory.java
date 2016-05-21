package uo.sdi.business;

import uo.sdi.business.LoginService;


public interface ServicesFactory
{
	public LoginService createLoginService();

	public UserService createUserService();

	public TripService createTripService();

	public ApplicationService createApplicationService();
}