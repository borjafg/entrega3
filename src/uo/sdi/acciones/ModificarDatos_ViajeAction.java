package uo.sdi.acciones;

import alb.util.log.Log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;


public class ModificarDatos_ViajeAction extends AbstractGestionarViajeAction {
	/**
	 * Si se pasan los nuevos datos del viaje, comprueba: </br></br>
	 * 
	 * - Que el viaje existe en el sistema </br> - Que todavía no se inició el
	 * viaje </br> - Que el usuario que intenta modificar los datos del viaje es
	 * su promotor </br></br>
	 * 
	 * En caso de que se cumplan esas condiciones cambia los datos del viaje,
	 * siempre y cuando se cumplan estos requisitos: </br></br>
	 * 
	 * - Las fechas de salida (inicio) y llegada son posteriores a la del día en
	 * que se están modificando </br> - La fecha límite para apuntarse es previa
	 * a la de inicio, que a su vez es previa a la de llegada </br>
	 * 
	 */
	@Override
	protected String logicaNegocio(HttpServletRequest request,
			HttpServletResponse response, Trip datosViaje) {
		try {
			Long idUsuario = ((User) request.getSession().getAttribute("user"))
					.getId();

			Trip viaje = Factories.persistence.newTripDao().findById(
					datosViaje.getId());

			if (viaje == null) {
				request.setAttribute("causaError", "No se ha encontrado el viaje "
						+ "del que se pretenden cambiar los datos");

				return "FRACASO";
			}

			if (viaje.getStatus().equals(TripStatus.CANCELLED)) {
				request.setAttribute("causaError", "El viaje fue cancelado");

				return "FRACASO";
			}

			// Si todavía no pasó la fecha de inicio del viaje
			//
			if (new Date().after(viaje.getDepartureDate())) {
				request.setAttribute("causaError",
						"Ya pasó la fecha de incio del viaje. Ya no "
								+ "se pueden cambiar sus datos");

				return "FRACASO";
			}

			if (!idUsuario.equals(viaje.getId())) {
				request.setAttribute("causaError",
						"Sólo el promotor del viaje puede cambiar sus datos");

				return "FRACASO";
			}

			Factories.persistence.newTripDao().update(datosViaje);
		}

		catch (Exception excep) {
			Log.error("Ha ocurrido un error al intentar guardar los datos del viaje");

			request.setAttribute("causaError",
					"No se han podido guardar los datos del viaje");

			return "FRACASO";
		}

		return "EXITO";
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
}