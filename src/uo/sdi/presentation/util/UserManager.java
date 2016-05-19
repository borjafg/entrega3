package uo.sdi.presentation.util;

import java.util.Map;

import uo.sdi.model.User;
import alb.util.log.Log;

public class UserManager {
	/**
	 * Comprueba si el usuario que solicito ver el listado de viajes está
	 * loguado en el sistema
	 * 
	 * @param sesion para comprobar si el usuario esta registrado
	 * @return información del usuario (objeto de la clase User)
	 * 
	 */
	public static User comprobarUsuarioRegistrado(Map<String, Object> sesion) {
		Object user = sesion.get("user");

		if (user == null) {
			Log.debug("El usuario no esta logueado o la sesion expiro");
			return null;
		}

		return (User) user;
	}
}