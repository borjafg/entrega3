package uo.sdi.business;

import java.util.List;

import uo.sdi.model.Trip;
import uo.sdi.model.User;

public interface TripService {

    public List<Trip> getListActiveTrips(User user) throws Exception;

    /**
     * Busca una lista de viajes en los que un usuario es promotor.
     * 
     * @param user
     *            datos del usuario promotor de los viajes
     * @return lista de viajes en los que es promotor
     * 
     * @throws Exception
     *             ha ocurrido un error al buscar los viajes
     * 
     */
    public List<Trip> getPromoterTrips(User user) throws Exception;

    public void save(Trip trip) throws Exception;

    public void cancelTrip(Trip trip, User user) throws Exception;

    /**
     * Devuelve la informacion de un viaje
     * 
     * @param id
     *            identificador del viaje del que se quieren obtener los datos
     * 
     * @return informacion del viaje
     * 
     * @throws Exception
     *             ha ocurrido un error al buscar la informacion del viaje
     */
    public Trip getInfoTrip(Long id) throws Exception;

    List<Trip> findAll();

    List<Trip> findById(Long id);
}