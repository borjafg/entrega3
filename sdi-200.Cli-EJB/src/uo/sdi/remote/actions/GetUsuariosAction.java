package uo.sdi.remote.actions;

import java.util.List;

import uo.sdi.business.SeatService;
import uo.sdi.business.TripService;
import uo.sdi.business.UserService;
import uo.sdi.business.impl.RemoteEjbServiceLocator;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
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

    private List<User> findAllUsers() throws Exception {
	UserService service = new RemoteEjbServiceLocator().getUserService();
	
	return service.getUsers();
    }

    private List<Trip> listadoViajes(Long id) throws Exception {
	TripService ts = new RemoteEjbServiceLocator().getTripService();
	
	return ts.getPromoterTrips(id);
    }

    private List<Seat> listSeatsUser(Long id) throws Exception {
	SeatService ss = new RemoteEjbServiceLocator().getSeatService();
	
	return ss.findBy_userId(id);
    }

    private void printLine(User u) throws Exception {
	StringBuilder sb = new StringBuilder();

	sb.append(" Nombre: " + u.getName());
	sb.append(" Apellido: " + u.getSurname());
	sb.append(" Email: " + u.getEmail());
	sb.append(" Login:" + u.getLogin());
	sb.append(" Status: " + u.getStatus());
	
	sb.append(" Numero viajes promovidos: "
		+ numeroViajesPromovidos(u.getId()));
	
	sb.append(" Numero viajes participo: "
		+ numeroViajesParticipo(u.getId()));

	System.out.println(sb.toString());

    }

    private int numeroViajesParticipo(Long id) throws Exception {
	return listSeatsUser(id).size();
    }

    private int numeroViajesPromovidos(Long id) throws Exception {
	return listadoViajes(id).size();
    }
}