package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class SaveUser {
    private final String LOGIN_EXIST = "Este login ya existe";
    private final String ERROR = "Error al guardar los datos del usuario";

    public void save(User user) throws Exception {
	if (Factories.persistence.newUserDao().findByLogin(user.getLogin()) != null) {
	    throw new Exception(LOGIN_EXIST);
	}

	else {
	    try {
		Factories.persistence.newUserDao().save(user);
	    }

	    catch (Exception excep) {
		throw new Exception(ERROR);
	    }
	}
    }
}