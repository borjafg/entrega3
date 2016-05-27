package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

public class DesUsuario {
    private final String USUARIO_INVALIDO = "El usuario no es valido o ya esta "
	    + "cancelado";

    private final String ERROR = "Error al cancelar el viaje";

    public void desactivar(String login) throws Exception {
	try {
	    validar(login);

	    Factories.persistence.newUserDao().desactivate(login);
	}

	catch (Exception excep) {
	    switch (excep.getMessage()) {
	    case USUARIO_INVALIDO:
		throw excep;

	    default:
		throw new Exception(ERROR);
	    }
	}
    }

    private void validar(String login) throws Exception {
	if (login == null || login.equals("")) {
	    throw new Exception(USUARIO_INVALIDO);
	}

	User user = Factories.persistence.newUserDao().findByLogin(login);

	if (user == null || user.getStatus().equals(UserStatus.CANCELLED)) {
	    throw new Exception(USUARIO_INVALIDO);
	}
    }
}