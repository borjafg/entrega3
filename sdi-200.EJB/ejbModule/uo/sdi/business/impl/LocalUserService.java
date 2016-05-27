package uo.sdi.business.impl;

import java.util.List;

import javax.ejb.Local;

import uo.sdi.business.UserService;
import uo.sdi.model.User;

@Local
public interface LocalUserService extends UserService {

    public List<User> getUsers() throws Exception;
}