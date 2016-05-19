package uo.sdi.business.impl;

import uo.sdi.business.LoginService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class LoginServiceImpl implements LoginService {
	@Override
	public User verify(String login, String password) throws Exception
	{
		User user = null;
		
		try
		{
			user = Factories.persistence.newUserDao().findByLogin(login);
		}
		
		catch(Exception excep) { throw new Exception(); }
		
		if (!validLogin(user, password))
			return null;
		
		return user;
	}

	private boolean validLogin(User user, String password) {
		if (user == null) {
			return false;
		}

		return user.getPassword().equals(password)
				&& user.getStatus().equals(UserStatus.ACTIVE);
	}
}