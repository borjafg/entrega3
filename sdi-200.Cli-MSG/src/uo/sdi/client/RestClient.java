package uo.sdi.client;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uo.sdi.client.rest.TripServicesRest;
import uo.sdi.client.rest.UsuarioServicesRest;
import uo.sdi.client.util.Authenticator;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class RestClient {
    private final String URL_REST = "http://localhost:8280/sdi-200Web/rest/";

    public List<Trip> listarViajesUsuario(User user) throws Exception {
	// ===================================================
	// (1) Preparar los datos del usuario (para conectarse
	// al servidor de aplicaciones y listar los viajes)
	// ===================================================

	String usuario = user.getLogin();
	String contraseña = user.getPassword();

	// ==============================
	// (2) Obtener la lista de viajes
	// ==============================

	TripServicesRest client = new ResteasyClientBuilder().build()
		.register(new Authenticator(usuario, contraseña))
		.target(URL_REST).proxy(TripServicesRest.class);

	// =======================
	// (3) Devolver los viajes
	// =======================

	return client.getViajesParticipaUsuario(user.getId());
    }

    public User login(String user, String password) throws Exception {
	
	UsuarioServicesRest client = new ResteasyClientBuilder().build()
		.register(new Authenticator(user, password)).target(URL_REST)
		.proxy(UsuarioServicesRest.class);

	return client.getUserByLogin(user);
    }
}