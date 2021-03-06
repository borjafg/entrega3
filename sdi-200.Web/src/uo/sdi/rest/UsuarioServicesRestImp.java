package uo.sdi.rest;

import java.util.List;

import uo.sdi.business.UserService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

public class UsuarioServicesRestImp implements UsuarioServicesRest {
    UserService service = Factories.services.getUserService();

    @Override
    public List<User> getUsuarios() throws Exception {
	return service.getUsers();
    }


    @Override
    public User getUserByLogin(String login) throws Exception {
	return service.getUserByLogin(login);
    }

}
