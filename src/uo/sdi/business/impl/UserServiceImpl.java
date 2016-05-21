package uo.sdi.business.impl;

import java.util.List;
import java.util.Map;

import uo.sdi.business.UserService;
import uo.sdi.business.impl.classes.FindPassengers;
import uo.sdi.business.impl.classes.GetInfoUser;
import uo.sdi.business.impl.classes.SaveUser;
import uo.sdi.model.User;

public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) throws Exception {
	new SaveUser().save(user);
    }

    @Override
    public List<User> findPassengers(Long idTrip) throws Exception {
	return new FindPassengers().find(idTrip);
    }

    @Override
    public Map<String, Object> getInfoUser(Long id) throws Exception {
	return new GetInfoUser().getInfo(id);
    }
}