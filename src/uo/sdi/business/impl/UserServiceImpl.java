package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.UserService;
import uo.sdi.business.impl.classes.FindPassengers;
import uo.sdi.business.impl.classes.SaveUser;
import uo.sdi.model.User;


public class UserServiceImpl implements UserService
{
	@Override
	public void save(User user) throws Exception {
		new SaveUser().save(user);
	}

	@Override
	public List<User> findPassengers(Long idTrip) throws Exception {
		return new FindPassengers().find(idTrip);
	}
}