package uo.sdi.business;

import uo.sdi.model.Seat;

public interface SeatService {
    /**
     * Exluye a un usuario de un viaje si ya se le habia aceptado en ese viaje.
     * Unicamente el promotor del viaje tiene permiso para hacer esta accion, y
     * solamente si no paso la fecha de cierre del viaje.
     * 
     * @param idPromotor
     *            identificador del promotor del viaje
     * @param seat
     *            asistencia del usuario al viaje
     * 
     * @throws Exception
     *             Ha ocurrido un error al cancelar la asistencia
     * 
     */
    public void excludeUserFromTrip_Confirmed(Long idPromotor, Seat seat)
	    throws Exception;
}