package uo.sdi.acciones;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.util.Authenticator;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ConfirmarPasajeros implements Action {
    private TripServicesRest client;
    private UsuarioServicesRest clientUser;
    private ApplicationServicesRest clientApp;

    @Override
    public void execute() throws Exception {
	String user = Console.readString("Usuario");
	String pass = Console.readString("Password");
	client = new ResteasyClientBuilder().build()
		.register(new Authenticator(user, pass))
		.target("http://localhost:8280/sdi-200Web/rest/")
		.proxy(TripServicesRest.class);
	clientUser = new ResteasyClientBuilder().build()
		.register(new Authenticator(user, pass))
		.target("http://localhost:8280/sdi-200Web/rest/")
		.proxy(UsuarioServicesRest.class);
	clientApp = new ResteasyClientBuilder().build()
		.register(new Authenticator(user, pass))
		.target("http://localhost:8280/sdi-200Web/rest/")
		.proxy(ApplicationServicesRest.class);
	List<Trip> trips = client.getViajes();
	User usuario = findUserByLogin(user);
	List<Trip> tripsPromoted = listViajesPromovidos(trips, usuario.getId());
	printUser(usuario);
	printTrip(tripsPromoted);
	Long idViaje = Console
		.readLong("Inserte id viaje para confirmar pasajeros");
	for (Trip trip : tripsPromoted) {
	    if(trip.getId().equals(idViaje))
		System.out.println();
	}
//	confirmarPasajero(usuario.getId(), idViaje);

    }

    private void confirmarPasajero(Long idUser, Long idViaje, Trip trip) {

    }

    private void printUser(User usuario) {
	StringBuilder sb = new StringBuilder();
	sb.append(usuario.getLogin());
	System.out.println(sb.toString());

    }

    private void printTrip(List<Trip> trips) {
	for (Trip trip : trips) {
	    printTrip(trip);
	}
    }

    private User findUserByLogin(String login) {
	return clientUser.getUserByLogin(login);
    }

    private List<Trip> listViajesPromovidos(List<Trip> viajes, Long idUsuario) {
	List<Trip> viajesPromovidos = new ArrayList<>();
	for (Trip trip : viajes) {
	    if (trip.getPromoterId().equals(idUsuario)
		    && (trip.getStatus().equals(TripStatus.OPEN)))
		viajesPromovidos.add(trip);
	}
	return viajesPromovidos;
    }

    private void printTrip(Trip t) {
	StringBuilder sb = new StringBuilder();
	sb.append("Id:" + t.getId());
	sb.append(" Fecha de llegada:" + t.getArrivalDate());
	sb.append(" Origen:" + t.getDeparture());
	sb.append(" Comentarios: " + t.getComments());
	System.out.println(sb.toString());

    }

}
