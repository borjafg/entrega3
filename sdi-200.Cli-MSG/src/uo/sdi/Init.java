package uo.sdi;

import alb.util.console.Console;
import uo.sdi.client.RestClient;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;

/**
 * Su unica funcion es inicializar la aplicación. Sólo se utiliza al arrancar la
 * aplicación. <br/>
 * <br/>
 * Está separada de la clase MainMenu para que el codigo quede más legible, y
 * porque de esta forma si se quisiera cambiar lo que ocurre al iniciar la
 * aplicación sólo habría que tocar y recompilar esta clase.
 * 
 */
public class Init {
    RestClient cliente = new RestClient();

    public void run() throws Exception {
	User user = login();
	MainMenu.setUser(user);
    }

    private User login() throws Exception {
	String usuario;
	String contraseña;

	User user = null;

	while (user != null) {
	    usuario = pedirString("Usuario");
	    contraseña = pedirString("Contraseña");

	    if (usuario.equals("salir") || contraseña.equals("salir")) {
		throw new Exception("salir");
	    }

	    user = cliente.login(usuario, contraseña);

	    if (user != null) {
		if (user.getStatus().equals(UserStatus.CANCELLED)) {
		    user = null; // Cuenta cancelada (No es valida)
		    Console.println("Usuario o contraseña incorrectos");
		}
		
		else if(!user.getPassword().equals(contraseña)) {
		    user = null;
		    Console.println("Usuario o contraseña incorrectos");
		}
	    }
	}

	return user;
    }

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
    private String pedirString(String mensaje) {
	String cadena = null;

	while ((cadena.equals("") || cadena.equals(null))
		&& !cadena.equals("salir")) {

	    Console.println("Para salir escriba \"salir\"");
	    cadena = Console.readString(mensaje);
	}

	return cadena;
    }
}