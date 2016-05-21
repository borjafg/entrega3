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

    public void save(Trip viaje) throws Exception;
}