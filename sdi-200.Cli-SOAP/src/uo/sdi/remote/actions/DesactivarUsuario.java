package uo.sdi.remote.actions;

import java.util.List;

import uo.sdi.ws.EjbUserServiceService;
import uo.sdi.ws.Exception_Exception;
import uo.sdi.ws.User;
import uo.sdi.ws.UserService;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DesactivarUsuario implements Action{

    @Override
    public void execute() throws Exception {
	Long id = Console.readLong("Id del usuario");
	UserService service = new EjbUserServiceService().getUserServicePort();
	User usuario = findUser(id);
	service.desUsuario(usuario.getLogin());
	System.out.println("usuario desactivado");
    }

    private User findUser(Long id) throws Exception_Exception {
	List<User> usuario = new EjbUserServiceService().getUserServicePort().getUsers();
	for (User user : usuario) {
	    if (user.getId().equals(id))
		return user;
	}
	return null;
	
    }

}
