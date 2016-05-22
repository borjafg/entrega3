package uo.sdi.business.impl.classes;

import uo.sdi.infrastructure.Factories;

public class DesUsuario {

    public void desactivar(String login) {
	Factories.persistence.newUserDao().desactivate(login);
    }

}
