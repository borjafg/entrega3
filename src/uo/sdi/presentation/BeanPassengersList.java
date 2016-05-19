package uo.sdi.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import alb.util.log.Log;

/**
 * Almacena una lista de pasajeros cuya asistencia a un viaje esta confirmada.
 * 
 */
@ManagedBean(name = "passengersList")
@ViewScoped
public class BeanPassengersList {
	private Trip viaje;

	private List<User> pasajeros;

	/**
	 * Variable necesaria para que en la vista correspondiente se pueda filtrar
	 * la lista de pasejeros de un viaje según el criterio que el usuario elija
	 */
	private List<User> pasajerosFiltrados;

	/**
	 * Variable necesaria para que en la vista correspondiente el usuario pueda
	 * elegir cuantos filas ver por cada pagina (con viajes) de la tabla
	 */
	private int filasPagina = 5;

	public List<User> getPasajeros() {
		return pasajeros;
	}

	public void setPasajerosFiltrados(List<User> pasajerosFiltrados) {
		this.pasajerosFiltrados = pasajerosFiltrados;
	}

	public List<User> getPasajerosFiltrados() {
		return pasajerosFiltrados;
	}

	public void setFilasPagina(int filasPagina) {

		if (this.filasPagina != filasPagina) {
			Log.debug("El usuario ha elegido cambiar el numero de "
					+ "pasajeros que hay en cada pagina. Antes: [%s]. "
					+ "Ahora: [%s]", this.filasPagina, filasPagina);

			this.filasPagina = filasPagina;
		}
	}

	public int getFilasPagina() {
		return filasPagina;
	}

	public void setViaje(Trip viaje) {
		this.viaje = viaje;
	}

	public Trip getViaje() {
		return viaje;
	}

	/* =========================================================== */
	/* =========================================================== */
	/* =========================================================== */

	/**
	 * Devuelve el nombre y apellidos de un pasajero, y "*" si es el promotor
	 * del viaje
	 * 
	 * @param pasajero
	 *            Pasajero del que se quiere obtener la informacion
	 * @return información del pasajero. Ejemplos:<br/>
	 *         -> Juan Perez Garcia *<br/>
	 *         -> Luis Ramirez Gonzalez<br/>
	 *         -> Juan Vazquez del Valle<br/>
	 */
	public String getInfoPasajero(User pasajero) {
		StringBuilder sb = new StringBuilder();

		sb.append(pasajero.getName()).append(" ");
		sb.append(pasajero.getSurname()).append(" ");
		sb.append(comprobarRolUsuario(pasajero));

		return sb.toString();
	}

	/**
	 * Comprueba si un usuario es promotor de un viaje o es un pasajero
	 * 
	 * @return "*" (promotor) o "" (pasajero)
	 * 
	 */
	private String comprobarRolUsuario(User user) {
		if (viaje.getPromoterId().equals(user.getId())) {
			return "*";
		}

		else {
			return "";
		}
	}
}