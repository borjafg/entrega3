package com.sdi;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import com.sdi.client.Consumer;
import com.sdi.client.Producer;
import com.sdi.client.TripInfo;
import com.sdi.ui.Console;

/**
 * Es la clase que ejecuta la accion que indico el usuario. Otra clase es la que
 * se encarga de evaluar la entrada proporcionada por el usuario y llamar a uno
 * de los metodos de esta clase.<br/>
 * <br/>
 * El objetivo es separar parte de la capa de presentacion (entrada/salida) de
 * la logica (ejecutar la accion apropiada); sin embargo, esta clase todavía
 * forma parte de la capa de presentacion (ya que pide datos al usuario, y
 * delega en otras clases la ejecucion de la logica).
 * 
 */
public class ActionsProcessor {
    private String usuario;
    private TripInfo viaje = null;

    

    /**
     * Pide al usuario un usuario y una contraseña y las valida.
     * 
     * @return true si el usuario quiere terminar la ejecucion del programa, o
     *         false si consigue loguearse con exito
     * 
     * @throws JMSException
     *             ha ocurrido un error al inicializar el sistema de mensajeria
     * 
     */
    public boolean login() throws JMSException {
	String aux;
	boolean salir = false; // Desea terminar la ejecucion del programa
	boolean exito = false; // ¿Se logueo con exito?

	while (!exito && !salir) {
	    usuario = Console.readString("Nombre de usuario");
	    String contraseña = Console.readString("Nombre de usuario");

	    if (usuario != null && usuario != "" && contraseña != null
		    && contraseña != "") {

		// validar contra el servidor de aplicaciones
		// ------- ¿Como implementar este servicio? --------
	    }

	    if (!exito) {
		aux = Console.readString("¿Volver a intentarlo? (si/no)");

		if (aux.equals("si"))
		    salir = true;
	    }
	}

	return salir;
    }

    
}