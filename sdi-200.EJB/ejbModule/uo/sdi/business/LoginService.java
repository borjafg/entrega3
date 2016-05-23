package uo.sdi.business;

import uo.sdi.model.User;


public interface LoginService
{
	public User verify(String login, String password) throws Exception;
}