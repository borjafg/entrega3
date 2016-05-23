package uo.sdi.remote.actions;

import uo.sdi.ws.EjbRatingServiceService;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BorrarCalificaciones implements Action {

    @Override
    public void execute() throws Exception {
	Long id = Console.readLong("Id del mensaje");
	new EjbRatingServiceService().getRatingServicePort().deleteRating(id);
	System.out.println("Calificacion borrada");
    }

}
