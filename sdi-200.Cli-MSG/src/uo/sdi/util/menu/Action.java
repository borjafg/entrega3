package uo.sdi.util.menu;

import uo.sdi.client.MessagingClient;

/**
 * Representa cada acción invocada por el usuario.
 */
public interface Action {
    public void execute(MessagingClient client) throws Exception;
}