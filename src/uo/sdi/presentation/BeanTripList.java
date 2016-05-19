package uo.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.presentation.util.ErrorMessageManager;
import uo.sdi.presentation.util.TypeManager;
import alb.util.log.Log;

/**
 * Almacena una lista de viajes a los que un usuario puede solicitar asistir.
 * 
 */
@ManagedBean(name = "tripList")
@ViewScoped
public class BeanTripList implements Serializable {
	private static final long serialVersionUID = -345254566L;

	private List<Trip> listaViajes;

	/**
	 * Variable necesaria para que en la vista correspondiente se puedan filtrar
	 * los viajes según el criterio que el usuario elija
	 */
	private List<Trip> viajesFiltrados;

	/**
	 * Variable necesaria para que en la vista correspondiente se pueda saber
	 * que viaje selecciono el usuario
	 */
	private Trip viajeSeleccionado;

	/**
	 * Variable necesaria para que en la vista correspondiente el usuario pueda
	 * elegir cuantos filas ver por cada pagina (con viajes) de la tabla
	 */
	private int filasPagina = 5;

	private List<User> pasajeros;

	/* =========================================================== */
	/* =========================================================== */
	/* =========================================================== */

	public BeanTripList() {
		cargarListaViajes();
	}

	/**
	 * Realiza la busqueda de viajes activos y comprueba si el usuario que hizo
	 * la petición está registrado.
	 * 
	 */
	public void cargarListaViajes() {
		try {
			Log.debug("Realizando búsqueda de viajes activos");

			FacesContext contexto = FacesContext.getCurrentInstance();

			User usuario = contexto
					.getApplication()
					.evaluateExpressionGet(contexto, "#{user}",
							BeanUser.class).getUser();

			// Si es un usuario registrado no se le mostrarán los viajes a
			// los que ya ha solicitado asistir. Tampoco se mostrarán aquellos
			// en los que sea promotor

			buscarViajesActivos(usuario);
		}

		catch (Exception excep) {
			Log.error("Ha ocurrido un error mientras se buscaba la lista de "
					+ "viajes activos");

			listaViajes = new ArrayList<Trip>();

			FacesContext contexto = FacesContext.getCurrentInstance();
			ErrorMessageManager.register(contexto, "form:tableTrips",
					"tripList_ErrorProcessingRequest",
					FacesMessage.SEVERITY_FATAL);
		}
	}

	/**
	 * Realiza una búsqueda de viajes activos.
	 * 
	 * @param user
	 *            usuario que realizó la petición (null si no está logueado)
	 * 
	 * @throws Exception
	 *             Ha ocurrido un error al buscar la lista de viajes
	 * 
	 */
	private void buscarViajesActivos(User user) throws Exception {
		listaViajes = Factories.services.createTripService()
				.getListActiveTrips(user);

		Log.debug("Obtenida lista de viajes conteniendo [%s] viajes",
				listaViajes.size());
	}

	/* =========================================================== */
	/* =========================================================== */
	/* =========================================================== */

	public List<Trip> getListaViajes() {
		return listaViajes;
	}

	public void setViajesFiltrados(List<Trip> viajesFiltrados) {
		this.viajesFiltrados = viajesFiltrados;
	}

	public List<Trip> getViajesFiltrados() {
		return viajesFiltrados;
	}

	public void setViajeSeleccionado(Trip viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public Trip getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setFilasPagina(int filasPagina) {
		if (this.filasPagina != filasPagina) {
			Log.debug("El usuario ha elegido cambiar el numero de viajes "
					+ "que hay en cada pagina. Antes: [%s]. Ahora: [%s]",
					this.filasPagina, filasPagina);

			this.filasPagina = filasPagina;
		}
	}

	public int getFilasPagina() {
		return filasPagina;
	}

	/* =========================================================== */
	/* =========================================================== */
	/* =========================================================== */

	/**
	 * Se usa para filtrar los elementos de una tabla de viajes tomando como
	 * criterio de filtrado el precio.
	 * 
	 * @param value
	 *            precio aproximado de uno de los viajes de la tabla
	 * @param filter
	 *            valor que se usa para filtrar (máximo precio buscado)
	 * @param locale
	 *            idioma utilizado por el cliente
	 * 
	 * @return resultado de comparar ambos precios
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();

		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		// Devuelve true si valorMaximo >= precioEvaluado
		return ((Comparable) value).compareTo(Double.valueOf(filterText)) <= 0;
	}

	/**
	 * Se usa para filtrar los elementos de una tabla de viajes tomando como
	 * criterio de filtrado una fecha del viaje (fecha de inicio, fecha de fin,
	 * fecha de cierre).
	 * 
	 * @param value
	 *            cadena de texto usada para filtrarla
	 * @param filter
	 *            valor que se usa para filtrar
	 * @param locale
	 *            idioma utilizado por el cliente
	 * 
	 * @return true si la fecha encaja con el texto usado para filtrar
	 * 
	 */
	public boolean filterByFecha(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();

		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return TypeManager.dateToString((Date) value, "dd/MM/yyyy HH:mm")
				.contains(filterText);
	}

	/**
	 * Convierte una fecha en un String con formato dd/MM/yyyy HH:mm
	 * 
	 * @param fecha
	 *            fecha que va a formteada
	 * @return fecha convertida (Ej: 25/01/2020 20:10)
	 * 
	 */
	public String format(Date fecha) {
		return TypeManager.dateToString(fecha, "dd/MM/yyyy HH:mm");
	}

	/**
	 * Recoge los nombres del promotor del viaje y de los asistentes confirmados
	 * 
	 * @return resultado de procesar esta peticion
	 * 
	 */
	public String verInfoViaje() {
		FacesContext context = FacesContext.getCurrentInstance();
		BeanLogin user = context.getApplication().evaluateExpressionGet(
				context, "#{login}", BeanLogin.class);

		if (user == null) {
			Log.debug("El usuario no esta registrado");

			return "fallo";
		}

		if (viajeSeleccionado != null) {
			Log.debug("El usuario ha seleccionado el viaje: [%s]",
					viajeSeleccionado.toString());

			try {
				this.pasajeros = Factories.services.createUserService()
						.findPassengers(viajeSeleccionado.getId());

				Log.debug("Se ha obtenido con exito la lista de pasajeros del "
						+ "viaje con id: [%d], conteniendo [%s] pasajero/s",
						viajeSeleccionado.getId(), pasajeros.size());

				return "exito";
			}

			catch (Exception excep) {
				Log.error("Ha ocurrido un error al intentar mostrar la lista de "
						+ "pasajeros del viaje");

				return "error";
			}
		}

		else {
			Log.debug("El usuario ha pedido ver la lista de pasajeros de un "
					+ "viaje, pero no habia seleccionado el viaje");

			ErrorMessageManager
					.register(context, "form:tableTrips",
							"tripList_ErrorNoTripSelected",
							FacesMessage.SEVERITY_ERROR);

			return "fallo";
		}
	}

	/* =========================================================== */
	/* =========================================================== */
	/* =========================================================== */

	/**
	 * Registra una petición del usuario para asistir a un viaje.
	 * 
	 * @return resultado de procesar la petición: <br/>
	 *         -> "exito": se ha completado con exito el registro. <br/>
	 *         -> "fallo": el usuario elegido un viaje invalido o ninguno, o su
	 *         sesion expiro. <br/>
	 *         -> "error": ha habido un error durante el procesamiento de la
	 *         peticion
	 * 
	 */
	public String aplicarViaje() {
		try {
			FacesContext contexto = FacesContext.getCurrentInstance();

			User usuario = contexto
					.getApplication()
					.evaluateExpressionGet(contexto, "#{login}",
							BeanUser.class).getUser();

			if (viajeSeleccionado == null) {
				ErrorMessageManager.register(contexto, "form:tableTrips",
						"tripList_ErrorNoTripSelected",
						FacesMessage.SEVERITY_ERROR);

				Log.debug("El usuario no selecciono en que viaje solicitar plaza");

				return "fallo";
			}

			if (usuario == null) {
				Log.debug("El usuario no esta registrado");
				// TODO
				return "noUser";
			}

			Long idUsuario = usuario.getId();
			Long idViaje = viajeSeleccionado.getId();

			Factories.services.createApplicationService().applicateForTrip(
					idViaje, idUsuario);

			Log.debug("El usuario de id: [%d] ha aplicado con exito para "
					+ "asistir al viaje de id: [%s]", idUsuario, idViaje);

			return "exito";
		}

		// =================================
		// =================================

		catch (Exception excep) {
			FacesContext contexto = FacesContext.getCurrentInstance();

			if (excep.getMessage().equals("Viaje cerrado")) {
				ErrorMessageManager.register(contexto, "form:tableTrips", "",
						FacesMessage.SEVERITY_ERROR);

				Log.debug("El viaje que selecciono el usuario esta cerrado");

				return "fallo";
			}

			else if (excep.getMessage().equals("Viaje cancelado")) {
				ErrorMessageManager
						.register(contexto, "form:tableTrips",
								"tripList_ErrorTripClosed",
								FacesMessage.SEVERITY_ERROR);

				Log.debug("El viaje que selecciono el usuario esta cancelado");

				return "fallo";
			}

			else if (excep.getMessage().equals("Ya solicitado")) {
				ErrorMessageManager.register(contexto, "form:tableTrips",
						"tripList_ErrorAlreadyApplied",
						FacesMessage.SEVERITY_ERROR);

				Log.debug("El usuario ya habia solicitado asistir a ese viaje");

				return "fallo";
			}

			else {
				ErrorMessageManager.register(contexto, "form:tableTrips",
						"tripList_ErrorProcessingRequest_passengers",
						FacesMessage.SEVERITY_FATAL);

				Log.debug("Ha ocurrido un error al intentar guardar la "
						+ "solicitud del usuario para asistir al viaje");

				return "error";
			}
		} // Fin catch
	}
}