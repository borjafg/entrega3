package uo.sdi.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import uo.sdi.model.User;

/**
 * Almacena los datos de la cuenta con la que se registro el usuario.
 * 
 */
@ManagedBean(name = "user")
@SessionScoped
public class BeanUser {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Comprueba si el usuario esta logueado
	 * 
	 * @return true si se logueo, o false en caso contrario
	 * 
	 */
	public boolean isUserLoggedIn() {
		return user != null;
	}
}