package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class SaveUser {
    public void save(User user) throws Exception {
	if (Factories.persistence.newUserDao().findByLogin(user.getLogin()) != null) {
	    throw new Exception("This login is already in use");
	}

	else {
	    try {
		Factories.persistence.newUserDao().save(user);
	    }

	    catch (Exception excep) {
		throw new Exception("Error al guardar los datos del usuario");
	    }
	}
    }
}