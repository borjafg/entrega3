package uo.sdi.remote.actions;

import uo.sdi.business.impl.RemoteEjbServiceLocator;
import alb.util.console.Console;
import alb.util.menu.Action;

public class BorrarCalificaciones implements Action {

    @Override
    public void execute() throws Exception {
	Long id = Console.readLong("Id del mensaje");
	new RemoteEjbServiceLocator().getRatingService().deleteRating(id);
	System.out.println("Calificacion borrada");
    }

}
