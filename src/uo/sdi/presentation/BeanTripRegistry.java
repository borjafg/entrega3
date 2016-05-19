package uo.sdi.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.model.Waypoint;
import uo.sdi.presentation.util.Ciudades;
import uo.sdi.presentation.util.ErrorMessageManager;
import uo.sdi.presentation.util.TypeManager;
import alb.util.log.Log;

@ManagedBean(name = "tripRegistry")
@ViewScoped
public class BeanTripRegistry {
	private String calleSalida;
	private String ciudadSalida;
	private String provinciaSalida;
	private String paisSalida;
	private String codigoPostalSalida;
	private String coordenadasSalida;

	private String calleLlegada;
	private String ciudadLlegada;
	private String provinciaLlegada;
	private String paisLlegada;
	private String codigoPostalLlegada;
	private String coordenadasLlegada;

	private String fechaSalida;
	private String fechaLlegada;
	private String fechaCierre;

	private String precioAproximado;
	private String plazasMaximas;
	private String plazasDisponibles;

	private String comentarios;

	/* ============================================================= */
	/* ============================================================= */
	/* ============================================================= */

	public void setCalleSalida(String calleSalida) {
		this.calleSalida = calleSalida;
	}

	public String getCalleSalida() {
		return calleSalida;
	}

	public void setCiudadSalida(String ciudadSalida) {
		this.ciudadSalida = ciudadSalida;
	}

	public String getCiudadSalida() {
		return ciudadSalida;
	}

	public void setProvinciaSalida(String provinciaSalida) {
		this.provinciaSalida = provinciaSalida;
	}

	public String getProvinciaSalida() {
		return provinciaSalida;
	}

	public void setPaisSalida(String paisSalida) {
		this.paisSalida = paisSalida;
	}

	public String getPaisSalida() {
		return paisSalida;
	}

	public void setCodigoPostalSalida(String codigoPostalSalida) {
		this.codigoPostalSalida = codigoPostalSalida;
	}

	public String getCodigoPostalSalida() {
		return codigoPostalSalida;
	}

	public void setCoordenadasSalida(String coordenadasSalida) {
		this.coordenadasSalida = coordenadasSalida;
	}

	public String getCoordenadasSalida() {
		return coordenadasSalida;
	}

	/* ========================= */
	/* ========================= */

	public void setCalleLlegada(String calleLlegada) {
		this.calleLlegada = calleLlegada;
	}

	public String getCalleLlegada() {
		return calleLlegada;
	}

	public void setCiudadLlegada(String ciudadLlegada) {
		this.ciudadLlegada = ciudadLlegada;
	}

	public String getCiudadLlegada() {
		return ciudadLlegada;
	}

	public void setProvinciaLlegada(String provinciaLlegada) {
		this.provinciaLlegada = provinciaLlegada;
	}

	public String getProvinciaLlegada() {
		return provinciaLlegada;
	}

	public void setPaisLlegada(String paisLlegada) {
		this.paisLlegada = paisLlegada;
	}

	public String getPaisLlegada() {
		return paisLlegada;
	}

	public void setCodigoPostalLlegada(String codigoPostalLlegada) {
		this.codigoPostalLlegada = codigoPostalLlegada;
	}

	public String getCodigoPostalLlegada() {
		return codigoPostalLlegada;
	}

	public void setCoordenadasLlegada(String coordenadasLlegada) {
		this.coordenadasLlegada = coordenadasLlegada;
	}

	public String getCoordenadasLlegada() {
		return coordenadasLlegada;
	}

	/* ========================= */
	/* ========================= */

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public String getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	/* ========================= */
	/* ========================= */

	public void setPecioAproximado(String precioAproximado) {
		this.precioAproximado = precioAproximado;
	}

	public String getPecioAproximado() {
		return precioAproximado;
	}

	public void setPlazasMaximas(String plazasMaximas) {
		this.plazasMaximas = plazasMaximas;
	}

	public String getPlazasMaximas() {
		return plazasMaximas;
	}

	public void setPlazasDisponibles(String plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	public String getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getComentarios() {
		return comentarios;
	}

	/* ============================================================= */
	/* ============================================================= */
	/* ============================================================= */

	/**
	 * Misma funcionalidad que el metodo mostrarOpcionesLlegada(), pero en este
	 * caso comprobando el lugar de salida
	 * 
	 * @return true si el usuario escribio algun valor valido en el campo
	 *         provincia de salida, false en caso contrario
	 * 
	 */
	public boolean mostrarOpcionesSalida() {
		if (provinciaSalida != null && !"".equals(provinciaSalida)) {
			return true;
		}

		return false;
	}

	/**
	 * Comprueba si el usuario relleno el campo provincia de llegada en la vista
	 * correspondiente. Si es asi, entonces habra que darle la opcion de elegir
	 * entre varias posibles ciudades de esa provincia.
	 * 
	 * @return true si el usuario escribio algun valor valido en el campo
	 *         provincia de llegada, false en caso contrario
	 * 
	 */
	public boolean mostrarOpcionesLlegada() {
		if (provinciaLlegada != null && !"".equals(provinciaLlegada)) {
			return true;
		}

		return false;
	}

	/**
	 * Devuelve una lista de posibles ciudades de llegada según la provincia de
	 * llegada que se haya indicado previamente. Se usara el texto de entrada
	 * para buscar ciudades que empiecen por esa cadena de texto.
	 * 
	 * @param textoEntrada
	 *            ciudad buscada
	 * @return lista de posibles ciudades
	 * 
	 */
	public List<String> completarCiudadSalida(String textoEntrada) {
		List<String> posiblesCiudades = Ciudades.getCiudades(provinciaSalida);
		List<String> listaFiltrada = new ArrayList<String>();

		for (String ciudad : posiblesCiudades) {
			if (ciudad.startsWith(textoEntrada)) {
				listaFiltrada.add(ciudad);
			}
		}

		return listaFiltrada;
	}

	/**
	 * Devuelve una lista de posibles ciudades de llegada según la provincia de
	 * llegada que se haya indicado previamente. Se usara el texto de entrada
	 * para buscar ciudades que empiecen por esa cadena de texto.
	 * 
	 * @param textoEntrada
	 *            ciudad buscada
	 * @return lista de posibles ciudades
	 * 
	 */
	public List<String> completarCiudadLlegada(String textoEntrada) {
		List<String> posiblesCiudades = Ciudades.getCiudades(provinciaLlegada);
		List<String> listaFiltrada = new ArrayList<String>();

		for (String ciudad : posiblesCiudades) {
			if (ciudad.startsWith(textoEntrada)) {
				listaFiltrada.add(ciudad);
			}
		}

		return listaFiltrada;
	}

	/* ============================================================= */
	/* ============================================================= */
	/* ============================================================= */

	/**
	 * 
	 * 
	 */
	public String guardarViaje() {
		try {
			Trip viaje = new Trip();

			Waypoint GPS_salida = null;
			Waypoint GPS_llegada = null;

			if (coordenadasSalida != "" && coordenadasSalida != null) {
				GPS_salida = transformarCoordenadas(coordenadasSalida);
			}

			if (coordenadasLlegada != "" && coordenadasLlegada != null) {
				GPS_llegada = transformarCoordenadas(coordenadasLlegada);
			}

			AddressPoint lugarSalida = new AddressPoint(calleSalida,
					ciudadSalida, provinciaSalida, paisSalida,
					codigoPostalSalida, GPS_salida);

			AddressPoint lugarLlegada = new AddressPoint(calleLlegada,
					ciudadLlegada, provinciaLlegada, paisLlegada,
					codigoPostalLlegada, GPS_llegada);

			viaje.setDeparture(lugarSalida);
			viaje.setDestination(lugarLlegada);

			viaje.setDepartureDate(TypeManager.stringToDate(fechaSalida,
					"dd/MM/yyyy HH:mm"));
			viaje.setArrivalDate(TypeManager.stringToDate(fechaLlegada,
					"dd/MM/yyyy HH:mm"));
			viaje.setClosingDate(TypeManager.stringToDate(fechaCierre,
					"dd/MM/yyyy HH:mm"));

			viaje.setEstimatedCost(TypeManager.stringToDouble(precioAproximado));
			viaje.setMaxPax(TypeManager.stringToInteger(plazasMaximas));
			viaje.setMaxPax(TypeManager.stringToInteger(plazasDisponibles));
			viaje.setComments(comentarios);

			FacesContext context = FacesContext.getCurrentInstance();
			User user = context.getApplication().evaluateExpressionGet(
					context, "#{login}", BeanLogin.class).getUsuario();

			if (user == null // No logueado o usuario cancelado
					|| (user != null && user.getStatus().equals(
							UserStatus.CANCELLED))) {
				throw new Exception("Usuario invalido");
			}

			viaje.setPromoterId(user.getId());
			viaje.setStatus(TripStatus.OPEN);

			Factories.services.createTripService().save(viaje);

			return "exito";
		}

		catch (Exception excep) {
			if (excep.getMessage().equals("Viaje ya existe")) {
				Log.error("El viaje ya existe");

				ErrorMessageManager.register(FacesContext.getCurrentInstance(),
						"form:guardarViaje", "tripRegistryError_TripExist",
						FacesMessage.SEVERITY_ERROR);

				return "fallo";
			}

			if (excep.getMessage().equals("Usuario invalido")) {
				Log.error("El usuario que intenta crear el viaje no es valido"
						+ "o su sesion ha expirado");

				ErrorMessageManager.register(FacesContext.getCurrentInstance(),
						"form:guardarViaje", "tripRegistryError_UserNotValid",
						FacesMessage.SEVERITY_ERROR);

				return "fallo";
			}

			else {
				Log.error("Ha habido un error al guardar los datos del viaje");

				return "error";
			}
		}
	}

	/**
	 * Convierte un string en unas coordenadas GPS.
	 * 
	 * @param coordenadasGPS
	 *            cadena de texto a convertir (Ejemplo: "34.91, -1.76")
	 * @return coordenadas GPS, o null si no se pudo realizar la conversión
	 * 
	 */
	private Waypoint transformarCoordenadas(String coordenadasGPS) {
		Waypoint coordenadas = null;
		Double latitud;
		Double longitud;

		try {
			latitud = Double.parseDouble(coordenadasGPS.split(",")[0]);
			longitud = Double.parseDouble(coordenadasGPS.split(",")[1]);

			coordenadas = new Waypoint(latitud, longitud);

			return coordenadas;
		}

		catch (Exception excep) {
			return null;
		}
	}
}