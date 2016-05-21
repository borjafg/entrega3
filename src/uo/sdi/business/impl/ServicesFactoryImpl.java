package uo.sdi.business.impl;

import uo.sdi.business.ApplicationService;
import uo.sdi.business.LoginService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripService;
import uo.sdi.business.UserService;
import uo.sdi.business.impl.LoginServiceImpl;


public class ServicesFactoryImpl implements ServicesFactory
{
	@Override
	public LoginService createLoginService()
	{
		return new LoginServiceImpl();
	}

	@Override
	public UserService createUserService()
	{
		return new UserServiceImpl();
	}

	@Override
	public TripService createTripService() {
		return new TripServiceImpl();
	}  

	@Override
	public ApplicationService createApplicationService() {
		return new ApplicationServiceImpl();
	}
}