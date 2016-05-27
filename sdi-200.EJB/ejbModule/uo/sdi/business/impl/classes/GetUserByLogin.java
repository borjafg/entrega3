package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class GetUserByLogin {
    private final String USUARIO_INVALIDO = "No se ha indicado el usuario";
    private final String ERROR = "Error al buscar el usuario";

    public User getByLogin(String login) throws Exception {
	try {
	    validar(login);

	    User user = Factories.persistence.newUserDao().findByLogin(login);
	    return user;
	}

	catch (Exception excep) {
	    throw new Exception(ERROR);
	}
    }

    private void validar(String login) throws Exception {
	if (login == null || login.equals("")) {
	    throw new Exception(USUARIO_INVALIDO);
	}
    }

}