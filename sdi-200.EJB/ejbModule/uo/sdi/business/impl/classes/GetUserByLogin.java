package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class GetUserByLogin {

    public User getByLogin(String login) {
	User user = Factories.persistence.newUserDao().findByLogin(login);
	return user;
    }

}
