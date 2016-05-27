package uo.sdi.business.impl.classes;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class FindAll_Users {
    private final String ERROR = "Error al buscar las asistencias del usuario";
    
    public List<User> findAll() throws Exception {
	try {
	    List<User> usuarios = Factories.persistence.newUserDao().findAll();

	    return usuarios;
	}
	
	catch(Exception excep) {
	    throw new Exception(ERROR);
	}
    }
    
}