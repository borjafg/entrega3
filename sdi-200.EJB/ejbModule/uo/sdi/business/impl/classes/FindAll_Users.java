package uo.sdi.business.impl.classes;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class FindAll_Users {

    public List<User> findAll() {
	List<User> usuarios = Factories.persistence.newUserDao().findAll();
	return usuarios;
    }

}
