package uo.sdi.remote.actions;

import java.util.List;

import uo.sdi.ws.EjbSeatServiceService;
import uo.sdi.ws.EjbTripServiceService;
import uo.sdi.ws.EjbUserServiceService;
import uo.sdi.ws.Seat;
import uo.sdi.ws.SeatService;
import uo.sdi.ws.Trip;
import uo.sdi.ws.TripService;
import uo.sdi.ws.User;
import uo.sdi.ws.UserService;
import alb.util.menu.Action;

public class GetUsuariosAction implements Action {

    @Override
    public void execute() throws Exception {
	printHeader();
	List<User> usuarios = findAllUsers();
	for (User user : usuarios) {
	    printLine(user);
	}
    }

    private void printHeader() {
	System.out.println("Lista usuarios:");
    }

    private List<User> findAllUsers() {
	UserService service = new EjbUserServiceService().getUserServicePort();
	return service.getUsers();
    }

    private List<Trip> listadoViajes(Long id) {
	TripService ts = new EjbTripServiceService().getTripServicePort();
	return ts.findById(id);
    }

    private List<Seat> listSeatsUser(Long id) {
	SeatService ss = new EjbSeatServiceService().getSeatServicePort();
	return ss.findById(id);
    }

    private void printLine(User u) {
	StringBuilder sb = new StringBuilder();
	sb.append(" Nombre: " + u.getName());
	sb.append(" Apellido: " + u.getSurname());
	sb.append(" Email: " + u.getEmail());
	sb.append(" Login:" + u.getLogin());
	sb.append(" Status: " + u.getStatus());
	sb.append(" Numero viajes promovidos: " + numeroViajesPromovidos(u.getId()));
	sb.append(" Numero viajes participo: " + numeroViajesParticipo(u.getId()));
	System.out.println(sb.toString());

    }

    private int numeroViajesParticipo(Long id) {
	return listSeatsUser(id).size();
    }

    private int numeroViajesPromovidos(Long id) {
	return listadoViajes(id).size();
    }

}
