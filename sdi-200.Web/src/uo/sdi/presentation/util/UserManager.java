package uo.sdi.presentation.util;

import java.util.Map;

import javax.faces.context.FacesContext;

import uo.sdi.presentation.BeanUser;

public class UserManager {
	public BeanUser crearBeanUser() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();

		BeanUser user = (BeanUser) session.get("user");
		
		if (user == null) {
			user = new BeanUser();
			session.put("user", user);
		}
		
		return user;
	}
}