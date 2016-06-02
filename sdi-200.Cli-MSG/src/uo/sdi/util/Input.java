package uo.sdi.util;

import java.util.List;

import uo.sdi.client.RestClient;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import alb.util.console.Console;

/**
 * Sirve para pedir datos al usuario, como por ejemplo: un viaje, un string...
 * 
 */
public class Input {
    /**
     * Pide al usuario que escriba una cadena de texto no vacia.
     * 
     * @param mensaje
     *            Mensaje que se quiere mostrar al usuario para pedirle algun
     *            dato
     * 
     * @return texto escrito por el usuario (la cadena "salir" sirve para que el
     *         usuario indique que quiere salir de la aplicacion)
     * 
     */
    public static String pedirString(String mensaje) {
	String cadena = null;

	while (cadena == null || cadena.equals("")) {

	    cadena = Console.readString(mensaje);
	}

	return cadena;
    }

    /**
     * Pide al usuario que escriba un numero.
     * 
     * @param mensaje
     *            Mensaje que se quiere mostrar al usuario para pedirle algun
     *            dato
     * 
     * @return valor que escribio el usuario
     * 
     */
    public static Long pedirLong(String mensaje) {
	Long numero = null;

	while (numero == null) {

	    numero = Console.readLong(mensaje);
	}

	return numero;
    }

    /**
     * Le pide al usuario su usuario y su contraseña y comprueba que sean
     * validas.
     * 
     * @return Datos del usuario
     * 
     * @throws Exception
     *             Ha ocurrido un error, o el usuario no desea loguearse
     *             (mensaje de error = "salir")
     * 
     */
    public static User login() throws Exception {
	RestClient cliente = new RestClient();

	String usuario;
	String contraseña;

	User user = null;

	while (user == null) {
	    usuario = Input
		    .pedirString("Usuario (Si desea salir escriba \"salir\")");
	    contraseña = Input
		    .pedirString("Contraseña (Si desea salir escriba \"salir\")");

	    if (usuario.equals("salir") || contraseña.equals("salir")) {
		throw new Exception("salir");
	    }

	    user = cliente.login(usuario, contraseña);

	    if (user != null) {
		if (user.getStatus().equals(UserStatus.CANCELLED)) {
		    user = null; // Cuenta cancelada (No es valida)
		    Console.println("Usuario o contraseña incorrectos");
		}

		else if (!user.getPassword().equals(contraseña)) {
		    user = null;
		    Console.println("Usuario o contraseña incorrectos");
		}
	    }
	} // while end

	return user;
    }

    /**
     * Muestra los viajes de un usuario y
     * 
     * @param user
     *            usuario que quiere elegir el viaje
     * @return viaje elegido
     * 
     * @throws Exception
     *             Posibles casos: <br/>
     *             - Mensaje de error: "salir" (El usuario no desea elegir
     *             ningún viaje) <br/>
     *             - Mensaje de error: "El usuario no participa en ningun viaje" <br/>
     *             - Cualquier otro mensaje (Ha ocurrido un error)
     * 
     */
    public static Trip elegirViaje(User user) throws Exception {
	// ==============================
	// Obtener los viajes del usuario
	// ==============================

	List<Trip> listaViajes = new RestClient().listarViajesUsuario(user);

	if (listaViajes.size() <= 0) {
	    throw new Exception("El usuario no participa en ningun viaje");
	}

	Console.println("Lista de viajes (usuario: " + user.getLogin() + ")");

	for (Trip viaje : listaViajes) {
	    Console.println(ShowTrip.show(viaje));
	}

	Console.println("============================");

	// ================================
	// Pedirle al usuario que elija uno
	// ================================

	Trip viaje = null;
	Long id_viaje = null;

	while (viaje == null) {
	    id_viaje = Input
		    .pedirLong("Id del viaje (Para no elegir ninguno escriba "
			    + "\"-1\")");

	    if (id_viaje.equals(-1)) {
		throw new Exception("salir");
	    }

	    viaje = findTrip(listaViajes, id_viaje);
	}

	return viaje;
    }

    private static Trip findTrip(List<Trip> listaViajes, Long id_viaje) {

	for (Trip viaje : listaViajes) { // Para cada viaje en la lista

	    if (viaje.getId().equals(id_viaje)) { // Si coincide el id
		return viaje;
	    }
	}

	return null;
    }
}