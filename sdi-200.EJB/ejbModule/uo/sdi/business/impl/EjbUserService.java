package uo.sdi.business.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.impl.classes.DesUsuario;
import uo.sdi.business.impl.classes.FindAll_Users;
import uo.sdi.business.impl.classes.FindPassengers;
import uo.sdi.business.impl.classes.GetInfoUser;
import uo.sdi.business.impl.classes.GetUserByLogin;
import uo.sdi.business.impl.classes.SaveUser;
import uo.sdi.model.User;

@Stateless
@WebService(name = "UserService")
public class EjbUserService implements LocalUserService, RemoteUserService {
    @Override
    public void save(User user) throws Exception {
	new SaveUser().save(user);
    }

    @Override
    public List<User> findPassengers(Long idTrip) throws Exception {
	return new FindPassengers().find(idTrip);
    }

    @Override
    public List<User> getUsers() throws Exception {
	return new FindAll_Users().findAll();
    }

    @Override
    public Map<String, Object> getInfoUser(Long id) throws Exception {
	return new GetInfoUser().getInfo(id);
    }

    @Override
    public void desUsuario(String login) throws Exception {
	new DesUsuario().desactivar(login);
	
    }

    @Override
    public User getUserByLogin(String login) throws Exception {
	return new GetUserByLogin().getByLogin(login);
    }
}