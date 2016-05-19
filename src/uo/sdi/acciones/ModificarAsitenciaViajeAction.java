package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.TypeManager;
import uo.sdi.acciones.util.ValidadorParametros;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;


/**
 * Modifica la asistencia a un viaje de un usuario. Únicamente el promotor del
 * viaje puede aceptar o rechazar usuarios.
 * 
 * <br />
 * <br />
 * 
 * Hay que indicarle: <br />
 * - El id del viaje del que se modificará la asistencia <br />
 * - El id del usuario cuya asistencia en el viaje va a cambiar <br />
 * - El nuevo estado del usuario repecto al viaje (admitido, excluido) <br />
 * 
 */
public class ModificarAsitenciaViajeAction implements Accion {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String idViaje = (String) request.getAttribute("idViaje");
		String idUsuario = (String) request.getAttribute("idUsuario");
		String nuevoEstado = (String) request.getAttribute("estado");

		if (validarParametros(request, idViaje, idUsuario, nuevoEstado).equals(
				"FRACASO")) {
			return "FRACASO";
		}

		Long idUsuario_Long = TypeManager.stringToLong(idUsuario);
		Long idViaje_Long = TypeManager.stringToLong(idViaje);

		try {
			TripDao tripDao = Factories.persistence.newTripDao();
			SeatDao seatDao = Factories.persistence.newSeatDao();

			Trip viaje = tripDao.findById(idViaje_Long);

			if (peticionValida(request, viaje, idUsuario_Long)
					.equals("FRACASO")) {
				return "FRACASO";
			}

			// ================================================================
			// Se admite a un usuario
			// ================================================================
			if (nuevoEstado.equals("admitido")) {
				// No quedan plazas libres
				if (viaje.getAvailablePax() == 0) {
					Log.debug("No se puede admitir al usuario porque no quedan "
							+ " plazas libres en el viaje");

					request.setAttribute("causaError",
							"No quedan plazas libres en el viaje");

					return "FRACASO";
				}

				Seat asistencia = seatDao.findByUserAndTrip(idUsuario_Long,
						idViaje_Long);

				// El usuario ya habia sido admitido
				if (asistencia != null
						&& asistencia.getStatus().equals(SeatStatus.ACCEPTED)) {
					Log.debug(
							"Ya se había aceptado al usuario con id [%d] en el"
									+ " viaje con id [%d]", idUsuario_Long,
							idViaje_Long);

					request.setAttribute("causaError",
							"Ya se había aceptado al usuario en el viaje");

					return "FRACASO";
				}

				// El usuario no había sido admitido
				else if (asistencia != null
						&& asistencia.getStatus().equals(SeatStatus.EXCLUDED)) {
					asistencia.setStatus(SeatStatus.ACCEPTED);

					// 1 plaza libre más
					viaje.setAvailablePax(viaje.getAvailablePax() + 1);

					seatDao.update(asistencia);
					tripDao.update(viaje);

					Log.debug("Se ha admitido la solicitud del usuario con"
							+ "id: [%d], para el viaje de id: [%d]",
							idUsuario_Long, idViaje_Long);

					Log.debug("Número de plazas libres en el viaje: [%d]",
							viaje.getAvailablePax());

					return "EXITO";
				}

				// No se había registrado al usuario en el
				// viaje (ni excluido ni admitido)
				else {
					asistencia = new Seat();

					asistencia.setStatus(SeatStatus.EXCLUDED);
					asistencia.setTripId(idViaje_Long);
					asistencia.setUserId(idUsuario_Long);

					// 1 plaza libre menos
					viaje.setAvailablePax(viaje.getAvailablePax() + 1);

					seatDao.save(asistencia);
					tripDao.update(viaje);

					Log.debug(
							"Se ha admitido la solicitud del usuario con id: [%d], "
							+ "para el viaje de id: [%d]",
							idUsuario_Long, idViaje_Long);
					Log.debug("Número de plazas libres en el viaje: [%d]",
							viaje.getAvailablePax());

					return "EXITO";
				}
			} // Fin estado admitido

			// ================================================================
			// Se excluye a un usuario
			// ================================================================
			else if (nuevoEstado.equals("excluido")) {
				// Paso la fecha de inicio del viaje
				if (viaje.getClosingDate().before(new Date())) {
					Log.debug("Ya pasó la fecha para apuntarse. No se puede exluir a ningún usuario del viaje");

					request.setAttribute("causaError",
							"Ya ha pasado la fecha límite del viaje");

					return "FRACASO";
				}

				Seat asistencia = seatDao.findByUserAndTrip(idUsuario_Long,
						idViaje_Long);

				// Ya se excluyó al usuario
				if (asistencia != null
						&& asistencia.getStatus().equals(SeatStatus.EXCLUDED)) {
					Log.debug("El usuario con id [%d] ya había sido excluido del viaje con id [%d]");

					request.setAttribute("causaError",
							"El usuario ya había sido excluido del viaje");

					return "FRACASO";
				}

				// El usuario había sido aceptado en el viaje
				else if (asistencia != null
						&& asistencia.getStatus().equals(SeatStatus.ACCEPTED)) {
					asistencia.setStatus(SeatStatus.EXCLUDED);

					// 1 plaza libre más
					viaje.setAvailablePax(viaje.getAvailablePax() + 1);

					seatDao.save(asistencia);
					tripDao.update(viaje);

					Log.debug("Se ha denegado la asistencia del usuario con"
							+ "id: [%d], para el viaje de id: [%d]",
							idUsuario_Long, idViaje_Long);

					Log.debug("Número de plazas libres en el viaje: [%d]",
							viaje.getAvailablePax());

					return "EXITO";
				}

				// No se había registrado al usuario en el
				// viaje (ni excluido ni admitido)
				else {
					asistencia = new Seat();

					asistencia.setStatus(SeatStatus.EXCLUDED);
					asistencia.setTripId(idViaje_Long);
					asistencia.setUserId(idUsuario_Long);

					viaje.setAvailablePax(viaje.getAvailablePax() + 1);

					seatDao.save(asistencia);

					Log.debug("Se ha denegado la asistencia del usuario con"
							+ "id: [%d], para el viaje de id: [%d]",
							idUsuario_Long, idViaje_Long);

					Log.debug("Número de plazas libres en el viaje: [%d]",
							viaje.getAvailablePax());

					return "EXITO";
				}
			} // Fin estado excluido

			// ================================================================
			// Se ha indicado una opción inválida
			// ================================================================
			else {
				request.setAttribute("causaError",
						"La opción indicada no es válida");

				Log.debug(
						"No se ha podido modificar el estado del usuario respecto al "
						+ "viaje porque el estado \"[%s]\" no es válido", nuevoEstado);

				return "FRACASO";
			}
		}

		catch (Exception e) {
			Log.error("Ha ocurrido un error al intentar modificar la asistencia del "
					+ "usuario");

			request.setAttribute("causaError", "Ha ocurrido un error al intentar "
					+ "modificar la asistencia del usuario");

			return "FRACASO";
		}
	}

	public String validarParametros(HttpServletRequest request, String idViaje,
			String idUsuario, String nuevoEstado) {
		if (!ValidadorParametros.isParamsStringValid(idViaje, idUsuario,
				nuevoEstado)) {
			Log.debug("No se puede modificar el estado de un usuario respecto a un "
					+ "viaje porque falta indicar algún campo");

			request.setAttribute(
					"causaError",
					"Ha habido un problema al cambiar la aistencia al viaje. Falta "
					+ "indicar el viaje, el usuario o la accion a realizar "
					+ "(exluir/admitir)");

			return "FRACASO";
		}

		if (!ValidadorParametros.canBeParsedToLong(idViaje, idUsuario)) {
			Log.debug("El identificador del viaje o del usuario son inválidos");

			request.setAttribute("causaError",
					"El identificador del viaje o del usuario no son válidos");

			return "FRACASO";
		}

		return "EXITO";
	}

	private Object peticionValida(HttpServletRequest request, Trip viaje,
			Long idUsuario_Long) {
		User promoter = (User) request.getAttribute("user");

		// ================================================================
		// El usuario no logueado no es el promotor del viaje pero intenta
		// cambiar la asistencia
		// ================================================================
		if (!promoter.getId().equals(viaje.getPromoterId())) {
			Log.debug("El usuario con id = [%d] no es promotor del viaje "
					+ "con id = [%d]. El promotor de ese viaje es el usuario "
					+ "con id = [%d]", idUsuario_Long, viaje.getId(),
					viaje.getPromoterId());

			request.setAttribute("causaError",
					"El usuario indicado no es válido");

			return "FRACASO";
		}

		// ================================================================
		// El viaje indicado fue cancelado
		// ================================================================
		if (viaje.getStatus().equals(TripStatus.CANCELLED)) {
			Log.debug("El viaje con id = [%d] fue cancelado", viaje.getId());

			request.setAttribute("causaError", "El viaje fue cancelado");

			return "FRACASO";
		}

		// ================================================================
		// La cuenta del usuario que pidio asistir al viaje está
		// cancelada
		// ================================================================
		if (Factories.persistence.newUserDao().findById(idUsuario_Long)
				.getStatus().equals(UserStatus.CANCELLED)) {
			Log.debug("La cuenta de usuario con id = [%d] está cancelada",
					idUsuario_Long);

			request.setAttribute("causaError",
					"El usuario indicado no es válido");

			return "FRACASO";
		}

		// ================================================================
		// El usuario no pidió asistir a ese viaje
		// ================================================================
		else if (Factories.persistence.newApplicationDao().findByUserId(
				idUsuario_Long) == null) {
			Log.debug(
					"El usuario [%d] no había solicitado asistir al viaje [%d]",
					idUsuario_Long, viaje.getId());

			request.setAttribute("causaError",
					"El usuario no había solicitado asistir al viaje");

			return "FRACASO";
		}

		// ================================================================
		// Pasó la fecha de inicio del viaje
		// ================================================================
		else if (viaje.getDepartureDate().before(new Date())) {
			Log.debug("El viaje ya empezó, por lo que no se puede modificar la lista "
					+ "de asistencias");

			request.setAttribute("causaError",
					"Ya ha pasado la fecha de inicio del viaje");

			return "FRACASO";
		}

		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
}