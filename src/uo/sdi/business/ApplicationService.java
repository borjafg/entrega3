package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Application;
import uo.sdi.model.User;

public interface ApplicationService {
    /**
     * Crea una solicitud para asistir a un viaje.
     * 
     * @param idTrip
     *            identificador del viaje al que solicita asistir
     * @param idUser
     *            identificador del usuario que presenta la solicitud
     * 
     * @throws Exception
     *             Ha ocurrido un error al listar las solicitudes del usuario
     */
    public void applicateForTrip(Long idTrip, Long idUser) throws Exception;

    /**
     * Busca una lista de solicitudes que ha realizado un usuario para asistir a
     * unos viajes.
     * 
     * @param user
     *            usuario que presento las solicitudes
     * 
     * @return Lista de solicitudes que ha realizado el usuario para asistir a
     *         viajes
     * 
     * @throws Exception
     *             Ha ocurrido un error al listar las solicitudes del usuario
     */
    public List<Application> listApplicationsUser(User user) throws Exception;

    /**
     * Busca una lista de solicitudes que han realizado usuarios para asistir a
     * los viajes de un promotor.
     * 
     * @param user
     *            promotor de los viajes de los que se quieren obtener las
     *            solicitudes
     * 
     * @return Lista de solicitudes para asistir a los viajes que creo el
     *         usuario
     * 
     * @throws Exception
     *             Ha ocurrido un error al listar las solicitudes del usuario
     */
    public List<Application> listApplicationsPromoter(User user)
	    throws Exception;

    /**
     * Cancela una solicitud para asistir a un viaje. Para ello, crea una
     * asistencia y la pasa al estado EXCLUDED.<br/>
     * Esta operacion esta reservada para el usuario que presento la solicitud.
     * 
     * @param idUser
     *            Identificador del usuario que pretende retirar la solicitud
     * 
     * @param application
     *            solicitud para asistir al viaje
     * 
     * @throws Exception
     *             Ha ocurrido un error al cancelar la solicitud
     */
    public void cancelApplication(Long idUser, Application application)
	    throws Exception;

    /**
     * Cancela una solicitud para asistir a un viaje. Para ello, crea una
     * asistencia y la pasa al estado EXCLUDED.<br/>
     * <br/>
     * Esta operacion esta reservada para el promotor del viaje, y solamente
     * puede realizarse si no paso la fecha de cierre del viaje.
     * 
     * @param idUser
     *            Identificador del usuario que pretende aceptar la solicitud
     * 
     * @param application
     *            solicitud para asistir al viaje
     * 
     * @throws Exception
     *             Ha ocurrido un error al aceptar la solicitud
     */
    public void acceptApplication(Long idUser, Application application)
	    throws Exception;

    /**
     * Exluye a un usuario de un viaje si todavia no se le habia aceptado en ese
     * viaje. Unicamente el promotor del viaje tiene permiso para hacer esta
     * accion, y solamente si no paso la fecha de cierre del viaje.
     * 
     * @param idPromotor
     *            identificador del promotor del viaje
     * 
     * @param application
     *            solicitud del usuario para asistir al viaje
     * 
     * @throws Exception
     *             Ha ocurrido un error al cancelar la asistencia
     */
    public void excludeUserFromTrip_Applied(Long idPromotor,
	    Application application) throws Exception;
}