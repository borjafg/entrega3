package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;

public class DesUsuario {

    public void desactivar(String login) throws Exception {
	try {
	    Factories.persistence.newUserDao().desactivate(login);
	}

	catch (Exception excep) {
	    g
	}
    }
}