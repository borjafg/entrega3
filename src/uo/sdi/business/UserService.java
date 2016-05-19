package uo.sdi.business;

import java.util.List;

import uo.sdi.model.User;


public interface UserService {
	
	public void save(User user) throws Exception;

	public List<User> findPassengers(Long idTrip) throws Exception;
}