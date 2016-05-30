package uo.sdi.remote.actions;

import java.util.List;

import uo.sdi.ws.EjbSeatServiceService;
import uo.sdi.ws.EjbTripServiceService;
import uo.sdi.ws.EjbUserServiceService;
import uo.sdi.ws.Exception_Exception;
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

    private List<User> findAllUsers() throws Exception_Exception {
	UserService service = new EjbUserServiceService().getUserServicePort();
	return service.getUsers();
    }

    private List<Trip> listadoViajesPromovidos(Long id) throws Exception_Exception {
	TripService ts = new EjbTripServiceService().getTripServicePort();
	return ts.getPromoterTrips(id);
    }

    private List<Seat> listSeatsUser(Long id) throws Exception_Exception {
	SeatService ss = new EjbSeatServiceService().getSeatServicePort();
	return ss.findByUserId(id);
    }

    private void printLine(User u) throws Exception_Exception {
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

    private int numeroViajesParticipo(Long id) throws Exception_Exception {
	return listSeatsUser(id).size();
    }

    private int numeroViajesPromovidos(Long id) throws Exception_Exception {
	return listadoViajesPromovidos(id).size();
    }

}
