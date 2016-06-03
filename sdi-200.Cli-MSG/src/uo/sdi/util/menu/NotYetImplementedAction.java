package uo.sdi.util.menu;

import uo.sdi.client.MessagingClient;

public class NotYetImplementedAction implements Action {

    @Override
    public void execute(MessagingClient cliente) throws Exception {
	System.err.println("Opción no implementada");
    }
}