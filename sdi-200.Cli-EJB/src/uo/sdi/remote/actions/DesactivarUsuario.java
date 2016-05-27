package uo.sdi.remote.actions;

import java.util.List;

import uo.sdi.business.UserService;
import uo.sdi.business.impl.RemoteEjbServiceLocator;
import uo.sdi.model.User;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DesactivarUsuario implements Action {

    @Override
    public void execute() throws Exception {
	Long id = Console.readLong("Id del usuario");

	UserService service = new RemoteEjbServiceLocator().getUserService();
	User usuario = findUser(id);
	service.desUsuario(usuario.getLogin());

	System.out.println("usuario desactivado");
    }

    private User findUser(Long id) throws Exception {
	List<User> usuario = new RemoteEjbServiceLocator().getUserService()
		.getUsers();

	for (User user : usuario) {
	    if (user.getId().equals(id))
		return user;
	}

	return null;
    }

}